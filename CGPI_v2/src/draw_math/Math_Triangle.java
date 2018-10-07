package draw_math;

import java.util.LinkedList;

import javafx.geometry.Point2D;

public class Math_Triangle {

	public LinkedList<LinkedList<Point2D>> desenharTriangulo(int[] ponto1, int[] ponto2, int[] ponto3) {
		
		LinkedList<LinkedList<Point2D>> linhasTriangulo = new LinkedList<>();
		
		Math_Edge linha = new Math_Edge();
		
		linhasTriangulo.add(linha.desenharLinha(ponto1, ponto2));
		linhasTriangulo.add(linha.desenharLinha(ponto1, ponto3));
		linhasTriangulo.add(linha.desenharLinha(ponto2, ponto3));
		
		return linhasTriangulo;
	}
	
}
