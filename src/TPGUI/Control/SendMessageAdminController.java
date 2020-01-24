package TPGUI.Control;

import TPGUI.Noyau.ImmoESI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

import java.time.LocalDateTime;

public class SendMessageAdminController implements EventHandler<ActionEvent> {
	private TextArea message;

	public SendMessageAdminController(TextArea message) {
		this.message = message;
	}

	public void handle(ActionEvent actionEvent) {
		String dateString;
		LocalDateTime messageDateTime = LocalDateTime.now();
		dateString = messageDateTime.getDayOfWeek().toString() + ", " + messageDateTime.getDayOfMonth() + " "
				+ messageDateTime.getMonth().toString() + " " + messageDateTime.getYear() + " at "
				+ messageDateTime.getHour() + ":" + messageDateTime.getMinute() + ":" + messageDateTime.getSecond();
		ImmoESI.getListMessages().add(message.getText() + "\n\n\t\t\t\t" + dateString);
		message.setText("");
	}

}
