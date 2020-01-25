package TPGUI.Ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
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
		ListView<Proprietaire> propListView = new ListView<>();
		propListView.setMaxWidth(1000);
		propListView.setPrefWidth(700);
		propListView.setItems(observableProp);
		propListView.setCellFactory((ListView<Proprietaire> l) -> new PropCell());
		propListView.setOnKeyPressed(keyEvent -> {
			if(keyEvent.getCode().equals(KeyCode.ESCAPE)) close();
		});
		scaffold.setCenter(propListView);
		this.setScene(new Scene(scaffold, 800, 400));
	}
}
