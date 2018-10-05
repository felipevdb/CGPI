package draw_tools;

import javafx.geometry.Point2D;

public class Draw_Dot extends Point2D{
	Draw_Dot () {
		super(0, 0);
	}

	Draw_Dot (double x, double y) {
		super(x, y);
	}

	Draw_Dot (Draw_Dot p) {
		super(p.getx(), p.gety());
	}


	public double getx() {
		return getX();
	}

	public double gety() {
		return getY();
	}

    public double calcularDistancia(Draw_Dot p) {
		return distance(p);
	}
    
    public double calcularDistancia(double x, double y) {
		return distance(x, y);
	}
}