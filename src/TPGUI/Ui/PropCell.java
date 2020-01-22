package TPGUI.Ui;

import TPGUI.Noyau.Proprietaire;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PropCell extends ListCell<Proprietaire> {
	protected void updateItem(Proprietaire prop, boolean empty) {
		super.updateItem(prop, empty);
		if (prop != null) {
			// Building BienTile (ListCell)
			BorderPane tile = new BorderPane();
			tile.setPadding(new Insets(10));
			tile.setLeft(buildLeftOfTile(prop));
			tile.setRight(buildRightOfTile(prop));
			BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5), new Insets(5));
			setBackground(new Background(backgroundFill));
			tile.setBorder(new Border(new BorderStroke(Color.LIGHTGREY, BorderStrokeStyle.SOLID, new CornerRadii(5),
					BorderWidths.DEFAULT)));
			setGraphic(tile);
		}
	}

	private VBox buildLeftOfTile(Proprietaire prop) {
		Label nom = createMessage("NOM :\n" + prop.getNom());
		nom.setFont(Font.font("Montserrat", FontWeight.BOLD, 16));
		nom.setTextOverrun(OverrunStyle.ELLIPSIS);
		nom.setEllipsisString(" ...");
		nom.setWrapText(true);
		nom.setMaxHeight(70);
		Label prenom = createMessage("PRENOM :\n" + prop.getPrenom());
		prenom.setFont(Font.font("Montserrat", FontWeight.BOLD, 16));
		prenom.setTextOverrun(OverrunStyle.ELLIPSIS);
		prenom.setEllipsisString(" ...");
		prenom.setWrapText(true);
		prenom.setMaxHeight(70);
		Label adresse = createMessage("ADRESSE :\n" + prop.getAdresse());
		adresse.setFont(Font.font("Montserrat", FontWeight.BOLD, 16));
		adresse.setTextOverrun(OverrunStyle.ELLIPSIS);
		adresse.setEllipsisString(" ...");
		adresse.setWrapText(true);
		adresse.setMaxHeight(70);
		VBox left = new VBox(nom, prenom, adresse);
		left.setPrefWidth(450);
		return left;
	}

	private VBox buildRightOfTile(Proprietaire prop) {
		Label coordonnees = createMessage("COORDONNEES:\n");
		coordonnees.setTextFill(Color.WHITE);
		coordonnees.setFont(Font.font("Droid Serif", FontWeight.BOLD, 16));
		coordonnees.setBackground(new Background(new BackgroundFill(Color.MIDNIGHTBLUE, new CornerRadii(3), Insets.EMPTY)));
		Label tel = createMessage("Tel :" + prop.getTel());
		Label email = createMessage("email :" + prop.getEmail());
		VBox style = new VBox(tel, email);
		style.setSpacing(30);
		VBox right = new VBox(coordonnees, style);
		right.setSpacing(20);
		right.setPrefWidth(200);
		right.setBorder(new Border(new BorderStroke(Color.DARKGREY,BorderStrokeStyle.SOLID,null,new BorderWidths(1))));
		right.setPadding(new Insets(10));
		return right;
	}

	private Label createMessage(String s) {
		Label etiquette = new Label(s);
		etiquette.setAlignment(Pos.CENTER);
		etiquette.setFont(Font.font("Verdana", 16));
		etiquette.setLineSpacing(3);
		return etiquette;
	}
}
