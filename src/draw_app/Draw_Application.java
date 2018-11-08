package draw_app;

import draw_gui.Draw_GUI;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Bruno/Felipe/Gabriel/Guilherme
 */

public class Draw_Application extends Application{
     public static void main (String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
    	new Draw_GUI (stage);
    }
}

