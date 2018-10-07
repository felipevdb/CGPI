package draw_vectors;

import java.util.LinkedList;

import javafx.geometry.Point2D;

public class Vector_Point implements Vector_Elements {
	public LinkedList<Point2D> points = new LinkedList<>();
	public int x = -1, y = -1;

	public void clearElement() {
		points.clear();
	}

	public int numberElements() {
		return points.size();
	}

	public void addElement() {
		addElementPoint();
	}

	public void eraseElement() {
		eraseElementPoint();
	}

	public Point2D lastPoint() {
		return points.getLast();
	}

	public LinkedList<Point2D> allPoints() {
		return points;
	}

	public void addElementPoint() {
		points.add(new Point2D(x, y));
	}

	public void eraseElementPoint() {
		int found = 0;
		int count = 0;
		while ((found == 0) && (count < points.size())) {
			if (((int) points.get(count).getX() <= x + 3 && (int) points.get(count).getX() >= x - 3)
					&& ((int) points.get(count).getY() <= y + 3 && (int) points.get(count).getY() >= y - 3)) {
				points.remove(points.get(count));
				found = 1;
			}
			count++;
		}
	}
}
