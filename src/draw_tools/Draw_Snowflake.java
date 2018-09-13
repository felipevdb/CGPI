package draw_tools;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw_Snowflake {
	public void drawLine(int[] ponto1, int[] ponto2, GraphicsContext gc, Color color, Integer size) {
		Draw_Edge linha = new Draw_Edge();
		linha.desenharLinha(ponto1, ponto2, gc, color, size);
	}

	public void drawFractal(double x, double y, double direction, double length, int level, GraphicsContext gc) {
		if (level == 0) {
			System.out.println("Nao eh possivel!");
		} else if (level == 1) {
			int[] ponto1 = new int[2];
			int[] ponto2 = new int[2];
			ponto1[0] = (int) x;
			ponto1[1] = (int) y;
			ponto2[0] = (int) (ponto1[0] + length* Math.cos(direction));
			ponto2[1] = (int) (ponto1[1] + length* Math.sin(direction));
			drawLine(ponto1, ponto2, gc, Color.BLACK, 2);
			// drawLine(x, y, length, direction);
			x += (length * Math.cos(direction));
			y += (length * Math.sin(direction));
		} else {
			drawFractal(x, y, direction, length / 3, (level - 1), gc);
			x += (length * Math.cos(direction) / 3);
			y += (length * Math.sin(direction) / 3);
			direction = direction + (Math.toRadians(60));

			drawFractal(x, y, direction, length / 3, (level - 1), gc);
			x += (length * Math.cos(direction) / 3);
			y += (length * Math.sin(direction) / 3);
			direction -= (Math.toRadians(120));

			drawFractal(x, y, direction, length / 3, (level - 1), gc);
			x += (length * Math.cos(direction) / 3);
			y += (length * Math.sin(direction) / 3);
			direction += (Math.toRadians(60));

			drawFractal(x, y, direction, length / 3, (level - 1), gc);
			x += (length * Math.cos(direction) / 3);
			y += (length * Math.sin(direction) / 3);
		}
	}

	public void run(GraphicsContext gc, Canvas canvas) {
		int width = (int)canvas.getWidth()-400;
		int height = (int)canvas.getHeight()+250;
		double x = width / 2;
		double y = height / 2;
		int level = 6;
		double length = 400;
		double direction = 0;

		for (int i = 0; i < 3; i++) {
			drawFractal(x, y, direction, length, level, gc);
			x = x + length * Math.cos(direction);
			y = y + length * Math.sin(direction);
			direction -= (Math.toRadians(120));
		}
	}
}
