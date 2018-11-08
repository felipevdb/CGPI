package draw_vectors;

import java.util.LinkedList;

import draw_math.Math_Edge;
import javafx.geometry.Point2D;

public class Vector_Line implements Vector_Elements {
	public LinkedList<LinkedList<Point2D>> lines = new LinkedList<>();
	public Vector_Point line_points = new Vector_Point();
	public Math_Edge edge_calcs = new Math_Edge();
	public int x = -1, y = -1;

	public void addElement() {
		addElementLine();
	}

	public void eraseElement() {
		eraseElementLine();
	}

	public void eraseLastElement() {
		lines.removeLast();
	}

	public void clearElement() {
		lines.clear();
	}

	public int numberElements() {
		return lines.size();
	}

	public LinkedList<Point2D> lastLine() {
		return lines.getLast();
	}

	public LinkedList<LinkedList<Point2D>> allLines() {
		return lines;
	}

	private void addElementLine() {
		lines.add(line_points.points);
	}

	private void eraseElementLine() {
		int found = 0;
		int count = 0;
		while ((found == 0) && (count < lines.size())) {
			LinkedList<Point2D> linepoints = lines.get(count);
			if (edge_calcs.verifyPointonLine(x, y, linepoints.getFirst().getX(), linepoints.getFirst().getY(),
					linepoints.getLast().getX(), linepoints.getLast().getY())) {
				lines.remove(linepoints);
				found = 1;
			}
			count++;
		}
	}

}
