package draw_tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface Draw_Graphics {
	public void renderLastElementG(GraphicsContext gc);

	public void eraseLastElementG(GraphicsContext gc, Color canvas_color);

	public void addElement(GraphicsContext gc, int x, int y);

	public void eraseElement(GraphicsContext gc, Color canvas_color, int x, int y);

	public void selectElement(GraphicsContext gc, int x, int y);
}
