package draw_gui;

import draw_tools.Dot_Gr;
import draw_tools.Draw_Circle;
import draw_tools.Draw_Edge;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Draw_GUI {
	// menu box
	HBox menu = new HBox();
	// componente para desenho
	Canvas canvas = new Canvas(1200, 612);
	// componente para desenhar graficos
	GraphicsContext gc;
	int indicePonto = 1;
	int indiceReta = 1;
	int indiceCirculo = 1;
	int distancia;
	int[] ponto1 = new int[2];
	int[] ponto2 = new int[2];

	public Draw_GUI(Stage stage) {

		stage.setTitle("CGPI - Drawing");
		GUI_Menu gui_menu = new GUI_Menu(this, stage); // define menu de tools
		stage.setMaximized(true);

		BorderPane pane = new BorderPane(); // Painel para os componentes

		gc = canvas.getGraphicsContext2D(); // canvas
		changeCanvasColor(gc, canvas, Color.WHITE);

		menu.getStyleClass().add("menutools"); // attach style css
		pane.getStyleClass().add("paneclass");
		pane.setTop(menu);
		pane.setCenter(canvas); // posiciona o componente de desenho

		// trata mouseMoved
		canvas.setOnMouseMoved(event -> {
			stage.setTitle("CGPI - Drawing:" + " (" + (int) event.getX() + ", " + (int) event.getY() + ")");
		});

		// trata mousePressed
		canvas.setOnMousePressed(event -> {
			int x, y;

			if (event.getButton() == MouseButton.PRIMARY) {
				x = (int) event.getX();
				y = (int) event.getY();
				// trata acoes das tools
				switch (gui_menu.tooloption) {
				case 0:// dot
					desenharPonto(gc, x, y, gui_menu.sizeSpinner.getValue(), "", gui_menu.colorPicker.getValue());
					indicePonto++;
					break;
				case 1:// edge
					Draw_Edge linha = new Draw_Edge();
					desenharPonto(gc, x, y, gui_menu.sizeSpinner.getValue(), "", gui_menu.colorPicker.getValue());
					if (indiceReta % 2 == 0) { // segundo ponto
						ponto2[0] = x;
						ponto2[1] = y;
						linha.desenharLinha(ponto1, ponto2, gc, gui_menu.colorPicker.getValue(),
								gui_menu.sizeSpinner.getValue());
					} else {
						ponto1[0] = x;
						ponto1[1] = y;
					}
					indiceReta++;
					indicePonto++;
					break;
				case 2:// circle
					Draw_Circle circulo = new Draw_Circle();
					desenharPonto(gc, x, y, gui_menu.sizeSpinner.getValue(), "", gui_menu.colorPicker.getValue());
					if (indiceCirculo % 2 == 0) {
						ponto2[0] = x;
						ponto2[1] = y;
						circulo.desenharCirculo(ponto1, ponto2, gc, gui_menu.colorPicker.getValue(),
								gui_menu.sizeSpinner.getValue());
					} else {
						ponto1[0] = x;
						ponto1[1] = y;
					}
					indiceCirculo++;
					indicePonto++;
					break;
				}
			}
		});

		// cria e insere cena
		Scene scene = new Scene(pane);
		scene.getStylesheets().add("myStyle.css");
		stage.setScene(scene);
		stage.show();
	}

	public void changeCanvasColor(GraphicsContext gc, Canvas canvas, Color color) {
		gc.setFill(color);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	public void desenharPonto(GraphicsContext g, int x, int y, int diametro, String nome, Color cor) {
		Dot_Gr p;

		// Cria um ponto
		p = new Dot_Gr(x, y, cor, nome, diametro);

		// Desenha o ponto
		p.desenharPonto(g);
	}

}
