package TPGUI.Control;

import TPGUI.Ui.AddPropScreen;
import TPGUI.Ui.SetBienScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AddPropButtonController implements EventHandler<ActionEvent> {
	private SetBienScreen bienScreen;
	private String typeBienString;

	public AddPropButtonController(SetBienScreen bienScreen, String typeBienString) {
		this.bienScreen = bienScreen;
		this.typeBienString = typeBienString;
	}
	public void handle(ActionEvent actionEvent) {
		Stage stage = new AddPropScreen(bienScreen, typeBienString);
		stage.getIcons().add(new Image(("file:home.png")));
		stage.show();
	}
}
