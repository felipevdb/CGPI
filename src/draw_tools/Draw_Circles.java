package draw_tools;

import java.util.LinkedList;

import draw_vectors.Vector_Circle;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw_Circles implements Draw_Graphics {
	public Vector_Circle vector = new Vector_Circle();
	public int size;
	public Color color;

	public void renderLastElementG(GraphicsContext gc) {
		gc.setStroke(color);
		gc.setLineWidth(size);
		gc.strokeOval(vector.x, vector.y, vector.r, vector.r);
	}

	public void eraseLastElementG(GraphicsContext gc, Color canvas_color) {

	}

	public void addElement(GraphicsContext gc, LinkedList<Point2D> points) {
		vector.x = points.getFirst().getX();
		vector.y = points.getFirst().getY();
		vector.r = points.getFirst().distance(points.getLast());
		vector.addElement();
		renderLastElementG(gc);
	}

	public void eraseElement(GraphicsContext gc, Color canvas_color, LinkedList<Point2D> points) {
		/*
		 * eraseLastElementG(gc, canvas_color); vector.x = (int)
		 * points.getLast().getX(); vector.y = (int) points.getLast().getY();
		 * vector.eraseElement();
		 */
	}

	public void eraseLastElement(GraphicsContext gc, Color canvas_color) {
		eraseLastElementG(gc, canvas_color);
		vector.eraseLastElement();
	}

	public void selectElement(GraphicsContext gc, int x, int y) {
		/*
		 * int found = 0; int count = 0; LinkedList<LinkedList<Point2D>> lines =
		 * vector.allLines(); while ((found == 0) && (count < lines.size())) {
		 * LinkedList<Point2D> linepoints = lines.get(count); if
		 * (vector.edge_calcs.verifyPointonLine(x, y, linepoints.getFirst().getX(),
		 * linepoints.getFirst().getY(), linepoints.getLast().getX(),
		 * linepoints.getLast().getY())) { gc.setStroke(color); gc.setLineWidth(size);
		 * gc.strokeLine(vector.lines.get(count).getFirst().getX(),
		 * vector.lines.get(count).getFirst().getY(),
		 * vector.lines.get(count).getLast().getX(),
		 * vector.lines.get(count).getLast().getY()); found = 1; } count++; }
		 */
	}
}
