package draw_tools;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Button;

public class Button_Factory {
	public LinkedList<Button> createButtons(List<String> buttons_names) {
		LinkedList<Button> buttons = new LinkedList<>();
		buttons_names.forEach((btn) -> {
			buttons.add(new Button(btn));
		});
		return buttons;
	}
}
