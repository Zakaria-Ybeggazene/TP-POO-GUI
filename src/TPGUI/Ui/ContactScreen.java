package TPGUI.Ui;

import TPGUI.Noyau.Bien;
import TPGUI.Control.*;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ContactScreen extends Stage {

	public ContactScreen(Bien b) {
		this.setTitle("Contact " + b.getNatureTransaction().getNomTrans() + " " + b.getClass().getSimpleName()
				+ " " + b.getWilaya().toString());
		this.setResizable(false);
		this.setMaxWidth(800);
		this.setMaxHeight(400);
		this.setX(350);
		this.setY(200);
		BorderPane scaffold = new BorderPane();
		this.setScene(new Scene(scaffold, 800, 400));
		Label message1 = createMessage(b.getNatureTransaction().getNomTrans() + " " + b.getClass().getSimpleName()
				+ " " + b.getWilaya().toString());
		message1.setFont(Font.font("Verdana",FontWeight.BOLD,16));
		Label message2= createMessage("NOUS CONTACTER:");
		message2.setFont(Font.font("Verdana",FontWeight.BOLD,16));
		HBox topLayout = new HBox(message1);
		topLayout.setPrefSize(30, 30);
		topLayout.setPadding(new Insets(20));
		Label nom = createMessage("Nom : " + b.getProprietaire().getNom());
		Label prenom = createMessage("Prenom : " + b.getProprietaire().getPrenom());
		Label adresse = createMessage("Adresse :" + b.getProprietaire().getAdresse());
		Label email = createMessage("Email : " + b.getProprietaire().getEmail());
		Label tel = createMessage("Tel : " + b.getProprietaire().getTel());
		VBox infosProp = new VBox(nom, prenom, adresse, email, tel);// deuxième composant
		TextArea messageToAdmin = new TextArea();
		messageToAdmin.setEditable(true);
		messageToAdmin.setText("");
		messageToAdmin.setWrapText(true);
		Button sendMessageButton = new Button("Send");
		sendMessageButton.setTextAlignment(TextAlignment.CENTER);
		sendMessageButton.setPrefSize(90, 30);
		sendMessageButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		VBox space = new VBox();
		space.setPrefSize(0,10);
		HBox contact = new HBox(message2);
		VBox contactAdmin = new VBox(contact,space, messageToAdmin,sendMessageButton);// dernier composant
		contactAdmin.setMaxSize(400, 400);
		contactAdmin.setSpacing(30);
		infosProp.setPrefWidth(700);
		infosProp.setSpacing(20);
		VBox propInfoBox = new VBox(infosProp);
		propInfoBox.setPadding(new Insets(20));
		propInfoBox.setMaxSize(500,500);
		propInfoBox.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID,
				new CornerRadii(2), BorderWidths.DEFAULT)));
		//scrollPane.set
		scaffold.setTop(topLayout);
		HBox midLayer = new HBox(propInfoBox,contactAdmin);
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