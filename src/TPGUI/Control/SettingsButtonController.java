package TPGUI.Control;

import TPGUI.Noyau.ImmoESI;
import TPGUI.Ui.SettingsScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SettingsButtonController implements EventHandler<ActionEvent> {
	private ImmoESI model;
	public SettingsButtonController(ImmoESI model) {
		this.model=model;
	}
	public void handle(ActionEvent actionEvent) {
		Stage stage = new SettingsScreen(model);
		stage.getIcons().add(new Image(getClass().getResourceAsStream("../../settings.png"), 40, 40, true, true));
		stage.show();
	}
}
