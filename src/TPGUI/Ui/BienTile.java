package TPGUI.Ui;
import TPGUI.Control.ArchiveBienButtonController;
import TPGUI.Control.DetailsButtonController;
//import TPGUI.Control.ModifyBienButtonController;
import TPGUI.Control.RemoveBienButtonController;
import TPGUI.Control.ContactButtonController;
import TPGUI.Noyau.Bien;
import TPGUI.Noyau.ImmoESI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class BienTile extends ListCell<Bien> {
    private ImmoESI model;
    private HomeScreen homeScreen;

    public BienTile(ImmoESI model, HomeScreen homeScreen) {
        this.model = model;
        this.homeScreen = homeScreen;
    }

    @Override
    protected void updateItem(Bien bien, boolean empty) {
        super.updateItem(bien, empty);
        if(bien != null) {
            //Building BienTile (ListCell)
            BorderPane tile = new BorderPane();
            tile.setPadding(new Insets(10));
            tile.setLeft(buildLeftOfTile(bien));
            tile.setRight(buildRightOfTile(bien));
            BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5), new Insets(5));
            setBackground(new Background(backgroundFill));
            tile.setBorder(new Border(new BorderStroke(Color.LIGHTGREY, BorderStrokeStyle.SOLID,
                    new CornerRadii(5), BorderWidths.DEFAULT)));
            Button detailsOrModifyButton = buildTileButton(model.isAuthenticated() ? "Modify Bien" : "View Details");
            /*detailsOrModifyButton.setOnAction(model.isAuthenticated() ? new ModifyBienButtonController(bien)
                    : new DetailsButtonController(bien));*/
            Button contactButton = buildTileButton("Contact");
            contactButton.setOnAction( new ContactButtonController(bien));
            Button removeBienButton = buildTileButton("Remove Bien");
            removeBienButton.setOnAction(new RemoveBienButtonController(bien, homeScreen));
            Button archiveBienButton = buildTileButton("Archive Bien");
            archiveBienButton.setOnAction(new ArchiveBienButtonController(bien, homeScreen));
            HBox buttonBar = model.isAuthenticated() ? new HBox(detailsOrModifyButton, archiveBienButton, removeBienButton)
                    : new HBox(contactButton, detailsOrModifyButton);
            buttonBar.setAlignment(Pos.BASELINE_RIGHT);
            buttonBar.setSpacing(30);
            tile.setBottom(buttonBar);
            setGraphic(tile);
        }
    }

    private Button buildTileButton(String s) {
        Button button = new Button(s);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setPrefSize(150, 30);
        button.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        return button;
    }

    private VBox buildLeftOfTile(Bien bien) {
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
        return left;
    }

    private VBox buildRightOfTile(Bien bien) {
        Label price = createMessage(String.format("%.3f DA\n", bien.getPrixFinal()));
        price.setTextFill(Color.WHITE);
        price.setFont(Font.font("Droid Serif", FontWeight.BOLD, 16));
        price.setBackground(new Background(new BackgroundFill(Color.MIDNIGHTBLUE, new CornerRadii(3), Insets.EMPTY)));
        VBox right = new VBox(price);
        return right;
    }

    private Label createMessage(String s) {
        Label etiquette = new Label(s);
        etiquette.setAlignment(Pos.CENTER);
        etiquette.setFont(Font.font ("Verdana", 16));
        etiquette.setLineSpacing(3);
        return etiquette;
    }
}
