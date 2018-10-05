package draw_gui;

import draw_tools.Dot_Gr;
import draw_tools.Draw_Circle;
import draw_tools.Draw_Edge;
import draw_tools.Draw_Polygon;
import draw_tools.Draw_Rectangle;
import draw_tools.Draw_Snowflake;
import draw_tools.Draw_Triangle;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
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
	// canvas temporario para elastico
	Canvas canvastemp = new Canvas(1200, 612);
	// componente para desenho temporario
	GraphicsContext gctemp;

	int indicePoligono = 0;
	int indiceCirculo = 1;
	int indiceTriangulo = 1;
	int distancia;
	int[] ponto1 = new int[2];
	int[] ponto2 = new int[2];
	int[] ponto3 = new int[2];
	boolean primeiraVez = true;
	boolean fimElastico = true;
	int x = 0, y = 0, xant = 0, yant = 0;
	int[][] pontos = new int[20][20];
	

	public Draw_GUI(Stage stage) {

		stage.setTitle("CGPI - Drawing");
		GUI_Menu gui_menu = new GUI_Menu(this, stage); // define menu de tools
		stage.setMaximized(true);

		BorderPane pane = new BorderPane(); // Painel para os componentes

		gc = canvas.getGraphicsContext2D(); // canvas
		changeCanvasColor(gc, canvas, Color.WHITE);

		// desenha canvas temporario
		gctemp = canvastemp.getGraphicsContext2D(); // canvas
		changeCanvasColor(gctemp, canvastemp, Color.WHITE);

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
			primeiraVez = true;
			fimElastico = true;
			int stop = 0;
			Draw_Polygon poligono = new Draw_Polygon();

			// Botao Esquerdo Mouse
			if (event.getButton() == MouseButton.PRIMARY) {
				x = (int) event.getX();
				y = (int) event.getY();
				// trata acoes das tools
				switch (gui_menu.tooloption) {
				case 0:// dot
					desenharPonto(gc, x, y, gui_menu.sizeSpinner.getValue(), "", gui_menu.colorPicker.getValue());
					break;
				case 1:// edge
					if (primeiraVez == true) {
						ponto1[0] = x;
						ponto1[1] = y;
						desenharPonto(gc, ponto1[0], ponto1[1], gui_menu.sizeSpinner.getValue(), "",
								gui_menu.colorPicker.getValue());
						copyCanvas();
						pane.setCenter(canvastemp);
						primeiraVez = false;
					}
					break;
				case 2:// circle
					if (primeiraVez == true) {
						ponto1[0] = x;
						ponto1[1] = y;
						desenharPonto(gc, ponto1[0], ponto1[1], gui_menu.sizeSpinner.getValue(), "",
								gui_menu.colorPicker.getValue());
						copyCanvas();
						pane.setCenter(canvastemp);
						primeiraVez = false;
					}
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
					break;
				case 4:
					Draw_Triangle triangulo = new Draw_Triangle();
					Draw_Edge linhaTri = new Draw_Edge();
					desenharPonto(gc, x, y, gui_menu.sizeSpinner.getValue(), "", gui_menu.colorPicker.getValue());
					if (indiceTriangulo % 3 == 0) {
						ponto3[0] = x;
						ponto3[1] = y;
						triangulo.desenharTriangulo(ponto1, ponto2, ponto3, gc, gui_menu.colorPicker.getValue(),
								gui_menu.sizeSpinner.getValue());
					} else if (indiceTriangulo % 3 == 1) {
						ponto2[0] = x;
						ponto2[1] = y;

					} else {
						ponto1[0] = x;
						ponto1[1] = y;
						linhaTri.desenharLinha(ponto1, ponto2, gc, gui_menu.colorPicker.getValue(),
								gui_menu.sizeSpinner.getValue());
					}
					indiceTriangulo++;
					break;
				case 5: // polygon
					desenharPonto(gc, x, y, gui_menu.sizeSpinner.getValue(), "", gui_menu.colorPicker.getValue());
					pontos[indicePoligono][0] = x;
					pontos[indicePoligono][1] = y;
					if (indicePoligono > 0) {
						ponto1[0] = pontos[indicePoligono - 1][0];
						ponto1[1] = pontos[indicePoligono - 1][1];
						ponto2[0] = pontos[indicePoligono][0];
						ponto2[1] = pontos[indicePoligono][1];
						Draw_Edge linhaPol = new Draw_Edge();
						linhaPol.desenharLinha(ponto1, ponto2, gc, gui_menu.colorPicker.getValue(),
								gui_menu.sizeSpinner.getValue());
					}
					indicePoligono++;
					break;
				case 6:
					desenharPonto(gc, x, y, gui_menu.sizeSpinner.getValue(), "", gui_menu.colorPicker.getValue());
					pontos[indicePoligono][0] = x;
					pontos[indicePoligono][1] = y;
					if (indicePoligono > 0) {
						ponto1[0] = pontos[indicePoligono - 1][0];
						ponto1[1] = pontos[indicePoligono - 1][1];
						ponto2[0] = pontos[indicePoligono][0];
						ponto2[1] = pontos[indicePoligono][1];
						Draw_Edge linhaPol = new Draw_Edge();
						linhaPol.desenharLinha(ponto1, ponto2, gc, gui_menu.colorPicker.getValue(),
								gui_menu.sizeSpinner.getValue());
					}
					indicePoligono++;
					break;
				case 7:// snowflake
					Draw_Snowflake fractal = new Draw_Snowflake();
					fractal.run(gc, canvas, gui_menu.sizeSpinner.getValue());
					break;
				}
			}

			// Botao Direito Mouse
			if (event.getButton() == MouseButton.SECONDARY) {
				switch (gui_menu.tooloption) {
				case 5:
					x = (int) event.getX();
					y = (int) event.getY();
					if (indicePoligono == 0) {
						desenharPonto(gc, x, y, gui_menu.sizeSpinner.getValue(), "", gui_menu.colorPicker.getValue());
					} else {
						desenharPonto(gc, x, y, gui_menu.sizeSpinner.getValue(), "", gui_menu.colorPicker.getValue());
						pontos[indicePoligono][0] = x;
						pontos[indicePoligono][1] = y;
						indicePoligono++;
						poligono.desenharPoligonoFechado(indicePoligono, pontos, gc, gui_menu.colorPicker.getValue(),
								gui_menu.sizeSpinner.getValue());
					}
					break;
				case 6:
					x = (int) event.getX();
					y = (int) event.getY();
					if (indicePoligono == 0) {
						desenharPonto(gc, x, y, gui_menu.sizeSpinner.getValue(), "", gui_menu.colorPicker.getValue());
					} else {
						desenharPonto(gc, x, y, gui_menu.sizeSpinner.getValue(), "", gui_menu.colorPicker.getValue());
						pontos[indicePoligono][0] = x;
						pontos[indicePoligono][1] = y;
						indicePoligono++;
						poligono.desenharPoligonoAberto(indicePoligono, pontos, gc, gui_menu.colorPicker.getValue(),
								gui_menu.sizeSpinner.getValue());
					}
					break;

				}
				indicePoligono = 0;
			}
		});

		// trata MouseDragged
		canvas.setOnMouseDragged(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				switch (gui_menu.tooloption) {
				case 1:// edge
					Draw_Edge linha = new Draw_Edge();
					if (fimElastico == false) {
						xant = x;
						yant = y;
						ponto2[0] = xant;
						ponto2[1] = yant;
						// "apaga" reta anterior
						linha.desenharLinha(ponto1, ponto2, gctemp, Color.WHITE, gui_menu.sizeSpinner.getValue() * 2);
					}
					desenharPonto(gc, ponto1[0], ponto1[1], gui_menu.sizeSpinner.getValue(), "",
							gui_menu.colorPicker.getValue());
					x = (int) event.getX();
					y = (int) event.getY();
					ponto2[0] = x;
					ponto2[1] = y;
					linha.desenharLinha(ponto1, ponto2, gctemp, gui_menu.colorPicker.getValue(),
							gui_menu.sizeSpinner.getValue());
					fimElastico = false;
					break;
				case 2:// circle
					if (fimElastico == false) {
						xant = x;
						yant = y;
						ponto2[0] = xant;
						ponto2[1] = yant;
						Draw_Circle circulo = new Draw_Circle();
						// "apaga" reta anterior
						circulo.desenharCirculo(ponto1, ponto2, gctemp, Color.WHITE,
								gui_menu.sizeSpinner.getValue() * 2);
					}
					desenharPonto(gc, ponto1[0], ponto1[1], gui_menu.sizeSpinner.getValue(), "",
							gui_menu.colorPicker.getValue());
					x = (int) event.getX();
					y = (int) event.getY();
					ponto2[0] = x;
					ponto2[1] = y;
					Draw_Circle circulo = new Draw_Circle();
					circulo.desenharCirculo(ponto1, ponto2, gctemp, gui_menu.colorPicker.getValue(),
							gui_menu.sizeSpinner.getValue());
					fimElastico = false;
					break;
				}
			}
		});

		// trata MouseReleased
		canvas.setOnMouseReleased(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				switch (gui_menu.tooloption) {
				case 1:// edge
					if (fimElastico == false) {
						desenharPonto(gc, ponto1[0], ponto1[1], gui_menu.sizeSpinner.getValue(), "",
								gui_menu.colorPicker.getValue());
						Draw_Edge linha = new Draw_Edge();
						linha.desenharLinha(ponto1, ponto2, gc, gui_menu.colorPicker.getValue(),
								gui_menu.sizeSpinner.getValue());
						desenharPonto(gc, ponto2[0], ponto2[1], gui_menu.sizeSpinner.getValue(), "",
								gui_menu.colorPicker.getValue());
						pane.setCenter(canvas);
						fimElastico = true;
						primeiraVez = true;
					}
					break;
				case 2:// circle
					if (fimElastico == false) {
						Draw_Circle circulo = new Draw_Circle();
						circulo.desenharCirculo(ponto1, ponto2, gc, gui_menu.colorPicker.getValue(),
								gui_menu.sizeSpinner.getValue());
						pane.setCenter(canvas);
						fimElastico = true;
						primeiraVez = true;
					}
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

	private void copyCanvas() {
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		WritableImage image = canvas.snapshot(params, null);
		canvastemp.getGraphicsContext2D().drawImage(image, 0, 0);
	}

	public void desenharPonto(GraphicsContext g, int x, int y, int diametro, String nome, Color cor) {
		Dot_Gr p;

		// Cria um ponto
		p = new Dot_Gr(x, y, cor, nome, diametro);

		// Desenha o ponto
		p.desenharPonto(g);
	}

}
