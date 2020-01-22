package TPGUI.Control;

import TPGUI.Ui.ArchiveListScreen;
import javafx.event.*;
import javafx.scene.image.Image;
import javafx.stage.*;

public class ViewArchiveButtonController implements EventHandler<ActionEvent> {
	public void handle(ActionEvent actionEvent) {
		Stage stage = new ArchiveListScreen();
		stage.getIcons().add(new Image(("file:home.png")));
		stage.show();
	}
}
