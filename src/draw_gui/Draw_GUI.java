package draw_gui;

import java.awt.FileDialog;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.xml.transform.TransformerException;

import draw_tools.Draw_All;
import draw_tools.Draw_Circles;
import draw_tools.Draw_Export;
import draw_tools.Draw_Import;
import draw_tools.Draw_Lines;
import draw_tools.Draw_Points;
import javafx.geometry.Point2D;
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
	public String file_path = "C:\\";

	// Propriedades desenho
	public Draw_Points points_graphics = new Draw_Points();
	public Draw_Lines lines_graphics = new Draw_Lines();
	public Draw_Circles circles_graphics = new Draw_Circles();
	Draw_All canvas_graphics = new Draw_All();
	int tool = 0; // tool selecionada para desenho
	int toolc = 0; // tool canvas selecionada
	boolean primeiraVez = true;
	boolean fimElastico = true;

	// Vetores
	int sizep = 4;
	Color colorp = Color.GREEN;
	int x1 = 0, y1 = 0;
	int x2 = 0, y2 = 0;
	int x = 0, y = 0;
	double raio = 0;

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
		LinkedList<Button> toolscanvas = tools_buttons
				.createButtons(Arrays.asList("select", "delete", "clear", "clip", "import", "export"));
		toolscanvas.forEach((btn) -> {
			menuh.getChildren().add(btn); // adiciona botões no componente HBox
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

		toolscanvas.get(3).setOnAction(event -> {
			// clip
		});

		toolscanvas.get(4).setOnAction(event -> {
			Draw_Import importxml = new Draw_Import(); // import
			eraseAll();
			FileDialog fd = new FileDialog(new JFrame());
			fd.setVisible(true);
			fd.setMode(FileDialog.LOAD);
			File[] f = fd.getFiles();
			if (f.length > 0) {
				file_path = fd.getFiles()[0].getAbsolutePath();
			}
			importxml.xmlFilePath = file_path;
			importxml.importXML(); // le o arquivo xml
			points_graphics.vector.points = importxml.getPoints();
			lines_graphics.vector.lines = importxml.getLines();
			changePointSettings();
			renderAll();
		});

		toolscanvas.get(5).setOnAction(event -> {
			Draw_Export exportxml = new Draw_Export(); // export
			FileDialog fd = new FileDialog(new JFrame());
			fd.setVisible(true);
			fd.setMode(FileDialog.SAVE);
			file_path = fd.getFiles()[0].getAbsolutePath();
			exportxml.xmlFilePath = file_path;
			try {
				exportxml.exportXML(points_graphics.vector.points, lines_graphics.vector.lines);
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		});

		// MenuV
		LinkedList<Button> toolsdrawing = tools_buttons
				.createButtons(Arrays.asList("point", "line", "circle", "square", "triangle", "polygon", "snowflake"));
		toolsdrawing.forEach((btn) -> {
			menuv.getChildren().add(btn); // adiciona botões no componente VBox
		});

		final Spinner<Integer> sizeSpinner = new Spinner<Integer>(); // especifica tamanho dos pontos
		ColorPicker colorPicker = new ColorPicker(); // especifica cor dos pontos
		sizeSpinner.setId("sizeSpinner");
		colorPicker.setId("colorPicker");
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 8, sizep);
		sizeSpinner.setValueFactory(valueFactory);
		colorPicker.setValue(colorp);
		menuv.getChildren().addAll(sizeSpinner, colorPicker);

		colorPicker.setOnAction(event -> {
			colorp = colorPicker.getValue();
		});

		sizeSpinner.getEditor().textProperty().addListener(event -> {
			sizep = sizeSpinner.getValue();
		});

		toolsdrawing.get(0).setOnAction(event -> {
			tool = 0; // ponto
		});

		toolsdrawing.get(1).setOnAction(event -> {
			tool = 1; // linha
		});

		toolsdrawing.get(2).setOnAction(event -> {
			tool = 2; // circulo
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
					addPoint(x, y);
					break;
				// Linha
				case 1:
					if (primeiraVez == true) {
						x1 = x;
						y1 = y;
						primeiraVez = false;
					}
					break;
				// Circulo
				case 2:
					if (primeiraVez == true) {
						x1 = x;
						y1 = y;
						primeiraVez = false;
					}
					break;
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
						selectPoint(x, y);
						break;
					case 1:
						selectLine(x, y);
						break;
					}
					break;
				// Deletar
				case 1:
					switch (tool) {
					// Ponto
					case 0:
						erasePoint(x, y);
						break;
					case 1:
						eraseLine(x, y);
						break;
					}
					renderAll(); // refaz os desenhos
					break;
				}
			}
		});

		// Trata mouse dragged
		canvas.setOnMouseDragged(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				switch (tool) {
				// Linha
				case 1:
					if (fimElastico == false) {
						eraseLastLine();
						renderAll(); // refaz os desenhos
					}
					x = (int) event.getX();
					y = (int) event.getY();
					addLine(x1, y1, x, y);
					fimElastico = false;
					break;
				// Circulo
				case 2:
					if (fimElastico == false) {
						eraseLastCircle();
						renderAll();
					}
					x = (int) event.getX();
					y = (int) event.getY();
					addCircle(x1, y1, x, y);
					fimElastico = false;
					break;
				}
			}
		});

		// Trata mouse released
		canvas.setOnMouseReleased(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				switch (tool) {
				// Linha
				case 1:
					if (fimElastico == false) {
						eraseLastLine();
						addLine(x1, y1, x, y);
						fimElastico = true;
						primeiraVez = true;
					}
					break;
				// Circulo
				case 2:
					if (fimElastico == false) {
						addCircle(x1, y1, x, y);
						fimElastico = true;
						primeiraVez = true;
					}
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
		new Draw_All().changeCanvasColor(canvas, gc, colorbg);

		// Propriedades stage
		stage.setTitle("CGPI - Draw App");
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.show();
	}

	private void addPoint(int px, int py) {
		LinkedList<Point2D> points = new LinkedList<Point2D>();
		points.add(new Point2D(px, py));
		changePointSettings();
		points_graphics.addElement(gc, points);
	}

	private void erasePoint(int px, int py) {
		LinkedList<Point2D> points = new LinkedList<Point2D>();
		points.add(new Point2D(px, py));
		points_graphics.eraseElement(gc, colorbg, points);
	}

	private void selectPoint(int px, int py) {
		changePointSettings();
		points_graphics.selectElement(gc, px, py);
	}

	private void addLine(int px1, int py1, int px2, int py2) {
		LinkedList<Point2D> points = new LinkedList<Point2D>();
		points.add(new Point2D(px1, py1));
		points.add(new Point2D(px2, py2));
		changeLineSettings();
		lines_graphics.addElement(gc, points);
	}

	private void eraseLine(int px, int py) {
		LinkedList<Point2D> points = new LinkedList<Point2D>();
		points.add(new Point2D(px, py));
		lines_graphics.eraseElement(gc, colorbg, points);
	}

	private void eraseLastLine() {
		lines_graphics.eraseLastElement(gc, colorbg);
	}

	private void selectLine(int px, int py) {
		changeLineSettings();
		lines_graphics.selectElement(gc, px, py);
	}

	private void addCircle(int xf, int yf, int xm, int ym) {
		LinkedList<Point2D> points = new LinkedList<Point2D>();
		points.add(new Point2D(xf, yf));
		points.add(new Point2D(xm, ym));
		changeCircleSettings();
		circles_graphics.addElement(gc, points);
	}

	private void eraseCircle() {

	}

	private void eraseLastCircle() {
		circles_graphics.eraseLastElement(gc, colorbg);
	}

	private void selectCircle() {

	}

	private void changePointSettings() {
		points_graphics.color = colorp;
		points_graphics.size = sizep;
	}

	private void changeLineSettings() {
		lines_graphics.color = colorp;
		lines_graphics.size = sizep;
	}

	private void changeCircleSettings() {
		circles_graphics.color = colorp;
		circles_graphics.size = sizep;
	}

	private void renderAll() {
		canvas_graphics.renderAll(this);
	}

	private void eraseAll() {
		canvas_graphics.eraseAll(this);
	}
}