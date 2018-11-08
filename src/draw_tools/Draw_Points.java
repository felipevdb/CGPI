package draw_tools;

import java.util.LinkedList;

import draw_vectors.Vector_Point;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw_Points implements Draw_Graphics {
	public Vector_Point vector = new Vector_Point();
	public int size;
	public Color color;

	public void renderLastElementG(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillOval(vector.lastPoint().getX() - (size / 2), vector.lastPoint().getY() - (size / 2), size, size);
	}

	public void eraseLastElementG(GraphicsContext gc, Color canvas_color) {
		gc.setFill(canvas_color);
		gc.fillOval(vector.lastPoint().getX() - (size / 2), vector.lastPoint().getY() - (size / 2), size + 1, size + 1); // bug
																															// ->
																															// apagar
																															// somente
																															// se
																															// o
																															// ponto
																															// foi
																															// retirado
	}

	public void addElement(GraphicsContext gc, LinkedList<Point2D> points) {
		vector.x = (int) points.getLast().getX();
		vector.y = (int) points.getLast().getY();
		vector.addElement();
		renderLastElementG(gc);
	}

	public void eraseElement(GraphicsContext gc, Color canvas_color, LinkedList<Point2D> points) {
		if (vector.numberElements() > 0) {
			// eraseElementG(gc, canvas_color);
			vector.x = (int) points.getLast().getX();
			vector.y = (int) points.getLast().getY();
			vector.eraseElement();
		}
	}

	public void eraseLastElement(GraphicsContext gc, Color canvas_color) {
		eraseLastElementG(gc, canvas_color);
		vector.eraseLastElement();
	}

	public void selectElement(GraphicsContext gc, int x, int y) {
		int found = 0;
		int count = 0;
		LinkedList<Point2D> points = vector.allPoints();
		while ((found == 0) && (count < points.size())) {
			if (((int) points.get(count).getX() <= x + 3 && (int) points.get(count).getX() >= x - 3)
					&& ((int) points.get(count).getY() <= y + 3 && (int) points.get(count).getY() >= y - 3)) {
				gc.setFill(color);
				gc.fillOval(points.get(count).getX() - (size / 2), points.get(count).getY() - (size / 2), size, size);
				found = 1;
			}
			count++;
		}
	}
}
