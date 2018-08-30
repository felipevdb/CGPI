package draw_tools;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;
import sun.security.provider.certpath.Vertex;

public class Draw_Edge {

	public void desenharLinha(int[] ponto1, int[] ponto2, GraphicsContext gc, Color color, Integer size) {

		int dx = ponto2[0] - ponto1[0];
		int dy = ponto2[1] - ponto1[1];
		int inclinacao = 0;
		if (dx < 0) // caso ponto final < ponto inicial
		{
			desenharLinha(ponto2, ponto1, gc, color, size);
			return;
		}
		if (dy < 0)
			inclinacao = -1;
		else
			inclinacao = 1;

		int d;

		int x1, y1;

		x1 = ponto1[0];
		y1 = ponto1[1];

		Dot_Gr ponto12 = new Dot_Gr(x1, y1, color, size);
		ponto12.desenharPonto(gc);

		if (dx >= inclinacao * dy) { // m<=1
			if (dy < 0) { // caso y2<y1
				d = 2 * dy + dx;
				while (x1 < ponto2[0]) {
					if (d < 0) { // escolhido � o I
						d += 2 * (dy + dx);
						x1++;
						y1--;
					} else { // escolhido � o S
						d += 2 * dy;
						x1++; // varia apenas no eixo x
					}
					Dot_Gr ponto13 = new Dot_Gr(x1, y1, color, size);
					ponto13.desenharPonto(gc);
				}
			} else { // caso y1<y2
				d = 2 * dy - dx;
				while (x1 < ponto2[0]) {
					if (d < 0) { // escolhido � o I
						d += 2 * dy;
						x1++; // varia apenas no eixo x
					} else { // escolhido � o S
						d += 2 * (dy - dx);
						x1++;
						y1++;
					}
					Dot_Gr ponto14 = new Dot_Gr(x1, y1, color, size);
					ponto14.desenharPonto(gc);
				}
			}
		} else { // |m|>1
			if (dy < 0) { // caso y2<y1
				d = dy + 2 * dx;
				while (y1 > ponto2[1]) {
					if (d < 0) {
						d += 2 * dx;
						y1--; // varia apenas no eixo y
					} else {
						d += 2 * (dy + dx);
						x1++;
						y1--;
					}
					Dot_Gr ponto15 = new Dot_Gr(x1, y1, color, size);
					ponto15.desenharPonto(gc);
				}
			} else { // caso y1<y2
				d = dy - 2 * dx;
				while (y1 < ponto2[1]) {
					if (d < 0) {
						d += 2 * (dy - dx);
						x1++;
						y1++;
					} else {
						d += -2 * dx;
						y1++; // varia apenas no eixo y
					}
					Dot_Gr ponto16 = new Dot_Gr(x1, y1, color, size);
					ponto16.desenharPonto(gc);
				}
			}
		}
		Dot_Gr ponto17 = new Dot_Gr(ponto2[0], ponto2[1], color, size);
		ponto17.desenharPonto(gc);
	}

}
