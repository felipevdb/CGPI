package draw_gui;

public class Tools {
	int tooloption = 0;

	public void setTool(String option) {
		switch (option) {
		case "*": //dot
			tooloption = 0;
			break;
		case "|": //line
			tooloption = 1;
			break;
		case "o": //circle
			tooloption = 2;
			break;
		case "\u25A1": //square
			tooloption = 3;
			break;
		case "\u25B3": //triangle
			tooloption = 4;
			break;
		
		}
	}

}
