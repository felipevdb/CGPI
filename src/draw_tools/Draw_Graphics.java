package draw_tools;

import java.util.LinkedList;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface Draw_Graphics {
	public void renderLastElementG(GraphicsContext gc);

	public void eraseLastElementG(GraphicsContext gc, Color canvas_color);

	public void addElement(GraphicsContext gc, LinkedList<Point2D> points);

	public void eraseElement(GraphicsContext gc, Color canvas_color, LinkedList<Point2D> points);
	
	public void eraseLastElement(GraphicsContext gc, Color canvas_color);

	public void selectElement(GraphicsContext gc, int x, int y);
}
