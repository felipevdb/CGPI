package draw_tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw_Rectangle {
	
	public void desenharRetangulo(int[] ponto1, int[] ponto2, GraphicsContext gc, Color color, Integer size) {
		Draw_Edge linha = new Draw_Edge();
		int[] ponto3 = new int[2];
		int[] ponto4 = new int[2];
		ponto3[0] = ponto1[0];
		ponto3[1] = ponto2[1];
		ponto4[0] = ponto2[0];
		ponto4[1] = ponto1[1];
		linha.desenharLinha(ponto1, ponto3, gc, color, size);
		linha.desenharLinha(ponto1, ponto4, gc, color, size);
		linha.desenharLinha(ponto3, ponto2, gc, color, size);
		linha.desenharLinha(ponto2, ponto4, gc, color, size);
		
	}
}
