package draw_gui;

import draw_tools.Dot_Gr;
import draw_tools.Draw_Circle;
import draw_tools.Draw_Edge;
import draw_tools.Draw_Rectangle;
import draw_tools.Draw_Snowflake;
import draw_tools.Draw_Triangle;
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
	int indiceTriangulo = 1;
	int distancia;
	int[] ponto1 = new int[2];
	int[] ponto2 = new int[2];
	int[] ponto3 = new int[2];

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
				case 3:
					Draw_Rectangle retangulo = new Draw_Rectangle();
					desenharPonto(gc, x, y, gui_menu.sizeSpinner.getValue(), "", gui_menu.colorPicker.getValue());
					if (indiceCirculo % 2 == 0) {
						ponto2[0] = x;
						ponto2[1] = y;
						retangulo.desenharRetangulo(ponto1, ponto2, gc, gui_menu.colorPicker.getValue(),
								gui_menu.sizeSpinner.getValue());
					} else {
						ponto1[0] = x;
						ponto1[1] = y;
					}
					indiceCirculo++;
					indicePonto++;
					break;	
				case 4:
					Draw_Triangle triangulo = new Draw_Triangle();
					desenharPonto(gc, x, y, gui_menu.sizeSpinner.getValue(), "", gui_menu.colorPicker.getValue());					
					if (indiceTriangulo % 3 == 0) {
						ponto3[0] = x;
						ponto3[1] = y;
						triangulo.desenharTriangulo(ponto1, ponto2, ponto3, gc, gui_menu.colorPicker.getValue(),
								gui_menu.sizeSpinner.getValue());
					}
					else if (indiceTriangulo % 3 == 1) {
						ponto2[0] = x;
						ponto2[1] = y;
					}
					else {
						ponto1[0] = x;
						ponto1[1] = y;
					}
					indiceTriangulo++;
					indicePonto++;
					break;
				case 5:// snowflake
					Draw_Snowflake fractal = new Draw_Snowflake();
					fractal.run(gc, canvas, gui_menu.sizeSpinner.getValue());
					/*int width = (int) canvas.getHeight();
					int height = (int) canvas.getWidth();
					double x1 = width / 2;
					double y1 = height / 2;
					int level = 5;
					double length = 500;
					double direction = 0;
					
					for (int i = 0; i < 3; i++) {
						Draw_Snowflake fractal = new Draw_Snowflake();
						fractal.drawFractal(x1, y1, direction, length, level);
						x1 = x1 + length * Math.cos(direction);
						y1 = y1 + length * Math.sin(direction);
						direction -= (Math.toRadians(120));
					}*/
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
