package TPGUI.Control;

import TPGUI.Ui.AddPropScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AddPropButtonController implements EventHandler<ActionEvent> {
	
	public void handle(ActionEvent actionEvent) {
		Stage stage = new AddPropScreen();
		stage.getIcons().add(new Image(("file:home.png")));
		stage.show();
	}
}
