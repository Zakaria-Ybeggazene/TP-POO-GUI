package TPGUI.Ui;

import TPGUI.Noyau.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class SetBienScreen extends Stage {
    public SetBienScreen() {
        this.setTitle("Add Bien");
        this.setResizable(false);
        setMaxWidth(800);
        setMaxHeight(500);
        this.setScene(this.buildBienChoiceScene());
    }

    public SetBienScreen(Bien bien) {
        this.setTitle("Modify Bien");
        this.setResizable(false);
        setMaxWidth(800);
        setMaxHeight(500);
        this.setScene(this.buildModifyBienScene(bien, this));
    }

    private Scene buildBienChoiceScene() {
        BorderPane scaffold = new BorderPane();
        Label intro = createMessage("Choisissez le type du bien que vous voulez ajouter :\n");
        intro.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        Button appartementButton = buildChoiceButton("Appartement", bienAddingController("Appartement", this));
        Button maisonButton = buildChoiceButton("Maison", bienAddingController("Maison", this));
        Button terrainButton = buildChoiceButton("Terrain", bienAddingController("Terrain", this));
        HBox choice = new HBox(appartementButton, maisonButton, terrainButton);
        choice.setAlignment(Pos.CENTER);
        choice.setSpacing(20);
        VBox layout = new VBox(intro, choice);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(30);
        layout.setPadding(new Insets(0, 0, 50, 0));
        scaffold.setCenter(layout);
        return new Scene(scaffold, 800, 500);
    }

    private Button buildChoiceButton(String s, EventHandler<ActionEvent> controller) {
        Button button = new Button(s);
        button.setPrefSize(200, 50);
        button.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        button.setOnAction(controller);
        return button;
    }

    private Label createMessage(String s) {
        Label etiquette = new Label(s);
        etiquette.setAlignment(Pos.CENTER);
        etiquette.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        etiquette.setLineSpacing(3);
        return etiquette;
    }

    private EventHandler<ActionEvent> bienAddingController(String typeBienString, SetBienScreen bienScreen) {
        return actionEvent -> {
            bienScreen.setScene(buildAddBienScene(typeBienString, bienScreen));
        };
    }

    private Scene buildAddBienScene(String typeBienString, SetBienScreen bienScreen) {
        BorderPane scaffold = new BorderPane();
        Button returnToChoiceButton = new Button("<- Retour aux choix");
        returnToChoiceButton.setPrefSize(150, 20);
        returnToChoiceButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        returnToChoiceButton.setOnAction(returnActionEvent -> bienScreen.setScene(bienScreen.buildBienChoiceScene()));
        scaffold.setTop(returnToChoiceButton);
        GridPane form = new GridPane();
        form.setVgap(2);
        form.setHgap(0);
        form.setPadding(new Insets(20));
        ColumnConstraints colConst = new ColumnConstraints();
        colConst.setPercentWidth(100.0 / 2);
        form.getColumnConstraints().add(colConst);
        form.getColumnConstraints().add(colConst);
        Label typeBienLabel = createMessage("Type du bien");
        ChoiceBox<String> typeBien = new ChoiceBox<>(FXCollections.singletonObservableList(typeBienString));
        typeBien.setDisable(true);
        typeBien.setValue(typeBienString);
        Label propLabel = createMessage("Proprietaire");
        ObservableList<String> propList = FXCollections.observableArrayList();
        ChoiceBox<String> prop = new ChoiceBox<>();
        for (Proprietaire p : ImmoESI.getListProprietaires()) {
            propList.add(p.getNom() + " " + p.getPrenom() + ", Email : " + p.getEmail());
        }
        prop.setItems(propList);
        prop.setMaxWidth(190);
        Label createPropLabel = createMessage("ou ajouter un prop : ");
        Button createPropButton = new Button("Creer Prop");
        createPropButton.setPrefSize(100, 10);
        createPropButton.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
        HBox addProp = new HBox(createPropLabel, createPropButton);
        addProp.setMaxSize(300, 50);
        addProp.setAlignment(Pos.CENTER_LEFT);
        Label typeTransLabel = createMessage("Type de Transaction");
        ChoiceBox<Transaction> typeTrans = new ChoiceBox<>(FXCollections.observableArrayList(Transaction.values()));
        Label wilayaLabel = createMessage("Wilaya");
        ChoiceBox<Wilaya> wilaya = new ChoiceBox<>(FXCollections.observableArrayList(Wilaya.values()));
        Label addressLabel = createMessage("Adresse exacte");
        TextArea address = new TextArea();
        address.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 150 ? change : null));
        address.setText("");
        address.setWrapText(true);
        address.setMaxWidth(190);
        address.setPrefColumnCount(40);
        address.setPrefRowCount(3);
        Label superficieLabel = createMessage("Superficie (m²)");
        TextField superficie = new TextField();
        superficie.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                superficie.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        superficie.setMaxWidth(150);
        VBox left = new VBox(typeBienLabel, typeBien, propLabel, prop, addProp, typeTransLabel,
                typeTrans, wilayaLabel, wilaya, addressLabel, address, superficieLabel, superficie);
        left.setSpacing(2);
        form.addColumn(0, left);
        Label priceLabel = createMessage("Prix (DA)");
        TextField price = new TextField();
        price.setTextFormatter(getTextFormatter());
        price.setMaxWidth(150);
        Label prixNegociableLabel = createMessage("Prix Negociable ? ");
        ChoiceBox<String> prixNegociable = new ChoiceBox<>(FXCollections.observableArrayList("Oui", "Non"));
        HBox prixNegociableLine = new HBox(prixNegociableLabel, prixNegociable);
        prixNegociableLine.setAlignment(Pos.CENTER_LEFT);
        Label descriptifLabel = createMessage("Descriptif");
        TextArea descriptif = new TextArea();
        descriptif.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 400 ? change : null));
        descriptif.setText("");
        descriptif.setWrapText(true);
        descriptif.setPrefColumnCount(200);
        descriptif.setPrefRowCount(4);
        if (typeBienString.equals("Appartement")) {
            Label estMeubleLabel = createMessage("Meublé ? ");
            ChoiceBox<String> estMeuble = new ChoiceBox<>(FXCollections.observableArrayList("Oui", "Non"));
            HBox estMeubleLine = new HBox(estMeubleLabel, estMeuble);
            estMeubleLine.setAlignment(Pos.CENTER_LEFT);
            Label nbPiecesLabel = createMessage("Nombre de pieces");
            Spinner<Integer> nbPiecesSpinner = new Spinner<>();
            nbPiecesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 1, 1));
            nbPiecesSpinner.setEditable(false);
            VBox nbPieces = new VBox(nbPiecesLabel, nbPiecesSpinner);
            Label etageLabel = createMessage("Etage");
            Spinner<Integer> etageSpinner = new Spinner<>();
            etageSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 0, 1));
            etageSpinner.setEditable(false);
            VBox etage = new VBox(etageLabel, etageSpinner);
            HBox nbPiecesEtageBox = new HBox(nbPieces, etage);
            nbPiecesEtageBox.setSpacing(3);
            Label isDuplexLabel = createMessage("Duplex ? ");
            ChoiceBox<String> isDuplex = new ChoiceBox<>(FXCollections.observableArrayList("Oui", "Non"));
            HBox isDuplexLine = new HBox(isDuplexLabel, isDuplex);
            isDuplexLine.setAlignment(Pos.CENTER_LEFT);
            Label hasAscenseurLabel = createMessage("Ascenseur ? ");
            ChoiceBox<String> hasAscenseur = new ChoiceBox<>(FXCollections.observableArrayList("Oui", "Non"));
            HBox hasAscenseurLine = new HBox(hasAscenseurLabel, hasAscenseur);
            hasAscenseurLine.setAlignment(Pos.CENTER_LEFT);
            HBox duplexAscenseurBox = new HBox(isDuplexLine, hasAscenseurLine);
            duplexAscenseurBox.setSpacing(3);
            VBox right = new VBox(priceLabel, price, prixNegociableLine, estMeubleLine, nbPiecesEtageBox,
                    duplexAscenseurBox, descriptifLabel, descriptif);
            right.setSpacing(2);
            form.addColumn(1, right);
        } else if (typeBienString.equals("Maison")) {
            Label estMeubleLabel = createMessage("Meublé ? ");
            ChoiceBox<String> estMeuble = new ChoiceBox<>(FXCollections.observableArrayList("Oui", "Non"));
            HBox estMeubleLine = new HBox(estMeubleLabel, estMeuble);
            estMeubleLine.setAlignment(Pos.CENTER_LEFT);
            Label nbPiecesLabel = createMessage("Nombre de pieces");
            Spinner<Integer> nbPiecesSpinner = new Spinner<>();
            nbPiecesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 1, 1));
            nbPiecesSpinner.setEditable(false);
            VBox nbPieces = new VBox(nbPiecesLabel, nbPiecesSpinner);
            Label nbEtagesLabel = createMessage("Nombre d'étages");
            Spinner<Integer> nbEtagesSpinner = new Spinner<>();
            nbEtagesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 0, 1));
            nbEtagesSpinner.setEditable(false);
            VBox nbEtages = new VBox(nbEtagesLabel, nbEtagesSpinner);
            HBox nbPiecesNbEtagesBox = new HBox(nbPieces, nbEtages);
            nbPiecesNbEtagesBox.setSpacing(3);
            Label hasGarageLabel = createMessage("Garage ? ");
            ChoiceBox<String> hasGarage = new ChoiceBox<>(FXCollections.observableArrayList("Oui", "Non"));
            HBox hasGarageLine = new HBox(hasGarageLabel, hasGarage);
            hasGarageLine.setAlignment(Pos.CENTER_LEFT);
            Label hasJardinLabel = createMessage("Jardin ? ");
            ChoiceBox<String> hasJardin = new ChoiceBox<>(FXCollections.observableArrayList("Oui", "Non"));
            HBox hasJardinLine = new HBox(hasJardinLabel, hasJardin);
            hasJardinLine.setAlignment(Pos.CENTER_LEFT);
            HBox garageJardinBox = new HBox(hasGarageLine, hasJardinLine);
            garageJardinBox.setSpacing(3);
            Label hasPiscineLabel = createMessage("Piscine ? ");
            ChoiceBox<String> hasPiscine = new ChoiceBox<>(FXCollections.observableArrayList("Oui", "Non"));
            HBox hasPiscineLine = new HBox(hasPiscineLabel, hasPiscine);
            hasPiscineLine.setAlignment(Pos.CENTER_LEFT);
            Label superficieHabitableLabel = createMessage("Superficie habitable (m²)");
            TextField superficieHabitable = new TextField();
            superficieHabitable.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    superficieHabitable.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
            superficieHabitable.setMaxWidth(150);
            VBox right = new VBox(priceLabel, price, prixNegociableLine, estMeubleLine, nbPiecesNbEtagesBox,
                    garageJardinBox, hasPiscineLine, superficieHabitableLabel, superficieHabitable,
                    descriptifLabel, descriptif);
            right.setSpacing(2);
            form.addColumn(1, right);
        } else {
            Label statutJuridiqueLabel = createMessage("Statut Juridique");
            TextField statutJuridique = new TextField();
            statutJuridique.setMaxWidth(250);
            Label nbFacadesLabel = createMessage("Nombre de façades");
            Spinner<Integer> nbFacadesSpinner = new Spinner<>();
            nbFacadesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8, 1, 1));
            nbFacadesSpinner.setEditable(false);
            VBox right = new VBox(priceLabel, price, prixNegociableLine, statutJuridiqueLabel, statutJuridique,
                    nbFacadesLabel, nbFacadesSpinner, descriptifLabel, descriptif);
            right.setSpacing(2);
            form.addColumn(1, right);
        }
        scaffold.setCenter(form);
        scaffold.setPadding(new Insets(10));
        return new Scene(scaffold, 800, 500);
    }

    private Scene buildModifyBienScene(Bien bien, SetBienScreen bienScreen) {
        BorderPane scaffold = new BorderPane();
        GridPane form = new GridPane();
        form.setVgap(2);
        form.setHgap(0);
        form.setPadding(new Insets(20));
        ColumnConstraints colConst = new ColumnConstraints();
        colConst.setPercentWidth(100.0 / 2);
        form.getColumnConstraints().add(colConst);
        form.getColumnConstraints().add(colConst);
        Label typeBienLabel = createMessage("Type du bien");
        ChoiceBox<String> typeBien = new ChoiceBox<>(FXCollections.singletonObservableList(bien.getClass().getSimpleName()));
        typeBien.setValue(bien.getClass().getSimpleName());
        Label propLabel = createMessage("Proprietaire");
        ChoiceBox<String> prop = new ChoiceBox<>(FXCollections.observableArrayList(bien.getProprietaire().getNom()
                + " " + bien.getProprietaire().getPrenom() + ", Email : " + bien.getProprietaire().getEmail()));
        prop.setMaxWidth(190);
        prop.setValue(bien.getProprietaire().getNom()
                + " " + bien.getProprietaire().getPrenom() + ", Email : " + bien.getProprietaire().getEmail());
        Label typeTransLabel = createMessage("Type de Transaction");
        ChoiceBox<Transaction> typeTrans = new ChoiceBox<>(FXCollections.observableArrayList(Transaction.values()));
        typeTrans.setValue(bien.getNatureTransaction());
        Label wilayaLabel = createMessage("Wilaya");
        ChoiceBox<Wilaya> wilaya = new ChoiceBox<>(FXCollections.observableArrayList(Wilaya.values()));
        wilaya.setValue(bien.getWilaya());
        Label addressLabel = createMessage("Adresse exacte");
        TextArea address = new TextArea();
        address.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 150 ? change : null));
        address.setText(bien.getAdresseExacte());
        address.setWrapText(true);
        address.setMaxWidth(190);
        address.setPrefColumnCount(40);
        address.setPrefRowCount(3);
        Label superficieLabel = createMessage("Superficie (m²)");
        TextField superficie = new TextField();
        superficie.setText((int) bien.getSuperficie() + "");
        superficie.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                superficie.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        superficie.setMaxWidth(150);
        VBox left = new VBox(typeBienLabel, typeBien, propLabel, prop, typeTransLabel,
                typeTrans, wilayaLabel, wilaya, addressLabel, address, superficieLabel, superficie);
        left.setSpacing(2);
        form.addColumn(0, left);
        Label priceLabel = createMessage("Prix (DA)");
        TextField price = new TextField();
        price.setTextFormatter(getTextFormatter(bien));
        price.setMaxWidth(150);
        Label prixNegociableLabel = createMessage("Prix Negociable ? ");
        ChoiceBox<String> prixNegociable = new ChoiceBox<>(FXCollections.observableArrayList("Oui", "Non"));
        prixNegociable.setValue(bien.isPrixNegociable() ? "Oui" : "Non");
        HBox prixNegociableLine = new HBox(prixNegociableLabel, prixNegociable);
        prixNegociableLine.setAlignment(Pos.CENTER_LEFT);
        Label descriptifLabel = createMessage("Descriptif");
        TextArea descriptif = new TextArea();
        descriptif.setText(bien.getDescriptif());
        descriptif.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 400 ? change : null));
        descriptif.setWrapText(true);
        descriptif.setPrefColumnCount(200);
        descriptif.setPrefRowCount(4);
        if (bien.getClass().getSimpleName().equals("Appartement")) {
            Label estMeubleLabel = createMessage("Meublé ? ");
            ChoiceBox<String> estMeuble = new ChoiceBox<>(FXCollections.observableArrayList("Oui", "Non"));
            estMeuble.setValue(((Habitable) bien).estMeuble() ? "Oui" : "Non");
            HBox estMeubleLine = new HBox(estMeubleLabel, estMeuble);
            estMeubleLine.setAlignment(Pos.CENTER_LEFT);
            Label nbPiecesLabel = createMessage("Nombre de pieces");
            Spinner<Integer> nbPiecesSpinner = new Spinner<>();
            nbPiecesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30,
                    ((Habitable) bien).getNbPieces(), 1));
            nbPiecesSpinner.setEditable(false);
            VBox nbPieces = new VBox(nbPiecesLabel, nbPiecesSpinner);
            Label etageLabel = createMessage("Etage");
            Spinner<Integer> etageSpinner = new Spinner<>();
            etageSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30,
                    ((Appartement) bien).getEtage(), 1));
            etageSpinner.setEditable(false);
            VBox etage = new VBox(etageLabel, etageSpinner);
            HBox nbPiecesEtageBox = new HBox(nbPieces, etage);
            nbPiecesEtageBox.setSpacing(3);
            Label isDuplexLabel = createMessage("Duplex ? ");
            ChoiceBox<String> isDuplex = new ChoiceBox<>(FXCollections.observableArrayList("Oui", "Non"));
            isDuplex.setValue(((Appartement) bien).isDuplex() ? "Oui" : "Non");
            HBox isDuplexLine = new HBox(isDuplexLabel, isDuplex);
            isDuplexLine.setAlignment(Pos.CENTER_LEFT);
            Label hasAscenseurLabel = createMessage("Ascenseur ? ");
            ChoiceBox<String> hasAscenseur = new ChoiceBox<>(FXCollections.observableArrayList("Oui", "Non"));
            hasAscenseur.setValue(((Appartement) bien).hasAscenseur() ? "Oui" : "Non");
            HBox hasAscenseurLine = new HBox(hasAscenseurLabel, hasAscenseur);
            hasAscenseurLine.setAlignment(Pos.CENTER_LEFT);
            HBox duplexAscenseurBox = new HBox(isDuplexLine, hasAscenseurLine);
            duplexAscenseurBox.setSpacing(3);
            VBox right = new VBox(priceLabel, price, prixNegociableLine, estMeubleLine, nbPiecesEtageBox,
                    duplexAscenseurBox, descriptifLabel, descriptif);
            right.setSpacing(2);
            form.addColumn(1, right);
        } else if (bien.getClass().getSimpleName().equals("Maison")) {
            Label estMeubleLabel = createMessage("Meublé ? ");
            ChoiceBox<String> estMeuble = new ChoiceBox<>(FXCollections.observableArrayList("Oui", "Non"));
            estMeuble.setValue(((Habitable) bien).estMeuble() ? "Oui" : "Non");
            HBox estMeubleLine = new HBox(estMeubleLabel, estMeuble);
            estMeubleLine.setAlignment(Pos.CENTER_LEFT);
            Label nbPiecesLabel = createMessage("Nombre de pieces");
            Spinner<Integer> nbPiecesSpinner = new Spinner<>();
            nbPiecesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30,
                    ((Habitable) bien).getNbPieces(), 1));
            nbPiecesSpinner.setEditable(false);
            VBox nbPieces = new VBox(nbPiecesLabel, nbPiecesSpinner);
            Label nbEtagesLabel = createMessage("Nombre d'étages");
            Spinner<Integer> nbEtagesSpinner = new Spinner<>();
            nbEtagesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30,
                    ((Maison) bien).getNbEtages(), 1));
            nbEtagesSpinner.setEditable(false);
            VBox nbEtages = new VBox(nbEtagesLabel, nbEtagesSpinner);
            HBox nbPiecesNbEtagesBox = new HBox(nbPieces, nbEtages);
            nbPiecesNbEtagesBox.setSpacing(3);
            Label hasGarageLabel = createMessage("Garage ? ");
            ChoiceBox<String> hasGarage = new ChoiceBox<>(FXCollections.observableArrayList("Oui", "Non"));
            hasGarage.setValue(((Maison) bien).hasGarage() ? "Oui" : "Non");
            HBox hasGarageLine = new HBox(hasGarageLabel, hasGarage);
            hasGarageLine.setAlignment(Pos.CENTER_LEFT);
            Label hasJardinLabel = createMessage("Jardin ? ");
            ChoiceBox<String> hasJardin = new ChoiceBox<>(FXCollections.observableArrayList("Oui", "Non"));
            hasJardin.setValue(((Maison) bien).hasJardin() ? "Oui" : "Non");
            HBox hasJardinLine = new HBox(hasJardinLabel, hasJardin);
            hasJardinLine.setAlignment(Pos.CENTER_LEFT);
            HBox garageJardinBox = new HBox(hasGarageLine, hasJardinLine);
            garageJardinBox.setSpacing(3);
            Label hasPiscineLabel = createMessage("Piscine ? ");
            ChoiceBox<String> hasPiscine = new ChoiceBox<>(FXCollections.observableArrayList("Oui", "Non"));
            hasPiscine.setValue(((Maison) bien).hasPiscine() ? "Oui" : "Non");
            HBox hasPiscineLine = new HBox(hasPiscineLabel, hasPiscine);
            hasPiscineLine.setAlignment(Pos.CENTER_LEFT);
            Label superficieHabitableLabel = createMessage("Superficie habitable (m²)");
            TextField superficieHabitable = new TextField();
            superficieHabitable.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    superficieHabitable.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
            superficieHabitable.setMaxWidth(150);
            superficieHabitable.setText(((Maison) bien).getSuperficieHabitable() + "");
            VBox right = new VBox(priceLabel, price, prixNegociableLine, estMeubleLine, nbPiecesNbEtagesBox,
                    garageJardinBox, hasPiscineLine, superficieHabitableLabel, superficieHabitable,
                    descriptifLabel, descriptif);
            right.setSpacing(2);
            form.addColumn(1, right);
        } else {
            Label statutJuridiqueLabel = createMessage("Statut Juridique");
            TextField statutJuridique = new TextField();
            statutJuridique.setMaxWidth(250);
            statutJuridique.setText(((Terrain) bien).getStatutJuridique());
            Label nbFacadesLabel = createMessage("Nombre de façades");
            Spinner<Integer> nbFacadesSpinner = new Spinner<>();
            nbFacadesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8,
                    ((Terrain) bien).getNbFacades(), 1));
            nbFacadesSpinner.setEditable(false);
            VBox right = new VBox(priceLabel, price, prixNegociableLine, statutJuridiqueLabel, statutJuridique,
                    nbFacadesLabel, nbFacadesSpinner, descriptifLabel, descriptif);
            right.setSpacing(2);
            form.addColumn(1, right);
        }
        scaffold.setCenter(form);
        scaffold.setPadding(new Insets(10));
        return new Scene(scaffold, 800, 500);
    }

    private TextFormatter<Double> getTextFormatter(Bien bien) {
        Pattern validEditingState = Pattern.compile("(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

        UnaryOperator<TextFormatter.Change> filter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return c;
            } else {
                return null;
            }
        };

        StringConverter<Double> converter = new StringConverter<Double>() {

            @Override
            public Double fromString(String s) {
                if (s.isEmpty() || ".".equals(s)) {
                    return 0.0;
                } else {
                    return Double.valueOf(s);
                }
            }


            @Override
            public String toString(Double d) {
                return d.toString();
            }
        };
        return new TextFormatter<Double>(converter, bien.getPrixNet(), filter);
    }

    private TextFormatter<Double> getTextFormatter() {
        Pattern validEditingState = Pattern.compile("(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

        UnaryOperator<TextFormatter.Change> filter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return c;
            } else {
                return null;
            }
        };

        StringConverter<Double> converter = new StringConverter<Double>() {

            @Override
            public Double fromString(String s) {
                if (s.isEmpty() || ".".equals(s)) {
                    return 0.0;
                } else {
                    return Double.valueOf(s);
                }
            }


            @Override
            public String toString(Double d) {
                return d.toString();
            }
        };
        return new TextFormatter<Double>(converter, 0.0, filter);
    }
}
