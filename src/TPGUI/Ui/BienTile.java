package TPGUI.Ui;
import TPGUI.Noyau.Bien;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BienTile extends ListCell<Bien> {
    public BienTile() {}

    @Override
    protected void updateItem(Bien bien, boolean empty) {
        super.updateItem(bien, empty);
        if(bien != null) {
            //Building BienTile (ListCell)
            Label typeBien = createMessage(bien.getClass().getSimpleName());
            typeBien.setFont(Font.font("Ubuntu", FontWeight.BOLD, 24));
            typeBien.setTextFill(Color.PURPLE);
            Label typeTrans = createMessage(bien.getNatureTransaction().getNomTrans());
            typeTrans.setFont(Font.font("Roboto", FontWeight.EXTRA_BOLD, 16));
            typeTrans.setTextFill(Color.DARKGREEN);
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
            price.setBackground(new Background(new BackgroundFill(Color.PURPLE, new CornerRadii(3), new Insets(0))));
            VBox right = new VBox(price);
            BorderPane tile = new BorderPane();
            tile.setPadding(new Insets(10));
            tile.setLeft(left);
            tile.setRight(right);
            BackgroundFill backgroundFill = new BackgroundFill(Color.BEIGE, new CornerRadii(5), new Insets(5));
            setBackground(new Background(backgroundFill));
            tile.setBorder(new Border(new BorderStroke(Color.LIGHTGREY, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
            setGraphic(tile);
        }
    }

    public Label createMessage(String s) {
        Label etiquette = new Label(s);
        etiquette.setAlignment(Pos.CENTER);
        etiquette.setFont(Font.font ("Verdana", 16));
        etiquette.setLineSpacing(3);
        return etiquette;
    }
}
