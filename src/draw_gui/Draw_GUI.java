package draw_gui;

import draw_tools.Dot_Gr;
import draw_tools.Draw_Edge;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Draw_GUI {
	int indicePonto = 1;
	int indiceReta = 1;
	int distancia;
	int[] ponto1= new int[2];
	int[] ponto2= new int[2];

	public Draw_GUI(Stage stage) {

		// define titulo da janela
		stage.setTitle("CGPI - Drawing");

		// define menu de tools
		HBox menu = new HBox();
		menu.setBackground(new Background(new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

		Tools tool = new Tools();
		int ntools = 3;
		Button[] tools = new Button[10];
		tools = createBtnTools(tools, ntools);
		tools[0].setText("*");
		tools[1].setText("|");
		tools[2].setText("o");
		ColorPicker colorPicker = new ColorPicker();
		colorPicker.setValue(Color.CORAL);

		// attach style css
		menu.getStyleClass().add("menutools");

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

		// Escolha da Tool
		for (int i = 0; i < ntools; i++) {
			String option = tools[i].getText();
			tools[i].setOnAction(event -> tool.setTool(option));
		}

		// Eventos de mouse
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
				// dot tool
				if (tool.tooloption == 0) {
					System.out.println("teste botao ponto");
					desenharPonto(gc, x, y, 6, "P" + indicePonto, colorPicker.getValue());
					indicePonto++;
				}
				// edge tool
				if (tool.tooloption == 1) {
					
					Draw_Edge linha = new Draw_Edge();
					
					System.out.println("teste botao linha");
					desenharPonto(gc, x, y, 6, "P" + indicePonto, colorPicker.getValue());
					if(indiceReta%2 == 0) {
						ponto2[0] = x;
						ponto2[1] = y;
						System.out.println("p2");
						linha.desenharLinha(ponto1,ponto2,gc);
					}
					else {
						ponto1[0] = x;
						ponto1[1] = y;
						System.out.println("p1");
					}
					indiceReta++;
					indicePonto++;
				}
				// circle tool
				if (tool.tooloption == 2) {
					System.out.println("teste botao circulo");
					desenharPonto(gc, x, y, 6, "P" + indicePonto, colorPicker.getValue());
					indicePonto++;
				}
			}
		});

		// atributos do painel
		menu.getChildren().addAll(tools[0], tools[1], tools[2], colorPicker);
		pane.setTop(menu);
		pane.setCenter(canvas); // posiciona o componente de desenho

		// cria e insere cena
		Scene scene = new Scene(pane);
		scene.getStylesheets().add("myStyle.css");
		stage.setScene(scene);
		stage.show();
	}

	private Button[] createBtnTools(Button[] tools, int number) {
		for (int i = 0; i < number; i++) {
			tools[i] = new Button();
		}
		return tools;
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
