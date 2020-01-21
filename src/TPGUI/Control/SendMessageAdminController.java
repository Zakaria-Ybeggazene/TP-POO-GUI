package TPGUI.Control;


import TPGUI.Noyau.Bien;
import TPGUI.Noyau.ImmoESI;
import TPGUI.Ui.DetailsScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;


public class SendMessageAdminController implements EventHandler<ActionEvent> {
	private TextArea message;
	
	public SendMessageAdminController(TextArea message) {
		this.message=message;
	}
	
	public void handle(ActionEvent actionEvent) {
		ImmoESI.getListMessages().add(message.getText());
		message.setText("");
		ImmoESI.afficherListMessages();
	}

}
