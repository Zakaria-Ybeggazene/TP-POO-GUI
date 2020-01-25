package TPGUI.Control;


import TPGUI.Ui.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ViewMessagesButtonController implements EventHandler<ActionEvent> {
	public void handle(ActionEvent actionEvent) {
		Stage stage = new MessagesListScreen();
		stage.getIcons().add(new Image(("file:home.png")));
		stage.show();
	}
}
