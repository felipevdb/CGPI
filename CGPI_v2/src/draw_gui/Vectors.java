package draw_gui;

import java.util.LinkedList;

import javafx.geometry.Point2D;

public class Vectors {
	LinkedList<Point2D> points = new LinkedList<>();
	LinkedList<LinkedList<Point2D>> lines = new LinkedList<>();
	LinkedList<LinkedList<Point2D>> circles = new LinkedList<>();

	public void addPoint(int x, int y) {
		points.add(new Point2D(x, y));
	}

	public void erasePoint(int x, int y) {
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

	public Point2D lastPoint() {
		return points.getLast();
	}

	public void cleanPoints() {
		points.clear();
	}

	public LinkedList<Point2D> allPoints() {
		return points;
	}

	public int numberPoints() {
		return points.size();
	}

}
