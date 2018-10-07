package draw_math;

import java.util.LinkedList;

import javafx.geometry.Point2D;

public class Math_Rectangle {

	public void desenharRetangulo(int[] ponto1, int[] ponto2) {
		
		LinkedList<LinkedList<Point2D>> linhasRetangulo = new LinkedList<>();
		Math_Edge linha = new Math_Edge();
		
		int[] ponto3 = new int[2];
		int[] ponto4 = new int[2];
		ponto3[0] = ponto1[0];
		ponto3[1] = ponto2[1];
		ponto4[0] = ponto2[0];
		ponto4[1] = ponto1[1];
		
		
		linhasRetangulo.add(linha.desenharLinha(ponto1, ponto3));
		linhasRetangulo.add(linha.desenharLinha(ponto1, ponto4));
		linhasRetangulo.add(linha.desenharLinha(ponto3, ponto2));
		linhasRetangulo.add(linha.desenharLinha(ponto2, ponto4));
		
	}
	
}
