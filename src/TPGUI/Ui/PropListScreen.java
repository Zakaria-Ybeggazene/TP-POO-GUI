package TPGUI.Ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import TPGUI.Noyau.ImmoESI;
import TPGUI.Noyau.Proprietaire;

public class PropListScreen extends Stage {
	public PropListScreen() {
		this.setTitle("Proprietaires");
		this.setResizable(false);
		this.setMaxWidth(800);
		this.setMaxHeight(400);
		this.setX(350);
		this.setY(200);
		BorderPane scaffold = new BorderPane();
		ObservableList<Proprietaire> observableProp = FXCollections.observableList(ImmoESI.getListProprietaires());
		ListView<Proprietaire> PropListView = new ListView<>();
		PropListView.setMaxWidth(1000);
		PropListView.setPrefWidth(700);
		PropListView.setItems(observableProp);
		PropListView.setCellFactory((ListView<Proprietaire> l) -> new PropCell());
		scaffold.setCenter(PropListView);
		this.setScene(new Scene(scaffold, 800, 400));
	}
}
