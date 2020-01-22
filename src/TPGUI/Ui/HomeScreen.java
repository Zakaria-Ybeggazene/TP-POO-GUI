package TPGUI.Ui;

import TPGUI.Control.AdminLoginController;
import TPGUI.Control.FilterButtonController;
import TPGUI.Noyau.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
        this.getIcons().add(new Image(("file:home.png")));
        BorderPane scaffold = new BorderPane();
        scaffold.setPadding(new Insets(10));
        ObservableList<Bien> observableBiens = FXCollections.observableList(ImmoESI.getListBiens());
        this.setScene(getNewScene(observableBiens));
    }

    public Scene getNewScene(ObservableList<Bien> observableBiens) {
        //Creatng Top Welcome Message
        BorderPane scaffold = new BorderPane();
        HBox topLayer = buildTopLayer();
        scaffold.setTop(topLayer);
        // Creating BienTiles
        //ObservableList<Bien> observableBiens = FXCollections.observableList(ImmoESI.getListBiens());
        ListView<Bien> bienListView = new ListView<>();
        bienListView.setMaxWidth(1000);
        bienListView.setPrefWidth(1000);
        bienListView.setItems(observableBiens);
        bienListView.setCellFactory((ListView<Bien> l) -> new BienTile(model, this));
        ToolBar filterBar = buildFilterBar(observableBiens);
        filterBar.setOrientation(Orientation.VERTICAL);
        HBox homeScreenCenter = new HBox(filterBar, bienListView);
        homeScreenCenter.setAlignment(Pos.CENTER);
        homeScreenCenter.setMaxSize(1000, -1);
        homeScreenCenter.setPrefSize(1000, -1);
        scaffold.setCenter(homeScreenCenter);
        scaffold.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        return isMaximized() ? new Scene(scaffold, 1280, 800) : new Scene(scaffold, 1080, 520);
    }

    private HBox buildTopLayer() {
        Pane top;
        if(!model.isAuthenticated()) {
            Label mainMessage = createMessage("Bienvenu dans Immo ESI");
            mainMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 32));
            Label descriptionMessage = createMessage("Immo ESI est une application de gestion de biens " +
                    "immobiliers qui met en contact vendeurs et acheteurs.\nDécouvrez les biens existant sur la " +
                    "plateforme et contactez nous pour ajouter les votres !");
            descriptionMessage.setWrapText(true);
            TextFlow topMessage = new TextFlow(mainMessage, descriptionMessage);
            top = topMessage;
        } else {
            HBox topAdminTools = new HBox(buildSettingsButton(), buildStdTopButton("View Prop List"),
                    buildStdTopButton("View Archive List"), buildStdTopButton("View Messages List"),
                    buildStdTopButton("Add Bien"));
            topAdminTools.setSpacing(10);
            top = topAdminTools;
        }
        HBox topLayer = new HBox(top, buildAdminLoginButton());
        topLayer.setSpacing(model.isAuthenticated() ? isMaximized() ? 250 : 80 : 10);
        topLayer.setPadding(model.isAuthenticated()? new Insets(10, 10, 43, 10) : new Insets(10));
        return topLayer;
    }

    private ToolBar buildFilterBar(ObservableList<Bien> observableBiens) {
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
        nbMinPiecesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 1, 1));
        Button filterButton = new Button("Filter");
        filterButton.setPrefSize(171, 35);
        filterButton.setFont(Font.font("Roboto", FontWeight.BOLD, 16));
        filterButton.setBackground(new Background(new BackgroundFill(Color.MIDNIGHTBLUE, new CornerRadii(3), Insets.EMPTY)));
        filterButton.setTextFill(Color.WHITE);
        filterButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                filterButton.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, new CornerRadii(3), Insets.EMPTY)));
            }
        });
        filterButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                filterButton.setBackground(new Background(new BackgroundFill(Color.MIDNIGHTBLUE, new CornerRadii(3), Insets.EMPTY)));
            }
        });
        filterButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                filterButton.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(3))));
            }
        });
        filterButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                filterButton.setBorder(Border.EMPTY);
            }
        });
        filterButton.setOnAction(new FilterButtonController(this,
                observableBiens,
                typeTrans,
                wilaya,
                prixMaxSpinner,
                prixMinSpinner,
                typeBien,
                surfaceMinSpinner,
                nbMinPiecesSpinner));
        ToolBar filterBar = new ToolBar(transLabel,
                typeTrans,
                wilayaLabel,
                wilaya,
                prixMaxLabel,
                prixMaxSpinner,
                prixMinLabel,
                prixMinSpinner,
                bienLabel,
                typeBien,
                surfaceMinLabel,
                surfaceMinSpinner,
                nbMinPiecesLabel,
                nbMinPiecesSpinner,
                filterButton);
        filterBar.setOrientation(Orientation.VERTICAL);
        return filterBar;
    }

    private Button buildAdminLoginButton() {
        Button adminLoginButton = new Button(model.isAuthenticated() ? "Disconnect" : "Login as\nAdmin");
        adminLoginButton.setWrapText(true);
        adminLoginButton.setTextAlignment(TextAlignment.CENTER);
        adminLoginButton.setPrefSize(model.isAuthenticated() ? 100 : 300, 50);
        adminLoginButton.setMinSize(model.isAuthenticated() ? 100 : 100, 50);
        adminLoginButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        adminLoginButton.setOnAction(new AdminLoginController(model, this));
        return adminLoginButton;
    }

    private Button buildSettingsButton() {
        Image settingsIcon = new Image(getClass().getResourceAsStream("../../settings.png"), 40, 40, true, true);
        Button settingButton = new Button();
        settingButton.setGraphic(new ImageView(settingsIcon));
        settingButton.setPrefSize(50, 50);
        return settingButton;
    }

    private Button buildStdTopButton(String s) {
        Button button = new Button(s);
        button.setPrefSize(200, 50);
        button.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        return button;
    }

    private Label createMessage(String s) {
        Label etiquette = new Label(s);
        etiquette.setAlignment(Pos.CENTER);
        etiquette.setFont(Font.font("Verdana", 16));
        etiquette.setLineSpacing(3);
        return etiquette;
    }
}
