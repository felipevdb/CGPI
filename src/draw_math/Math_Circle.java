package draw_math;

import java.util.LinkedList;

import javafx.geometry.Point2D;

public class Math_Circle {
	
	public LinkedList<Point2D> desenharCirculo(int[] ponto1, int[] ponto2) {
		
		LinkedList<Point2D> pontosCirculo = new LinkedList<>();
		
		int dx = ponto2[0] - ponto1[0];
		int dy = ponto2[1] - ponto1[1];
		
		int xC = ponto1[0];
		int yC = ponto1[1];
		
		int r = (int) Math.sqrt((Math.pow(dx, 2) + Math.pow(dy, 2)));
		int x = 0, y = r, u = 1, v = 2 * r - 1, E = 0;
				while (x < y){  
					
					pontosCirculo.add(new Point2D( xC + x, yC + y)); // NNE
					pontosCirculo.add(new Point2D( (xC + y), (yC - x)));// ESE
					pontosCirculo.add(new Point2D( xC - x, yC - y));// SSW
					pontosCirculo.add(new Point2D( xC - y, yC + x)); // WNW
					
					
					x++; E += u; u += 2;
					if (v < 2 * E){
						y--; 
						E -= v; 
						v -= 2;
					}
					if (x > y) break;
					
					pontosCirculo.add(new Point2D(  xC + y, yC + x)); // ENE
					pontosCirculo.add(new Point2D(xC + x, yC - y)); // SSE
					pontosCirculo.add(new Point2D(xC - y, yC - x));// WSW
					pontosCirculo.add(new Point2D(xC -x, yC + y));// NNW

				}
				
				pontosCirculo.add(new Point2D(ponto1[0],ponto1[1]));
				
				return pontosCirculo;
	}
}