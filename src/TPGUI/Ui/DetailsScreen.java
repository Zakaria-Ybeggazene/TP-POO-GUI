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
		this.setTitle(bien.getIdentifiant()+" "+bien.getNatureTransaction().getNomTrans() + " " + bien.getClass().getSimpleName() + " "
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
		Label dateAjout = createMessage("Added on "+bien.getDateAjoutString());
		Label descriptionLabel = createMessage("Description :\n");
		descriptionLabel.setUnderline(true);
		Label description = createMessage(bien.getDescriptif());
		description.setFont(Font.font("Montserrat", FontWeight.BOLD, 16));
		description.setWrapText(true);
		Label price = createMessage(String.format("%.3f DA\n", bien.getPrixFinal()));
		price.setTextFill(javafx.scene.paint.Color.WHITE);
		price.setFont(Font.font("Droid Serif", FontWeight.BOLD, 16));
		price.setBackground(new Background(new BackgroundFill(Color.MIDNIGHTBLUE, new CornerRadii(3), new Insets(0))));
		Label adresseLabel = createMessage("Adresse exacte :\n");
		adresseLabel.setUnderline(true);
		Label adresse = createMessage(bien.getAdresseExacte()+"\n"+bien.getWilaya().toString());
		Label superficieLabel = createMessage("Superficie");
		superficieLabel.setUnderline(true);
		Label superficie = createMessage(" : "+bien.getSuperficie() + " m²");
		HBox superficieLine = new HBox(superficieLabel, superficie);
		Button contactButton = new Button("Contact");
		contactButton.setTextAlignment(TextAlignment.CENTER);
		contactButton.setPrefSize(100, 30);
		contactButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		contactButton.setPadding(new Insets(10));
		contactButton.setOnAction(new ContactButtonController(bien));
		HBox topLayer = new HBox(contactButton);
		topLayer.setAlignment(Pos.BASELINE_RIGHT);
		topLayer.setOpaqueInsets(new Insets(10));
		VBox details;
		if (bien instanceof Appartement) {
			Label etageLabel = createMessage("Etage");
			etageLabel.setUnderline(true);
			Label etage = createMessage((" : "+((Appartement) bien).getEtage()));
			HBox etageLine = new HBox(etageLabel, etage);
			Label duplexLabel = createMessage("Type d'appartement");
			duplexLabel.setUnderline(true);
			Label duplex = createMessage(
					((Appartement) bien).isDuplex() ? " : Duplexe" : " : Simplexe");
			HBox duplexLine = new HBox(duplexLabel, duplex);
			Label ascenseurLabel = createMessage("Ascenseur");
			ascenseurLabel.setUnderline(true);
			Label ascenseur = createMessage(
					((Appartement) bien).hasAscenseur() ? " : Oui" : " : Non");
			HBox ascenseurLine = new HBox(ascenseurLabel, ascenseur);
			details = new VBox(typeBien, typeTrans, dateAjout, price,descriptionLabel, description,
					adresseLabel, adresse, superficieLine, etageLine, duplexLine, ascenseurLine);
		} else {
			if (bien instanceof Maison) {
				Label garageLabel = createMessage("Garage");
				garageLabel.setUnderline(true);
				Label garage = createMessage(((Maison) bien).hasGarage() ? " : Oui" : " : Non");
				HBox garageLine = new HBox(garageLabel, garage);
				Label jardinLabel = createMessage("Jardin");
				jardinLabel.setUnderline(true);
				Label jardin = createMessage(((Maison) bien).hasJardin() ? " : Oui" : " : Non");
				HBox jardinLine = new HBox(jardinLabel, jardin);
				Label piscineLabel = createMessage("Piscine");
				piscineLabel.setUnderline(true);
				Label piscine = createMessage(((Maison) bien).hasPiscine() ? " : Oui" : " : Non");
				HBox piscineLine = new HBox(piscineLabel, piscine);
				Label superficieHabitableLabel = createMessage("Superficie habitable");
				superficieHabitableLabel.setUnderline(true);
				Label superficieHabitable = createMessage(" : "+((Maison) bien).getSuperficieHabitable()+" m²");
				HBox superficieHabitableLine = new HBox(superficieHabitableLabel, superficieHabitable);
				details = new VBox(typeBien, typeTrans, dateAjout, price, descriptionLabel, description,
						adresseLabel, adresse, superficieLine, garageLine, jardinLine,
						piscineLine, superficieHabitableLine);
			} else {
				Label nbFacadesLabel = createMessage("Nombre de facades");
				nbFacadesLabel.setUnderline(true);
				Label nbFacades = createMessage(" : "+((Terrain) bien).getNbFacades());
				HBox nbFacadesLine = new HBox(nbFacadesLabel, nbFacades);
				Label statutJuridiqueLabel = createMessage("Statut juridique");
				statutJuridiqueLabel.setUnderline(true);
				Label statutJuridique = createMessage(" : "+((Terrain) bien).getStatutJuridique());
				HBox statutJuridiqueLine = new HBox(statutJuridiqueLabel, statutJuridique);
				details = new VBox(typeBien, typeTrans, dateAjout, price, descriptionLabel, description,
						adresseLabel, adresse, superficieLine, nbFacadesLine, statutJuridiqueLine);
			}
		}
		scaffold.setTop(topLayer);
		details.setPrefWidth(700);
		details.setSpacing(10);
		scrollPane.setContent(details);
		scrollPane.setPadding(new Insets(20));
	}

	private Label createMessage(String s) {
		Label etiquette = new Label(s);
		etiquette.setAlignment(Pos.CENTER);
		etiquette.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		etiquette.setLineSpacing(3);
		return etiquette;
	}
}