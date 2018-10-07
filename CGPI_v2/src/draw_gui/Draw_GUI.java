package draw_gui;

import java.util.Arrays;
import java.util.LinkedList;

import draw_tools.Draw_Util;
import draw_tools.Draw_Points;
import draw_vectors.Vector_Util;
import draw_vectors.Vector_Point;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Draw_GUI {
	// Componentes para desenho
	public Canvas canvas = new Canvas(720, 512);
	public Color colorbg = Color.WHITE;
	public GraphicsContext gc = canvas.getGraphicsContext2D();

	// Propriedades desenho
	public Draw_Points points_graphics = new Draw_Points();
	Draw_Util canvas_graphics = new Draw_Util();
	int tool = 0; // tool selecionada para desenho
	int toolc = 0; // tool canvas selecionada

	// Vetores
	int sizep = points_graphics.vector_tools.size_point;
	Color colorp = points_graphics.vector_tools.color_point;
	int x = 0, y = 0;
	int[] ponto1 = new int[2];
	int[] ponto2 = new int[2];

	public Draw_GUI(Stage stage) {
		// Componentes
		BorderPane pane = new BorderPane();
		HBox menuh = new HBox();
		VBox menuv = new VBox();
		Button_Factory tools_buttons = new Button_Factory();

		// Pane elements
		pane.setTop(menuh);
		pane.setLeft(menuv);
		pane.setCenter(canvas);

		// MenuH
		LinkedList<Button> toolscanvas = tools_buttons.createButtons(Arrays.asList("selecionar", "deletar", "limpar"));
		toolscanvas.forEach((btn) -> {
			menuh.getChildren().add(btn); // adiciona botões no componente HBox
		});

		final Spinner<Integer> sizeSpinner = new Spinner<Integer>(); // especifica tamanho dos pontos
		ColorPicker colorPicker = new ColorPicker(); // especifica cor dos pontos
		sizeSpinner.setId("sizeSpinner");
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 8, sizep);
		sizeSpinner.setValueFactory(valueFactory);
		colorPicker.setValue(colorp);
		menuh.getChildren().addAll(sizeSpinner, colorPicker);

		colorPicker.setOnAction(event -> {
			colorp = colorPicker.getValue();
			points_graphics.vector_tools.color_point = colorp;
		});

		sizeSpinner.getEditor().textProperty().addListener(event -> {
			sizep = sizeSpinner.getValue();
			points_graphics.vector_tools.size_point = sizep;
		});

		toolscanvas.get(0).setOnAction(event -> {
			toolc = 0; // selecionar
		});

		toolscanvas.get(1).setOnAction(event -> {
			toolc = 1; // deletar
		});

		toolscanvas.get(2).setOnAction(event -> {
			eraseAll(); // limpar
		});

		// MenuV
		LinkedList<Button> toolsdrawing = tools_buttons
				.createButtons(Arrays.asList("\u2022", "/", "O", "\u25B3", "\u25A1"));
		toolsdrawing.forEach((btn) -> {
			menuv.getChildren().add(btn); // adiciona botões no componente VBox
		});

		toolsdrawing.get(0).setOnAction(event -> {
			tool = 0; // ponto
		});

		// Trata mouseMoved
		canvas.setOnMouseMoved(event -> {
			stage.setTitle("CGPI - Draw App" + " (" + (int) event.getX() + ", " + (int) event.getY() + ")");
		});

		// Trata mouse pressed
		canvas.setOnMousePressed(event -> {
			x = (int) event.getX();
			y = (int) event.getY();
			// Botao esquerdo mouse
			if (event.getButton() == MouseButton.PRIMARY) {
				switch (tool) {
				// Ponto
				case 0:
					addPoint();
					break;
				// Linha
				/*
				 * case 1: ponto1[0] = x; ponto1[1] = y; ponto2[0] = x + 20; ponto2[1] = y + 20;
				 * Draw_Edge newline = new Draw_Edge();
				 * vector.lines.add(newline.desenharLinha(ponto1, ponto2)); render(); break;
				 */
				}
			}
			// Botao direito mouse
			if (event.getButton() == MouseButton.SECONDARY) {
				switch (toolc) {
				// Selecionar
				case 0:
					switch (tool) {
					// Ponto
					case 0:
						selectPoint();
						break;
					}
					/*
					 * case 1: while ((found == 0) && (count < vector.lines.size())) { int countp =
					 * 0; while ((found == 0) && (countp < vector.lines.get(count).size())) { if
					 * (((int) vector.lines.get(count).get(countp).getX() <= x + 3 && (int)
					 * vector.lines.get(count).get(countp).getX() >= x - 3) && ((int)
					 * vector.lines.get(count).get(countp).getY() <= y + 3 && (int)
					 * vector.lines.get(count).get(countp).getY() >= y - 3)) {
					 * vector.lines.get(count).forEach((p) -> { gc.setFill(color);
					 * gc.fillOval(p.getX() - (10 / 2), p.getY() - (10 / 2), 10, 10); });
					 * 
					 * found = 1; } countp++; } count++; } count++; break;
					 */
					break;
				// Deletar
				case 1:
					switch (tool) {
					// Ponto
					case 0:
						erasePoint();
						break;
					/*
					 * case 1: int countp = 0; while ((found == 0) && (countp <
					 * vector.lines.get(count).size())) { if (((int)
					 * vector.lines.get(count).get(countp).getX() <= x + 3 && (int)
					 * vector.lines.get(count).get(countp).getX() >= x - 3) && ((int)
					 * vector.lines.get(count).get(countp).getY() <= y + 3 && (int)
					 * vector.lines.get(count).get(countp).getY() >= y - 3)) {
					 * vector.lines.remove(vector.lines.get(count));
					 * changeCanvasColor(colorbackground); render(colordraw, type); found = 1; }
					 * countp++; } count++; break;
					 */
					}
					renderAll(); // refaz os desenhos
					break;
				}
			}
		});

		// Cria e insere cena
		Scene scene = new Scene(pane);

		// Style
		scene.getStylesheets().add("myStyle.css");
		pane.getStyleClass().add("paneclass");
		menuh.getStyleClass().add("menutoolsh");
		menuv.getStyleClass().add("menutoolsv");
		menuv.setMinWidth(70);
		new Draw_Util().changeCanvasColor(canvas, gc, colorbg);

		// Propriedades stage
		stage.setTitle("CGPI - Draw App");
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.show();
	}

	private void addPoint() {
		points_graphics.addElement(gc, x, y);
	}

	private void erasePoint() {
		points_graphics.eraseElement(gc, colorbg, x, y);
	}

	private void selectPoint() {
		points_graphics.selectElement(gc, x, y);
	}

	private void renderAll() {
		canvas_graphics.renderAll(this);
	}

	private void eraseAll() {
		canvas_graphics.eraseAll(this);
	}
}