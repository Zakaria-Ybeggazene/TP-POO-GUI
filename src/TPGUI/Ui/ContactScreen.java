package TPGUI.Ui;

import TPGUI.Noyau.Bien;
import TPGUI.Control.*;

import TPGUI.Noyau.Proprietaire;
import TPGUI.Ui.ContactScreen;
import TPGUI.Control.ContactButtonController;
import javafx.scene.control.Label;
import javafx.scene.text.*;
import javafx.stage.*;
import TPGUI.Noyau.Bien;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.text.Font;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.awt.*;

public class ContactScreen extends Stage {
	private TextArea messageToAdmin;
	private Bien bien;

	public ContactScreen(Bien b) {
		this.bien = b;
		this.setTitle("Contact " + bien.getNatureTransaction().getNomTrans() + " " + bien.getClass().getSimpleName()
				+ " " + bien.getWilaya().toString());
		this.setResizable(false);
		this.setMaxWidth(800);
		this.setMaxHeight(400);
		this.setX(350);
		this.setY(200);
		BorderPane scaffold = new BorderPane();
		ScrollPane scrollPane = new ScrollPane();
		this.setScene(new Scene(scaffold, 800, 400));
		Label message1 = createMessage(bien.getNatureTransaction().getNomTrans() + " " + bien.getClass().getSimpleName()
				+ " " + bien.getWilaya().toString());
		Label message2= createMessage("NOUS CONTACTER:");
		message2.setFont(Font.font("Droid Serif",FontWeight.BOLD,16));
		HBox topLayout = new HBox(message1);
		topLayout.setPrefSize(30, 30);
		topLayout.setPadding(new Insets(20));
		Label nom = createMessage("Nom : " + bien.getProprietaire().getNom());
		Label prenom = createMessage("Prenom : " + bien.getProprietaire().getPrenom());
		Label adresse = createMessage("adresse :" + bien.getProprietaire().getAdresse());
		Label email = createMessage("email : " + bien.getProprietaire().getEmail());
		Label tel = createMessage("tel : " + bien.getProprietaire().getTel());
		VBox infosProp = new VBox(nom, prenom, adresse, email);// deuxième composant
		messageToAdmin = new TextArea();
		System.out.println(messageToAdmin.getPrefWidth());
		messageToAdmin.setEditable(true);
		messageToAdmin.setText("");
		//messageToAdmin.setAlignment(Pos.TOP_LEFT);
		messageToAdmin.setWrapText(true);
		Button sendMessageButton = new Button("send");
		sendMessageButton.setTextAlignment(TextAlignment.CENTER);
		sendMessageButton.setPrefSize(90, 30);
		sendMessageButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		VBox space = new VBox();
		space.setPrefSize(0,50);
		HBox contact = new HBox(message2);
		VBox contactAdmin = new VBox(contact,space,messageToAdmin,sendMessageButton);// dernier composant
		//contactAdmin.setAlignment(Pos.CENTER);
		contactAdmin.setMaxSize(400, 400);
		contactAdmin.setSpacing(20);
		infosProp.setPrefWidth(700);
		infosProp.setSpacing(20);
		scrollPane.setContent(infosProp);
		scrollPane.setPadding(new Insets(20));
		scrollPane.setMaxSize(500,500);
		//scrollPane.set
		scaffold.setTop(topLayout);
		HBox midLayer = new HBox(scrollPane,contactAdmin);
		midLayer.setSpacing(60);
		midLayer.setPadding(new Insets(20));
		scaffold.setCenter(midLayer);
		sendMessageButton.setOnAction(new SendMessageAdminController(messageToAdmin));


	}

	public Label createMessage(String s) {
		Label etiquette = new Label(s);
		etiquette.setAlignment(Pos.CENTER);
		etiquette.setFont(Font.font("Verdana", 16));
		etiquette.setLineSpacing(3);
		return etiquette;
	}

}