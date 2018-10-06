package draw_tools;

import draw_gui.Vectors;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Canvas_Tools {
	public void eraseAll(Canvas canvas, GraphicsContext gc, Color canvas_color, Vectors vector) {
		vector.cleanPoints();
		changeCanvasColor(canvas, gc, canvas_color);
	}

	public void renderAll(Canvas canvas, Color canvas_color, GraphicsContext gc, Color point_color, Vectors vector) {
		changeCanvasColor(canvas, gc, canvas_color);
		gc.setFill(point_color);
		vector.allPoints().forEach((p) -> {
			gc.fillOval(p.getX() - (4 / 2), p.getY() - (4 / 2), 4, 4); // bug -> não redesenha com tamanho origem do
																		// ponto
		});
	}

	public void selectAll(Vectors vector) { // finalizar implementação
		vector.allPoints();
	}

	public void changeCanvasColor(Canvas canvas, GraphicsContext gc, Color canvas_color) {
		gc.setFill(canvas_color);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
}
