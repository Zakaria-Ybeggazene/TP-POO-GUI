package TPGUI.Ui;


import TPGUI.Control.DetailsButtonController; 
import TPGUI.Noyau.Bien;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
import javafx.scene.text.TextAlignment;

public class ArchiveBienCell extends ListCell<Bien> {
	protected void updateItem(Bien bien, boolean empty) {
		super.updateItem(bien, empty);
		if (bien != null) {
			// Building BienTile (ListCell)
			BorderPane tile = new BorderPane();
			tile.setPadding(new Insets(10));
			tile.setLeft(buildLeftOfTile(bien));
			tile.setRight(buildRightOfTile(bien));
			BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5), new Insets(5));
			setBackground(new Background(backgroundFill));
			tile.setBorder(new Border(new BorderStroke(Color.LIGHTGREY, BorderStrokeStyle.SOLID, new CornerRadii(5),
					BorderWidths.DEFAULT)));
			setGraphic(tile);
		}
	}

	private VBox buildLeftOfTile(Bien bien) {
		Label typeBien = createMessage(bien.getClass().getSimpleName());
		typeBien.setFont(Font.font("Ubuntu", FontWeight.BOLD, 24));
		typeBien.setTextFill(Color.MIDNIGHTBLUE);
		Label typeTrans = createMessage(bien.getNatureTransaction().getNomTrans());
		typeTrans.setFont(Font.font("Roboto", FontWeight.EXTRA_BOLD, 16));
		typeTrans.setTextFill(Color.DARKBLUE);
		Label description = createMessage("Description :\n" + bien.getDescriptif());
		description.setFont(Font.font("Montserrat", FontWeight.BOLD, 16));
		description.setTextOverrun(OverrunStyle.ELLIPSIS);
		description.setEllipsisString(" ...");
		description.setWrapText(true);
		description.setMaxHeight(70);
		VBox left = new VBox(typeBien, typeTrans, description);
		left.setPrefWidth(600);
		return left;
	}

	private VBox buildRightOfTile(Bien bien) {
		Label price = createMessage(String.format("%.3f DA\n", bien.getPrixFinal()));
		price.setTextFill(Color.WHITE);
		price.setFont(Font.font("Droid Serif", FontWeight.BOLD, 16));
		price.setBackground(new Background(new BackgroundFill(Color.MIDNIGHTBLUE, new CornerRadii(3), Insets.EMPTY)));
		Button detailsButton = new Button("View details");
		detailsButton.setTextAlignment(TextAlignment.CENTER);
		detailsButton.setPrefSize(150, 30);
		detailsButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		detailsButton.setOnAction(new DetailsButtonController(bien));
		VBox right = new VBox(price,detailsButton);
		right.setSpacing(70);
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
