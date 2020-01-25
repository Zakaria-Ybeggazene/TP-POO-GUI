package TPGUI.Ui;

import TPGUI.Noyau.ImmoESI;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.scene.layout.*;

public class MessagesListScreen extends Stage {
	public MessagesListScreen() {
		this.setTitle("View Messages ");
		this.setResizable(false);
		this.setMaxWidth(800);
		this.setMaxHeight(400);
		this.setScene(getNewScene());
	}

	public Scene getNewScene() {
		BorderPane scaffold = new BorderPane();
		ObservableList<String> observableMessages = FXCollections.observableList(ImmoESI.getListMessages());
		ListView<String> messagesListView = new ListView<>();
		messagesListView.setMaxWidth(1000);
		messagesListView.setPrefWidth(700);
		messagesListView.setItems(observableMessages);
		messagesListView.setCellFactory((ListView<String> l) -> new MessageCell(this));
		messagesListView.setOnKeyPressed(keyEvent -> {
			if(keyEvent.getCode().equals(KeyCode.ESCAPE)) close();
		});
		scaffold.setCenter(messagesListView);
		return new Scene(scaffold, 800, 400);
	}
}
