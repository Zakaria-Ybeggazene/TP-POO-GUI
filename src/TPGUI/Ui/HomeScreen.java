package TPGUI.Ui;

import TPGUI.Control.AdminLoginController;
import TPGUI.Noyau.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.text.Font;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.util.StringConverter;

import javax.swing.*;

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
        this.setScene(getNewScene());
    }

    private Label createMessage(String s) {
        Label etiquette = new Label(s);
        etiquette.setAlignment(Pos.CENTER);
        etiquette.setFont(Font.font("Verdana", 16));
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
        Button adminLoginButton = new Button(model.isAuthenticated() ? "Disconnect" : "Login as\nAdmin");
        adminLoginButton.setWrapText(true);
        adminLoginButton.setTextAlignment(TextAlignment.CENTER);
        adminLoginButton.setPrefSize(model.isAuthenticated() ? 450 : 400, 50);
        adminLoginButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        adminLoginButton.setOnAction(new AdminLoginController(model, this));
        HBox topLayer = new HBox(topMessage, adminLoginButton);
        topLayer.setPadding(new Insets(10));
        return topLayer;
    }

    public Scene getNewScene() {
        //Creatng Top Welcome Message
        BorderPane scaffold = new BorderPane();
        HBox topLayer = buildTopWelcomeMessage();
        scaffold.setTop(topLayer);
        // Creating BienTiles
        ObservableList<Bien> observableBiens = FXCollections.observableList(ImmoESI.getListBiens());
        ListView<Bien> bienListView = new ListView<>();
        bienListView.setMaxWidth(1000);
        bienListView.setPrefWidth(1000);
        bienListView.setItems(observableBiens);
        bienListView.setCellFactory((ListView<Bien> l) -> new BienTile(model));
        ToolBar filterBar = buildFilterBar();
        filterBar.setOrientation(Orientation.VERTICAL);
        HBox homeScreenCenter = new HBox(filterBar, bienListView);
        homeScreenCenter.setAlignment(Pos.CENTER);
        homeScreenCenter.setPrefSize(1000, -1);
        scaffold.setCenter(homeScreenCenter);
        return new Scene(scaffold, 1080, 520);
    }

    private ToolBar buildFilterBar() {
        Label transLabel = createMessage("Type de Transaction");
        transLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        ObservableList<Transaction> observableListTrans = FXCollections.observableArrayList(Transaction.values());
        ChoiceBox<Transaction> typeTrans = new ChoiceBox<>(observableListTrans);
        Label wilayaLabel = createMessage("Wilaya");
        wilayaLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        ObservableList<Wilaya> observableListWilaya = FXCollections.observableArrayList(Wilaya.values());
        ChoiceBox<Wilaya> wilaya = new ChoiceBox<>(observableListWilaya);
        Label prixMaxLabel = createMessage("Prix Max (en DA)");
        prixMaxLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        Spinner<Double> prixMaxSpinner = new Spinner<>();
        prixMaxSpinner.setEditable(true);
        prixMaxSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, Double.MAX_VALUE, 0, 1000.0));
        Label prixMinLabel = createMessage("Prix Min (en DA)");
        prixMinLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        Spinner<Double> prixMinSpinner = new Spinner<>();
        prixMinSpinner.setEditable(true);
        prixMinSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, Double.MAX_VALUE, 0, 1000.0));
        Label bienLabel = createMessage("Type du bien");
        bienLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        ObservableList<String> observableListBien = FXCollections.observableArrayList("Appartement", "Maison", "Terrain");
        ChoiceBox<String> typeBien = new ChoiceBox<>(observableListBien);
        Label surfaceMinLabel = createMessage("Surface min (en m²)");
        surfaceMinLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        Spinner<Double> surfaceMinSpinner = new Spinner<>();
        surfaceMinSpinner.setEditable(true);
        surfaceMinSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, Double.MAX_VALUE, 0));
        Label nbMinPiecesLabel = createMessage("Nombre min de pieces");
        nbMinPiecesLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        Spinner<Integer> nbMinPiecesSpinner = new Spinner<>();
        nbMinPiecesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 1, 1));
        Button filterButton = new Button("Filter");
        filterButton.setPrefSize(171, 35);
        filterButton.setFont(Font.font("Roboto", FontWeight.BOLD, 16));
        filterButton.setBackground(new Background(new BackgroundFill(Color.MIDNIGHTBLUE, new CornerRadii(3), Insets.EMPTY)));
        filterButton.setTextFill(Color.WHITE);
        ToolBar filterBar = new ToolBar(transLabel, typeTrans, wilayaLabel, wilaya, prixMaxLabel, prixMaxSpinner, prixMinLabel, prixMinSpinner, bienLabel, typeBien, surfaceMinLabel, surfaceMinSpinner, nbMinPiecesLabel, nbMinPiecesSpinner, filterButton);
        filterBar.setOrientation(Orientation.VERTICAL);
        return filterBar;
    }
}
