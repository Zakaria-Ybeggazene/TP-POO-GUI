package TPGUI.Ui;

import java.time.LocalDateTime;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MessageCell extends ListCell<String> {

	protected void updateItem(String message, boolean empty) {
		super.updateItem(message, empty);
		if (message != null) {
			// Building MessageCell (ListCell)
			BorderPane tile = new BorderPane();
			tile.setPadding(new Insets(10));
			BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5), new Insets(5));
			setBackground(new Background(backgroundFill));
			tile.setBorder(new Border(new BorderStroke(Color.LIGHTGREY, BorderStrokeStyle.SOLID, new CornerRadii(5),
					BorderWidths.DEFAULT)));
			Label intro = createMessage("Message :");
			Label messageDuClient = createMessage(message);
			VBox layer = new VBox(intro, messageDuClient);
			tile.setCenter(layer);
			setGraphic(tile);
		}
	}

	private Label createMessage(String s) {
		Label etiquette = new Label(s);
		etiquette.setAlignment(Pos.CENTER);
		etiquette.setFont(Font.font("Verdana", 16));
		etiquette.setLineSpacing(3);
		return etiquette;
	}
}
