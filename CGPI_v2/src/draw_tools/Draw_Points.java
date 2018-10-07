package draw_tools;

import java.util.LinkedList;

import draw_vectors.Vector_Point;
import draw_vectors.Vector_Util;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw_Points implements Draw_Graphics {
	public Vector_Point vector = new Vector_Point();
	public Vector_Util vector_tools = new Vector_Util();

	public void renderLastElementG(GraphicsContext gc) {
		gc.setFill(vector_tools.color_point);
		gc.fillOval(vector.lastPoint().getX() - (10 / vector_tools.size_point),
				vector.lastPoint().getY() - (10 / vector_tools.size_point), vector_tools.size_point,
				vector_tools.size_point);
	}

	public void eraseLastElementG(GraphicsContext gc, Color canvas_color) {
		gc.setFill(canvas_color);
		gc.fillOval(vector.lastPoint().getX() - (10 / vector_tools.size_point),
				vector.lastPoint().getY() - (10 / vector_tools.size_point), vector_tools.size_point + 1,
				vector_tools.size_point + 1); // bug -> apagar somente se o ponto foi retirado
	}

	public void addElement(GraphicsContext gc, int x, int y) {
		vector.x = x;
		vector.y = y;
		vector.addElement();
		renderLastElementG(gc);
	}

	public void eraseElement(GraphicsContext gc, Color canvas_color, int x, int y) {
		if (vector.numberElements() > 0) {
			eraseLastElementG(gc, canvas_color);
			vector.x = x;
			vector.y = y;
			vector.eraseElement();
		}
	}

	public void selectElement(GraphicsContext gc, int x, int y) {
		int found = 0;
		int count = 0;
		LinkedList<Point2D> points = vector.allPoints();
		while ((found == 0) && (count < points.size())) {
			if (((int) points.get(count).getX() <= x + 3 && (int) points.get(count).getX() >= x - 3)
					&& ((int) points.get(count).getY() <= y + 3 && (int) points.get(count).getY() >= y - 3)) {
				gc.setFill(vector_tools.color_pselect);
				gc.fillOval(points.get(count).getX() - (vector_tools.size_point / 2),
						points.get(count).getY() - (vector_tools.size_point / 2), vector_tools.size_point,
						vector_tools.size_point);
				found = 1;
			}
			count++;
		}
	}
}
