package draw_tools;

import java.util.LinkedList;

import draw_gui.Vectors;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw_Points {
	public void renderLastPoint(GraphicsContext gc, Color point_color, int sizep, Vectors vector) {
		gc.setFill(point_color);
		gc.fillOval(vector.lastPoint().getX() - (10 / sizep), vector.lastPoint().getY() - (10 / sizep), sizep, sizep);
	}

	public void eraseLastPoint(GraphicsContext gc, Color canvas_color, int sizep, Vectors vector) {
		gc.setFill(canvas_color);
		gc.fillOval(vector.lastPoint().getX() - (10 / sizep), vector.lastPoint().getY() - (10 / sizep), sizep + 1,
				sizep + 1); // bug -> apagar somente se o ponto foi retirado
	}

	public void addPoint(GraphicsContext gc, Color colorp, int sizep, Vectors vector, int x, int y) {
		vector.addPoint(x, y);
		renderLastPoint(gc, colorp, sizep, vector);
	}

	public void erasePoint(GraphicsContext gc, Color colorbg, int sizep, Vectors vector, int x, int y) {
		if (vector.numberPoints() > 0) {
			eraseLastPoint(gc, colorbg, sizep, vector);
			vector.erasePoint(x, y);
		}
	}

	public void selectPoint(GraphicsContext gc, Color colorselect, int sizep, Vectors vector, int x, int y) {
		int found = 0;
		int count = 0;
		LinkedList<Point2D> points = vector.allPoints();
		while ((found == 0) && (count < points.size())) {
			if (((int) points.get(count).getX() <= x + 3 && (int) points.get(count).getX() >= x - 3)
					&& ((int) points.get(count).getY() <= y + 3 && (int) points.get(count).getY() >= y - 3)) {
				gc.setFill(colorselect);
				gc.fillOval(points.get(count).getX() - (sizep / 2), points.get(count).getY() - (sizep / 2), sizep,
						sizep);
				found = 1;
			}
			count++;
		}
	}
}
