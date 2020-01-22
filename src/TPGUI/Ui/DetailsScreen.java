package TPGUI.Ui;

import TPGUI.Control.ContactButtonController;

import TPGUI.Noyau.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class DetailsScreen extends Stage {

	public DetailsScreen(Bien bien) {
		this.setTitle(bien.getNatureTransaction().getNomTrans() + " " + bien.getClass().getSimpleName() + " "
				+ bien.getWilaya().toString());
		this.setResizable(false);
		this.setMaxWidth(800);
		this.setMaxHeight(400);
		BorderPane scaffold = new BorderPane();
		ScrollPane scrollPane = new ScrollPane();
		scaffold.setCenter(scrollPane);
		this.setScene(new Scene(scaffold, 800, 400));
		Label typeBien = createMessage(bien.getClass().getSimpleName());
		typeBien.setFont(Font.font("Ubuntu", FontWeight.BOLD, 24));
		typeBien.setTextFill(Color.MIDNIGHTBLUE);
		Label typeTrans = createMessage(bien.getNatureTransaction().getNomTrans());
		typeTrans.setFont(Font.font("Roboto", FontWeight.EXTRA_BOLD, 16));
		typeTrans.setTextFill(Color.DARKBLUE);
		Label description = createMessage("Description :\n" + bien.getDescriptif());
		description.setFont(Font.font("Montserrat", FontWeight.BOLD, 16));
		description.setWrapText(true);
		Label price = createMessage(String.format("%.3f DA\n", bien.getPrixFinal()));
		price.setTextFill(javafx.scene.paint.Color.WHITE);
		price.setFont(Font.font("Droid Serif", FontWeight.BOLD, 16));
		price.setBackground(new Background(new BackgroundFill(Color.MIDNIGHTBLUE, new CornerRadii(3), new Insets(0))));
		Label adresse = createMessage("Adresse exacte :\n" + bien.getAdresseExacte());
		adresse.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		Label superficie = createMessage("Superficie :\n" + bien.getSuperficie() + " m�");
		superficie.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		Button contactButton = new Button("Contact");
		contactButton.setTextAlignment(TextAlignment.CENTER);
		contactButton.setPrefSize(100, 30);
		contactButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		contactButton.setWrapText(true);
		contactButton.setPadding(new Insets(10));
		contactButton.setOnAction(new ContactButtonController(bien));
		HBox topLayer = new HBox(contactButton);
		topLayer.setAlignment(Pos.BASELINE_RIGHT);
		if (bien instanceof Appartement) {
			Label etage = createMessage("Etage : " + ((Appartement) bien).getEtage());
			etage.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
			Label duplex = createMessage(
					((Appartement) bien).isDuplex() ? "Type d'appartement : Duplexe" : "Type d'appartement : Simplexe");
			duplex.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
			Label ascenseur = createMessage(
					((Appartement) bien).hasAscenseur() ? "Ascenseur : Oui" : "Ascenseur : Non");
			ascenseur.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
			VBox details = new VBox(typeBien, typeTrans, price, description, adresse, superficie, etage, duplex,
					ascenseur);
			scaffold.setTop(topLayer);
			details.setPrefWidth(700);
			details.setSpacing(10);
			scrollPane.setContent(details);
			scrollPane.setPadding(new Insets(20));
		} else {
			if (bien instanceof Maison) {
				Label garage = createMessage(((Maison) bien).hasGarage() ? "Garage : Oui" : "Garage : Non");
				garage.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
				Label jardin = createMessage(((Maison) bien).hasJardin() ? "Jardin : Oui" : "Jardin : Non");
				jardin.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
				Label piscine = createMessage(((Maison) bien).hasPiscine() ? "Piscine : Oui" : "Piscine : Non");
				piscine.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
				Label superficieHabitable = createMessage(
						"Superficie habitable :" + ((Maison) bien).getSuperficieHabitable());
				superficieHabitable.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
				VBox details = new VBox(typeBien, typeTrans, price, description, adresse, superficie, garage, jardin,
						piscine, superficieHabitable);
				scaffold.setTop(topLayer);
				details.setPrefWidth(700);
				details.setSpacing(10);
				scrollPane.setContent(details);
				scrollPane.setPadding(new Insets(20));
			} else {
				Label nbFacades = createMessage("Nombre de facades :" + ((Terrain) bien).getNbFacades());
				nbFacades.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
				Label statutJuridique = createMessage("Statut juridique :" + ((Terrain) bien).getStatutJuridique());
				statutJuridique.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
				VBox details = new VBox(typeBien, typeTrans, price, description, adresse, superficie, nbFacades,
						statutJuridique);
				scaffold.setTop(topLayer);
				details.setPrefWidth(700);
				details.setSpacing(10);
				scrollPane.setContent(details);
				scrollPane.setPadding(new Insets(20));
			}
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