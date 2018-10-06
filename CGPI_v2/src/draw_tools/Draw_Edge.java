package draw_tools;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.geometry.Point2D;

public class Draw_Edge {

	public LinkedList<Point2D> desenharLinha(int[] ponto1, int[] ponto2) {
		
		LinkedList<Point2D> pontosLinhas = new LinkedList<>();
		
		
		int dx = ponto2[0] - ponto1[0];
		int dy = ponto2[1] - ponto1[1];
		int inclinacao = 0;
		if (dx < 0) // caso ponto final < ponto inicial
		{
			pontosLinhas = desenharLinha(ponto2, ponto1);
			return pontosLinhas;
		}
		if (dy < 0)
			inclinacao = -1;
		else
			inclinacao = 1;

		int d;

		int x1, y1;

		x1 = ponto1[0];
		y1 = ponto1[1];

		pontosLinhas.add(new Point2D(x1, y1));

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
					pontosLinhas.add(new Point2D(x1, y1));
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
					pontosLinhas.add(new Point2D(x1, y1));
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
					pontosLinhas.add(new Point2D(x1, y1));
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
					pontosLinhas.add(new Point2D(x1, y1));
				}
			}
		}
		pontosLinhas.add(new Point2D(ponto2[0], ponto2[1]));
		
		return pontosLinhas;
	}

}