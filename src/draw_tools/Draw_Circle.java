package draw_tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw_Circle {

	public void desenharCirculo(int[] ponto1, int[] ponto2, GraphicsContext gc, Color color, Integer size) {
		int pontoX;
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
		}

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