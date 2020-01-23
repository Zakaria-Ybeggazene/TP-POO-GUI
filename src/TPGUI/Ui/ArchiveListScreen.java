package TPGUI.Ui;

import TPGUI.Noyau.ImmoESI;
import TPGUI.Noyau.Bien;
import javafx.scene.control.ListView;
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
		ListView<Bien> ArchiveListView = new ListView<>();
		ArchiveListView.setMaxWidth(1000);
		ArchiveListView.setPrefWidth(700);
		ArchiveListView.setItems(observableArchive);
		ArchiveListView.setCellFactory((ListView<Bien> l) -> new ArchiveBienCell());
		scaffold.setCenter(ArchiveListView);
		this.setScene(new Scene(scaffold, 996, 400));
	}
}
