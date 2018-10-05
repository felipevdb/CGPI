package draw_tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw_Circle {

	public void desenharCirculo(int[] ponto1, int[] ponto2, GraphicsContext gc, Color color, Integer size) {
		int dx = ponto2[0] - ponto1[0];
		int dy = ponto2[1] - ponto1[1];
		
		int xC = ponto1[0];
		int yC = ponto1[1];
		
		Dot_Gr ponto3;
		Dot_Gr ponto4;
		Dot_Gr ponto5;
		Dot_Gr ponto6;
		Dot_Gr ponto7;
		Dot_Gr ponto8;
		Dot_Gr ponto9;
		Dot_Gr ponto10;
		
		int r = (int) Math.sqrt((Math.pow(dx, 2) + Math.pow(dy, 2)));
		int x = 0, y = r, u = 1, v = 2 * r - 1, E = 0;
				while (x < y){   
					ponto3 = new Dot_Gr( xC + x, yC + y,color,size); // NNE
					ponto4 = new Dot_Gr((xC + y), (yC - x),color,size); // ESE
					ponto5 = new Dot_Gr( xC - x, yC - y,color,size); // SSW
					ponto6 = new Dot_Gr( xC - y, yC + x,color,size); // WNW
					ponto3.desenharPonto(gc);
					ponto4.desenharPonto(gc);
					ponto5.desenharPonto(gc);
					ponto6.desenharPonto(gc);
					x++; E += u; u += 2;
					if (v < 2 * E){
						y--; 
						E -= v; 
						v -= 2;
					}
					if (x > y) break;
					ponto7 = new Dot_Gr( xC + y, yC + x,color,size); // ENE
					ponto8 = new Dot_Gr( xC + x, yC - y,color,size); // SSE
					ponto9 = new Dot_Gr( xC - y, yC - x,color,size); // WSW
					ponto10 = new Dot_Gr( xC -x, yC + y,color,size); // NNW
					ponto7.desenharPonto(gc);
					ponto8.desenharPonto(gc);
					ponto9.desenharPonto(gc);
					ponto10.desenharPonto(gc);
				}
				Dot_Gr pontoClear = new Dot_Gr(ponto1[0],ponto1[1],Color.WHITE,size);
				pontoClear.desenharPonto(gc);
		
		/*int pontoX;
		int pontoY;

		// Pontos 4 Quadrantes
		Dot_Gr pontoPrimeiroQuadrante;
		Dot_Gr pontoSegundoQuadrante;
		Dot_Gr pontoTerceiroQuadrante;
		Dot_Gr pontoQuartoQuadrante;

		int dx = ponto2[0] - ponto1[0];
		int dy = ponto2[1] - ponto1[1];

		// Raiz quadrada de (Xb - Xa) ao quadrado + (Yb-Ya) ao quadrado
		double raio = Math.sqrt((Math.pow(dx, 2) + Math.pow(dy, 2)));

		for (int grau = 0; grau <= 90; grau++) {
			pontoX = (int) (raio * Math.cos(grau)) + ponto1[0];

			pontoY = (int) (raio * Math.sin(grau)) + ponto1[1];

			pontoPrimeiroQuadrante = new Dot_Gr(pontoX, pontoY, color, size);
			pontoSegundoQuadrante = new Dot_Gr(-pontoX, pontoY, color, size);
			pontoTerceiroQuadrante = new Dot_Gr(pontoX, -pontoY, color, size);
			pontoQuartoQuadrante = new Dot_Gr(-pontoX, -pontoY, color, size);

			// Desenha os pontos
			pontoPrimeiroQuadrante.desenharPonto(gc);
			pontoSegundoQuadrante.desenharPonto(gc);
			pontoTerceiroQuadrante.desenharPonto(gc);
			pontoQuartoQuadrante.desenharPonto(gc);
		}*/

	}

}
/*
 * public void desenharCirculo(GraphicsContext g, int x1, int y1, int x2, int
 * y2, int diametro, String nome, Color cor){ int pontoX; int pontoY;
 * 
 * Dot_Gr pontoPrimeiroQuadrante; Dot_Gr pontoSegundoQuadrante; Dot_Gr
 * pontoTerceiroQuadrante; Dot_Gr pontoQuartoQuadrante;
 * 
 * double x = ; double raio = Math.sqrt(Math.pow((x2 - x1), 2));
 * 
 * for (int grau = 0; grau <= 90; grau++ ){ pontoX = (int) (raio *
 * Math.cos(grau)); pontoY = (int) (raio * Math.sin(grau));
 * 
 * // Cria pontos pontoPrimeiroQuadrante = new Dot_Gr(pontoX, pontoY, cor, nome,
 * diametro); pontoSegundoQuadrante = new Dot_Gr(-pontoX, pontoY, cor, nome,
 * diametro); pontoTerceiroQuadrante = new Dot_Gr(pontoX, -pontoY, cor, nome,
 * diametro); pontoQuartoQuadrante = new Dot_Gr(-pontoX, -pontoY, cor, nome,
 * diametro);
 * 
 * // Desenha os pontos pontoPrimeiroQuadrante.desenharPonto(g);
 * pontoSegundoQuadrante.desenharPonto(g);
 * pontoTerceiroQuadrante.desenharPonto(g);
 * pontoQuartoQuadrante.desenharPonto(g); } }
 */