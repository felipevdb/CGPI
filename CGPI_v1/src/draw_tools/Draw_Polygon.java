package draw_tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw_Polygon {

	public void desenharPoligonoFechado(int indicePoligono, int[][] pontos, GraphicsContext gc, Color color, Integer size) {
		// TODO Auto-generated method stub
		int x = 1;
		Draw_Edge linha = new Draw_Edge();
		int [] ponto1 = new int[2];
		int [] ponto2 = new int[2];
		while(x<indicePoligono) {
			ponto1[0]=pontos[x-1][0];
			ponto1[1]=pontos[x-1][1];
			ponto2[0]=pontos[x][0];
			ponto2[1]=pontos[x][1];
			
			linha.desenharLinha(ponto1, ponto2, gc, color, size);
			x++;
		}
		
		ponto1[0]=pontos[x-1][0];
		ponto1[1]=pontos[x-1][1];
		ponto2[0]=pontos[0][0];
		ponto2[1]=pontos[0][1];
		
		linha.desenharLinha(ponto1, ponto2, gc, color, size);
		
		
	}
	
	public void desenharPoligonoAberto(int indicePoligono, int[][] pontos, GraphicsContext gc, Color color, Integer size) {
		// TODO Auto-generated method stub
		int x = 1;
		Draw_Edge linha = new Draw_Edge();
		int [] ponto1 = new int[2];
		int [] ponto2 = new int[2];
		while(x<indicePoligono) {
			ponto1[0]=pontos[x-1][0];
			ponto1[1]=pontos[x-1][1];
			ponto2[0]=pontos[x][0];
			ponto2[1]=pontos[x][1];
			
			linha.desenharLinha(ponto1, ponto2, gc, color, size);
			x++;
		}
		
	}

	
}
