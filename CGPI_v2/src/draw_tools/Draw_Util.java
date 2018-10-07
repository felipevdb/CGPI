package draw_tools;

import java.util.LinkedList;

import draw_gui.Draw_GUI;
import draw_vectors.Vector_Point;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw_Util {
	public void eraseAll(Draw_GUI canvas_gui) {
		canvas_gui.points_graphics.vector.clearElement();
		changeCanvasColor(canvas_gui.canvas, canvas_gui.gc, canvas_gui.colorbg);
	}

	public void renderAll(Draw_GUI canvas_gui) {
		changeCanvasColor(canvas_gui.canvas, canvas_gui.gc, canvas_gui.colorbg);
		canvas_gui.gc.setFill(canvas_gui.points_graphics.vector_tools.color_point);
		LinkedList<Point2D> points = canvas_gui.points_graphics.vector.allPoints();
		points.forEach((p) -> {
			canvas_gui.gc.fillOval(p.getX() - (4 / 2), p.getY() - (4 / 2), 4, 4); // bug -> não redesenha com tamanho
																					// origem do
			// ponto
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
