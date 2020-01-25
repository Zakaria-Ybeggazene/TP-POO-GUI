package TPGUI.Ui;


import TPGUI.Noyau.ImmoESI;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MessageCell extends ListCell<String> {
	private MessagesListScreen messagesListScreen;

	public MessageCell(MessagesListScreen messagesListScreen) {
		this.messagesListScreen = messagesListScreen;
	}

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
			messageDuClient.setWrapText(true);
			VBox layer = new VBox(intro, messageDuClient);
			layer.setMaxWidth(650);
			tile.setCenter(layer);
			Button removeMessage = new Button("Remove\nMessage");
			removeMessage.setAlignment(Pos.CENTER);
			removeMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
			removeMessage.setPrefSize(100, 55);
			removeMessage.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent actionEvent) {
					ImmoESI.getListMessages().remove(message);
					messagesListScreen.setScene(messagesListScreen.getNewScene());
				}
			});
			tile.setRight(removeMessage);
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
