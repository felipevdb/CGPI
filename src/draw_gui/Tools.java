package draw_gui;

public class Tools {
	int tooloption = 0;

	public void setTool(String option) {
		switch (option) {
		case "*":
			tooloption = 0;
			break;
		case "|":
			tooloption = 1;
			break;
		case "o":
			tooloption = 2;
			break;
		}
	}

}
