package TPGUI.Ui;

import TPGUI.Noyau.ImmoESI;
import TPGUI.Noyau.Bien;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.scene.layout.*;

public class ArchiveListScreen extends Stage {
	public ArchiveListScreen() {
		this.setTitle("Archive");
		this.setResizable(false);
		this.setMaxWidth(996);
		this.setMaxHeight(400);
		BorderPane scaffold = new BorderPane();
		ObservableList<Bien> observableArchive = FXCollections.observableList(ImmoESI.getListArchive());
		ListView<Bien> archiveListView = new ListView<>();
		archiveListView.setMaxWidth(1000);
		archiveListView.setPrefWidth(700);
		archiveListView.setItems(observableArchive);
		archiveListView.setCellFactory((ListView<Bien> l) -> new ArchiveBienCell());
		archiveListView.setOnKeyPressed(keyEvent -> {
			if(keyEvent.getCode().equals(KeyCode.ESCAPE)) close();
		});
		scaffold.setCenter(archiveListView);
		this.setScene(new Scene(scaffold, 996, 400));
	}
}
