package draw_gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import draw_tools.Dot_Gr;

public class Draw_GUI {
	int indicePonto = 1;

	public Draw_GUI(Stage stage) {

		// define titulo da janela
		stage.setTitle("Testa Mouse");

		// define largura e altura da janela
		stage.setWidth(500);
		stage.setHeight(500);

		// Painel para os componentes
		BorderPane pane = new BorderPane();

		// componente para desenho
		Canvas canvas = new Canvas(500, 500);

		// componente para desenhar graficos

		GraphicsContext gc;
		gc = canvas.getGraphicsContext2D();

		// Eventos de mouse
		// trata mouseMoved
		canvas.setOnMouseMoved(event -> {
			stage.setTitle("Testa Mouse (Pressione botao do Mouse):" + " (" + (int) event.getX() + ", "
					+ (int) event.getY() + ")");
		});

		// trata mousePressed
		canvas.setOnMousePressed(event -> {
			int x, y;

			if (event.getButton() == MouseButton.PRIMARY) {
				x = (int) event.getX();
				y = (int) event.getY();
				// desenha ponto na posicao clicada
				desenharPonto(gc, x, y, 6, "P" + indicePonto, Color.BLUE);
				indicePonto++;
			} else if (event.getButton() == MouseButton.SECONDARY) {
				x = (int) event.getX();
				y = (int) event.getY();
				// desenha ponto na posicao clicada
				desenharPonto(gc, x, y, 6, "(" + x + ", " + y + ")", Color.RED);
			}
		});

		// atributos do painel
		pane.setBackground(new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, Insets.EMPTY)));
		pane.setCenter(canvas); // posiciona o componente de desenho

		// cria e insere cena
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Desenha um ponto grafico
	 * 
	 * @param g
	 *            contexto grafico
	 * @param x
	 *            posicao x
	 * @param y
	 *            posicao y
	 * @param diametro
	 *            diametro do ponto
	 * @param nome
	 *            nome do ponto
	 * @param cor
	 *            cor do ponto
	 */
	public void desenharPonto(GraphicsContext g, int x, int y, int diametro, String nome, Color cor) {
		Dot_Gr p;

		// Cria um ponto
		p = new Dot_Gr(x, y, cor, nome, diametro);

		// Desenha o ponto
		p.desenharPonto(g);
	}
}
