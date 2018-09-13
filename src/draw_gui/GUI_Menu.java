package draw_gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI_Menu {
	int ntools = 6;
	int tooloption = 0;
	Button[] tools = new Button[ntools];
	final Spinner<Integer> sizeSpinner = new Spinner<Integer>();
	ColorPicker colorPicker = new ColorPicker();
	Button clear = new Button();

	public GUI_Menu(Draw_GUI drawgui, Stage stage) {
		drawgui.menu.setBackground(new Background(new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

		// tools
		tools = createBtnTools(tools);
		tools[0].setText("\u2022"); // Dot
		tools[1].setText("/"); // Line
		tools[2].setText("o"); // Circle
		tools[3].setText("\u25A1"); // Square
		tools[4].setText("\u25B3"); // Triangle
		tools[5].setText("\u2744"); // SnowFlake

		// size
		sizeSpinner.setId("sizeSpinner");
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 8, 4);
		sizeSpinner.setValueFactory(valueFactory);

		// color
		colorPicker.setValue(Color.CORAL);

		// clean
		clear.setText("clear");
		clear.setOnAction(event -> {
			drawgui.gc.clearRect(0, 0, stage.getWidth(), stage.getHeight());
			drawgui.changeCanvasColor(drawgui.gc, drawgui.canvas, Color.WHITE);
		});

		// Escolha da Tool
		int tooloption = toolOptionListener(tools);

		// atributos do painel
		drawgui.menu.getChildren().addAll(tools[0], tools[1], tools[2], tools[3], tools[4], tools[5], colorPicker,
				sizeSpinner, clear);

	}

	private int toolOptionListener(Button[] tools) {
		for (int i = 0; i < ntools; i++) {
			String option = tools[i].getText();
			tools[i].setOnAction(event -> {
				switch (option) {
				case "\u2022": // dot
					tooloption = 0;
					break;
				case "/": // line
					tooloption = 1;
					break;
				case "o": // circle
					tooloption = 2;
					break;
				case "\u25A1": // square
					tooloption = 3;
					break;
				case "\u25B3": // triangle
					tooloption = 4;
					break;
				case "\u2744": // snowflake
					tooloption = 5;
					break;
				}
			});
		}
		return tooloption;
	}

	private Button[] createBtnTools(Button[] tools) {
		for (int i = 0; i < ntools; i++) {
			tools[i] = new Button();
		}
		return tools;
	}

}
