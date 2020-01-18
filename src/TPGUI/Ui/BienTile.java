package TPGUI.Ui;
import TPGUI.Control.DetailsButtonController;
import TPGUI.Control.ModifyButtonController;
import TPGUI.Noyau.Bien;
import TPGUI.Noyau.ImmoESI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class BienTile extends ListCell<Bien> {
    private ImmoESI model;
    public BienTile(ImmoESI model) {
        this.model = model;
    }

    @Override
    protected void updateItem(Bien bien, boolean empty) {
        super.updateItem(bien, empty);
        if(bien != null) {
            //Building BienTile (ListCell)
            Label typeBien = createMessage(bien.getClass().getSimpleName());
            typeBien.setFont(Font.font("Ubuntu", FontWeight.BOLD, 24));
            typeBien.setTextFill(Color.MIDNIGHTBLUE);
            Label typeTrans = createMessage(bien.getNatureTransaction().getNomTrans());
            typeTrans.setFont(Font.font("Roboto", FontWeight.EXTRA_BOLD, 16));
            typeTrans.setTextFill(Color.DARKBLUE);
            Label description = createMessage("Description :\n"+bien.getDescriptif());
            description.setFont(Font.font("Montserrat", FontWeight.BOLD, 16));
            description.setTextOverrun(OverrunStyle.ELLIPSIS);
            description.setEllipsisString(" ...");
            description.setWrapText(true);
            description.setMaxHeight(70);
            VBox left = new VBox(typeBien, typeTrans, description);
            left.setPrefWidth(600);
            Label price = createMessage(String.format("%.3f DA\n", bien.getPrixFinal()));
            price.setTextFill(Color.WHITE);
            price.setFont(Font.font("Droid Serif", FontWeight.BOLD, 16));
            price.setBackground(new Background(new BackgroundFill(Color.MIDNIGHTBLUE, new CornerRadii(3), Insets.EMPTY)));
            VBox right = new VBox(price);
            BorderPane tile = new BorderPane();
            tile.setPadding(new Insets(10));
            tile.setLeft(left);
            tile.setRight(right);
            BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5), new Insets(5));
            setBackground(new Background(backgroundFill));
            tile.setBorder(new Border(new BorderStroke(Color.LIGHTGREY, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
            Button viewDetailsButton = new Button(model.isAuthenticated() ? "Modifier Bien" : "View Details");
            viewDetailsButton.setTextAlignment(TextAlignment.CENTER);
            viewDetailsButton.setPrefSize(150, 30);
            viewDetailsButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
            viewDetailsButton.setOnAction(model.isAuthenticated() ? new ModifyButtonController() : new DetailsButtonController(bien));
            Button contactButton = new Button("Contact");
            contactButton.setTextAlignment(TextAlignment.CENTER);
            contactButton.setPrefSize(150, 30);
            contactButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
            HBox buttonBar = new HBox(contactButton, viewDetailsButton);
            buttonBar.setAlignment(Pos.BASELINE_RIGHT);
            buttonBar.setSpacing(30);
            tile.setBottom(buttonBar);
            setGraphic(tile);
        }
    }

    private Label createMessage(String s) {
        Label etiquette = new Label(s);
        etiquette.setAlignment(Pos.CENTER);
        etiquette.setFont(Font.font ("Verdana", 16));
        etiquette.setLineSpacing(3);
        return etiquette;
    }
}
