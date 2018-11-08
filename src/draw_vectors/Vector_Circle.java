package draw_vectors;

import java.util.LinkedList;

import javafx.scene.shape.Circle;

public class Vector_Circle implements Vector_Elements {
	public LinkedList<Circle> circles = new LinkedList<>();
	public double x = -1, y = -1, r = 0;

	public void addElement() {
		addElementCircle();
	}

	public void eraseElement() {
		eraseElementCircle();
	}

	public void eraseLastElement() {
		circles.removeLast();
	}

	public void clearElement() {
		circles.clear();
	}

	public int numberElements() {
		return circles.size();
	}

	public Circle lastCircle() {
		return circles.getLast();
	}

	public LinkedList<Circle> allCircles() {
		return circles;
	}

	private void addElementCircle() {
		circles.add(new Circle(x, y, r));
	}

	private void eraseElementCircle() {
		/*
		 * int found = 0; int count = 0; while ((found == 0) && (count < lines.size()))
		 * { LinkedList<Point2D> linepoints = lines.get(count); if
		 * (edge_calcs.verifyPointonLine(x, y, linepoints.getFirst().getX(),
		 * linepoints.getFirst().getY(), linepoints.getLast().getX(),
		 * linepoints.getLast().getY())) { lines.remove(linepoints); found = 1; }
		 * count++; }
		 */
	}

}
