package draw_gui;

import java.util.LinkedList;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Draw_GUI {
	// Componente para desenho
	Canvas canvas = new Canvas(720, 512);
	// Componente para desenhar
	GraphicsContext gc = canvas.getGraphicsContext2D();
	Color colorbg = Color.WHITE;
	// Pontos
	int x = 0, y = 0;
	Color colorp = Color.GREEN;
	Color colorselect = Color.RED;
	LinkedList<Point2D> points = new LinkedList<>();
	// Ferramentas
	int tool = 0;

	public Draw_GUI(Stage stage) {
		// Painel para os componentes
		BorderPane pane = new BorderPane();
		// Menu box
		HBox menuh = new HBox();
		VBox menuv = new VBox();

		// MenuH
		Button select_draw = new Button();
		select_draw.setText("select");
		Button erase_draw = new Button();
		erase_draw.setText("erase");
		Button clear_canvas = new Button();
		clear_canvas.setText("clear");
		menuh.getChildren().addAll(select_draw, erase_draw, clear_canvas);

		clear_canvas.setOnAction(event -> {
			eraseCanvas(colorbg);
		});

		select_draw.setOnAction(event -> {
			tool = 1;
		});

		erase_draw.setOnAction(event -> {
			tool = 2;
		});

		// MenuV
		Button point_tool = new Button();
		point_tool.setText("\u2022");
		point_tool.setMinWidth(50);
		menuv.getChildren().addAll(point_tool);

		point_tool.setOnAction(event -> {
			tool = 0;
		});

		// Pane elements
		pane.setTop(menuh);
		pane.setLeft(menuv);
		pane.setCenter(canvas);

		// Trata mouseMoved
		canvas.setOnMouseMoved(event -> {
			stage.setTitle("CGPI - Draw App" + " (" + (int) event.getX() + ", " + (int) event.getY() + ")");
		});

		// Trata mouse pressed
		canvas.setOnMousePressed(event -> {
			// Botao esquerdo mouse
			if (event.getButton() == MouseButton.PRIMARY) {
				switch (tool) {
				case 0:
					x = (int) event.getX();
					y = (int) event.getY();
					points.add(new Point2D(x, y));
					render(colorp, 0);
					break;
				case 1:
					x = (int) event.getX();
					y = (int) event.getY();
					selectDraw(x, y, colorselect);
					break;
				case 2:
					x = (int) event.getX();
					y = (int) event.getY();
					eraseDraw(x, y, colorbg, colorp);
					break;
				}
			}
		});

		// Cria e insere cena
		Scene scene = new Scene(pane);

		// style
		scene.getStylesheets().add("myStyle.css");
		pane.getStyleClass().add("paneclass");
		menuh.getStyleClass().add("menutoolsh");
		menuv.getStyleClass().add("menutoolsv");
		menuv.setMinWidth(70);
		changeCanvasColor(colorbg);

		// Propriedades stage
		stage.setTitle("CGPI - Draw App");
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.show();
	}

	private void changeCanvasColor(Color color) {
		gc.setFill(color);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	private void render(Color color, int type) {
		switch (type) {
		case 0:
			points.forEach((p) -> {
				gc.setFill(color);
				gc.fillOval(p.getX() - (10 / 2), p.getY() - (10 / 2), 10, 10);
			});
			gc.setFill(color);
			break;
		}
	}

	private void eraseCanvas(Color color) {
		points.forEach((p) -> {
			gc.clearRect(p.getX() - (10 / 2), p.getY() - (10 / 2), 10, 10);
		});
		points.clear();
		changeCanvasColor(color);
	}

	private void selectDraw(int x, int y, Color color) {
		int found = 0;
		int count = 0;
		while ((found == 0) && (count < points.size())) {
			if (((int) points.get(count).getX() <= x + 3 && (int) points.get(count).getX() >= x - 3)
					&& ((int) points.get(count).getY() <= y + 3 && (int) points.get(count).getY() >= y - 3)) {
				gc.setFill(color);
				gc.fillOval(points.get(count).getX() - (10 / 2), points.get(count).getY() - (10 / 2), 10, 10);
				found = 1;
			}
			count++;
		}
	}

	private void eraseDraw(int x, int y, Color colorbackground, Color colordraw) {
		int found = 0;
		int count = 0;
		while ((found == 0) && (count < points.size())) {
			if (((int) points.get(count).getX() <= x + 3 && (int) points.get(count).getX() >= x - 3)
					&& ((int) points.get(count).getY() <= y + 3 && (int) points.get(count).getY() >= y - 3)) {
				gc.clearRect(points.get(count).getX() - (10 / 2), points.get(count).getY() - (10 / 2), 10, 10);
				points.remove(points.get(count));
				changeCanvasColor(colorbackground);
				render(colordraw, 0);
				found = 1;
			}
			count++;
		}
	}
}