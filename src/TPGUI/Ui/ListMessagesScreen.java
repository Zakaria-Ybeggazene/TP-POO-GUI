package TPGUI.Ui;

import TPGUI.Noyau.ImmoESI;

import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.scene.layout.*;

public class ListMessagesScreen extends Stage {
	public ListMessagesScreen() {
		this.setTitle("View Messages ");
		this.setResizable(false);
		this.setMaxWidth(800);
		this.setMaxHeight(400);
		this.setX(350);
		this.setY(200);
		BorderPane scaffold = new BorderPane();
		ObservableList<String> observableMessages = FXCollections.observableList(ImmoESI.getListMessages());
		ListView<String> MessagesListView = new ListView<>();
		MessagesListView.setMaxWidth(1000);
		MessagesListView.setPrefWidth(700);
		MessagesListView.setItems(observableMessages);
		MessagesListView.setCellFactory((ListView<String> l) -> new MessageCell());
		scaffold.setCenter(MessagesListView);
		this.setScene(new Scene(scaffold, 800, 400));
	}
}
