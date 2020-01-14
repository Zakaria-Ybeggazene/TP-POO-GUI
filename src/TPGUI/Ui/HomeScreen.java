package TPGUI.Ui;
import TPGUI.Noyau.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.text.Font;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class HomeScreen extends Stage {
    private ImmoESI model;
    public HomeScreen(ImmoESI model) {
        //Creating Main Layout
        this.model = model;
        this.setTitle("Immo ESI");
        this.setResizable(true);
        this.setMinWidth(1080);
        this.setMinHeight(520);
        BorderPane scaffold = new BorderPane();
        scaffold.setPadding(new Insets(10));
        this.setScene(new Scene(scaffold, 1080, 520));
        //Creatng Top Welcome Message
        HBox topLayer = buildTopWelcomeMessage();
        scaffold.setTop(topLayer);
        // Creating BienTiles
        ObservableList<Bien> observableBiens = FXCollections.observableList(ImmoESI.getListBiens());
        ListView<Bien> bienListView = new ListView<>();
        bienListView.setMaxWidth(1000);
        bienListView.setItems(observableBiens);
        bienListView.setCellFactory((ListView<Bien> l) -> new BienTile());
        Label raw = createMessage("Filter Bar (coming soon)");
        raw.setBorder(new Border(new BorderStroke(Color.LIGHTGREY, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
        HBox filterBar = new HBox(raw);
        filterBar.setAlignment(Pos.CENTER);
        VBox homeScreenCenter = new VBox(filterBar, bienListView);
        homeScreenCenter.setAlignment(Pos.CENTER);
        scaffold.setCenter(homeScreenCenter);
    }

    public Label createMessage(String s) {
        Label etiquette = new Label(s);
        etiquette.setAlignment(Pos.CENTER);
        etiquette.setFont(Font.font ("Verdana", 16));
        etiquette.setLineSpacing(3);
        return etiquette;
    }

    private HBox buildTopWelcomeMessage() {
        Label mainMessage = createMessage("Bienvenu dans Immo ESI");
        mainMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 32));
        Label descriptionMessage = createMessage("Immo ESI est une application de gestion de biens " +
                "immobiliers qui met en contact vendeurs et acheteurs.\nDécouvrez les biens existant sur la " +
                "plateforme et contactez nous pour ajouter les votres !");
        descriptionMessage.setWrapText(true);
        TextFlow topMessage = new TextFlow(mainMessage, descriptionMessage);
        Button adminLoginButton = new Button("Login as\nAdmin");
        adminLoginButton.setWrapText(true);
        adminLoginButton.setTextAlignment(TextAlignment.CENTER);
        adminLoginButton.setPrefSize(400, 50);
        adminLoginButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        HBox topLayer = new HBox(topMessage, adminLoginButton);
        topLayer.setPadding(new Insets(10));
        return topLayer;
    }
}
