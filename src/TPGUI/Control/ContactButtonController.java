package TPGUI.Control;

import TPGUI.Noyau.Bien;
import TPGUI.Ui.ContactScreen;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;

public class ContactButtonController implements EventHandler<ActionEvent> {
	private Bien bien;

	public ContactButtonController(Bien b) {
		this.bien = b;
	}

	public void handle(ActionEvent actionEvent) {
		ContactScreen stage = new ContactScreen(bien);
		stage.getIcons().add(new Image(("file:home.png")));
		stage.show();
	}

}
