package draw_tools;

import java.util.LinkedList;

import draw_gui.Draw_GUI;
import draw_vectors.Vector_Point;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Draw_All {
	public void eraseAll(Draw_GUI canvas_gui) {
		canvas_gui.points_graphics.vector.clearElement();
		canvas_gui.lines_graphics.vector.clearElement();
		canvas_gui.circles_graphics.vector.clearElement();
		changeCanvasColor(canvas_gui.canvas, canvas_gui.gc, canvas_gui.colorbg);
	}

	public void renderAll(Draw_GUI canvas_gui) {
		changeCanvasColor(canvas_gui.canvas, canvas_gui.gc, canvas_gui.colorbg);
		canvas_gui.gc.setFill(canvas_gui.points_graphics.color);
		LinkedList<Point2D> points = canvas_gui.points_graphics.vector.allPoints();
		points.forEach((p) -> {
			canvas_gui.gc.fillOval(p.getX() - (canvas_gui.points_graphics.size / 2),
					p.getY() - (canvas_gui.points_graphics.size / 2), canvas_gui.points_graphics.size,
					canvas_gui.points_graphics.size); // bug -> não redesenha com tamanho
			// origem do
			// ponto
		});
		LinkedList<LinkedList<Point2D>> lines = canvas_gui.lines_graphics.vector.allLines();
		lines.forEach((l) -> {
			canvas_gui.gc.strokeLine(l.getFirst().getX(), l.getFirst().getY(), l.getLast().getX(), l.getLast().getY());
		});
		LinkedList<Circle> circles = canvas_gui.circles_graphics.vector.allCircles();
		circles.forEach((c) -> {
			canvas_gui.gc.strokeOval(c.getCenterX(), c.getCenterY(), c.getRadius(), c.getRadius());
		});
	}

	public void selectAll(Vector_Point vector_point) { // finalizar implementação
		vector_point.allPoints();
	}

	public void changeCanvasColor(Canvas canvas, GraphicsContext gc, Color canvas_color) {
		gc.setFill(canvas_color);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
}
