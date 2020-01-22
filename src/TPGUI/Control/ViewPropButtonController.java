package TPGUI.Control;

import TPGUI.Ui.PropListScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ViewPropButtonController implements EventHandler<ActionEvent> {

	public void handle(ActionEvent actionEvent) {
		Stage stage = new PropListScreen();
		stage.getIcons().add(new Image(("file:home.png")));
		stage.show();
	}
}
