package TPGUI.Ui;


import TPGUI.Noyau.ImmoESI;
import TPGUI.Noyau.Proprietaire;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Set;

public class AddPropScreen extends Stage {

	public AddPropScreen(SetBienScreen bienScreen, String typeBienString) {
		this.setTitle("Add proprietaire");
		this.setResizable(false);
		this.setMaxWidth(800);
		this.setMaxHeight(400);
		this.setX(350);
		this.setY(200);
		BorderPane scaffold = new BorderPane();
		scaffold.setPadding(new Insets(50));
		this.setScene(new Scene(scaffold, 800, 400));
		BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5), new Insets(5));
		scaffold.setBackground(new Background(backgroundFill));
		scaffold.setBorder(new Border(
				new BorderStroke(Color.LIGHTGREY, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
		Label nom = createMessage("Nom*      :");
		Label prenom = createMessage("Prenom*  :");
		Label adresse = createMessage("Adresse*  :");
		Label tel = createMessage("Tel*         :");
		Label mail = createMessage("E-mail      :");
		TextField champsNom = new TextField("");
		champsNom.setPrefWidth(180);
		champsNom.setPrefHeight(30);
		TextField champsPrenom = new TextField("");
		champsPrenom.setPrefWidth(180);
		champsPrenom.setPrefHeight(30);
		TextField champsAdr = new TextField("");
		champsAdr.setPrefWidth(520);
		TextField champsTel = new TextField("");
		champsTel.setPrefWidth(180);
		champsTel.setPrefHeight(30);
		TextField champsMail = new TextField("");
		champsMail.setPrefWidth(180);
		champsMail.setPrefHeight(30);
		HBox fullname = new HBox(nom, champsNom);
		fullname.setSpacing(13);
		HBox firstName = new HBox(prenom, champsPrenom);
		firstName.setSpacing(12);
		HBox name = new HBox(fullname, firstName);
		name.setSpacing(60);
		name.setPadding(new Insets(0, 30, 0, 30));
		HBox adress = new HBox(adresse, champsAdr);
		adress.setPadding(new Insets(0, 30, 0, 30));
		adress.setSpacing(10);
		HBox email = new HBox(mail, champsMail);
		email.setSpacing(11);
		HBox number = new HBox(tel, champsTel);
		number.setSpacing(12);
		HBox coordonnees = new HBox(number, email);
		coordonnees.setPadding(new Insets(0, 30, 0, 30));
		coordonnees.setSpacing(60);
		Button addPropButton = new Button("add");
		addPropButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		addPropButton.setPrefSize(70, 40);
		HBox btnLayout = new HBox(addPropButton);
		btnLayout.setAlignment(Pos.CENTER);
		VBox layout = new VBox(name, adress, coordonnees, btnLayout);
		layout.setSpacing(30);
		this.getScene().setOnKeyPressed(keyEvent -> {
			if (keyEvent.getCode().equals(KeyCode.ENTER)) {
			TextField[] tab = new TextField[5];
			tab[0] = champsNom;
			tab[1] = champsPrenom;
			tab[2] = champsAdr;
			tab[3] = champsTel;
			tab[4] = champsMail;
			addPropGraphic(tab, layout, bienScreen, typeBienString);}
		});
		addPropButton.setOnAction(actionEvent -> {
			TextField[] tab = new TextField[5];
			tab[0] = champsNom;
			tab[1] = champsPrenom;
			tab[2] = champsAdr;
			tab[3] = champsTel;
			tab[4] = champsMail;
			addPropGraphic(tab, layout, bienScreen, typeBienString);
		});
		scaffold.setCenter(layout);
	}

	private void addPropGraphic(TextField[] tab, VBox layout, SetBienScreen bienScreen, String typeBienString) {
		boolean emptyField = false;
		Label champsObligatoire = createMessage("\t\t\t\t\t\tChamps obligatoire");
		champsObligatoire.setTextFill(Color.RED);
		Label propAdded = createMessage("\t\t\t\t\t\tAdded successfully");
		propAdded.setTextFill(Color.GREEN);
		Service<Void> service = new ProcessService();
		if (!service.isRunning()) {
			service.start();
		}
		if (tab[0].getText().equals("")) {
			emptyField = true;
			if (layout.getChildren().size() > 4)
				layout.getChildren().remove(4);
			layout.getChildren().addAll(champsObligatoire);
			tab[0].setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");

		} else if (layout.getChildren().size() > 4)
			tab[0].setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");

		if (tab[1].getText().equals("")) {
			emptyField = true;
			if (layout.getChildren().size() > 4)
				layout.getChildren().remove(4);
			layout.getChildren().addAll(champsObligatoire);
			tab[1].setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		} else if (layout.getChildren().size() > 4)
			tab[1].setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
		if (tab[2].getText().equals("")) {
			emptyField = true;
			if (layout.getChildren().size() > 4)
				layout.getChildren().remove(4);
			layout.getChildren().addAll(champsObligatoire);
			tab[2].setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		} else if (layout.getChildren().size() > 4)
			tab[2].setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
		if (tab[3].getText().equals("")) {
			emptyField = true;
			if (layout.getChildren().size() > 4)
				layout.getChildren().remove(4);
			layout.getChildren().addAll(champsObligatoire);
			tab[3].setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		} else if (layout.getChildren().size() > 4)
			tab[3].setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
		if (!tab[4].getText().equals(""))
			tab[4].setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
		if (!emptyField) {
			Proprietaire prop = new Proprietaire(tab[0].getText(), tab[1].getText(), tab[2].getText(), tab[3].getText(),
					tab[4].getText());
			ImmoESI.addProp(prop);
			if (layout.getChildren().size() > 4)
				layout.getChildren().remove(4);
			layout.getChildren().addAll(propAdded);
			service.setOnSucceeded(e -> {
				this.close();
				service.reset();
				bienScreen.setScene(bienScreen.buildAddBienScene(typeBienString, bienScreen));
			});
		}
	}

	static class ProcessService extends Service<Void> {

		@Override
		protected Task<Void> createTask() {
			return new Task<Void>() {

				@Override
				protected Void call() throws Exception { // Computations takes 0.7
					Thread.sleep(1300);
					return null;
				}
			};
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
