package draw_tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw_Triangle {

	public void desenharTriangulo(int[] ponto1, int[] ponto2, int[] ponto3, GraphicsContext gc, Color color, Integer size) {
		Draw_Edge linha = new Draw_Edge();
		linha.desenharLinha(ponto1, ponto2, gc, color, size);
		linha.desenharLinha(ponto1, ponto3, gc, color, size);
		linha.desenharLinha(ponto2, ponto3, gc, color, size);
	}
}
