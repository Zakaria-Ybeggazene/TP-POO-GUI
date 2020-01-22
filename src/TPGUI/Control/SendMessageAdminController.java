package TPGUI.Control;

import TPGUI.Noyau.ImmoESI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

public class SendMessageAdminController implements EventHandler<ActionEvent> {
	private TextArea message;

	public SendMessageAdminController(TextArea message) {
		this.message = message;
	}

	public void handle(ActionEvent actionEvent) {
		ImmoESI.getListMessages().add(message.getText());
		message.setText("");
	}

}
