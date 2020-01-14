package TPGUI.Ui;

import TPGUI.Noyau.Bien;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.*;

public class DetailsScreen extends Stage {
    private Bien bien;

    public DetailsScreen(Bien bien) {
        this.bien = bien;
        this.setTitle(bien.getNatureTransaction().getNomTrans() + " " + bien.getClass().getSimpleName() + " " +
                bien.getWilaya().toString());
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
        Label description = createMessage("Description :\n" + bien.getDescriptif());
        description.setFont(Font.font("Montserrat", FontWeight.BOLD, 16));
        description.setWrapText(true);
        Label price = createMessage(String.format("%.3f DA\n", bien.getPrixFinal()));
        price.setTextFill(javafx.scene.paint.Color.WHITE);
        price.setFont(Font.font("Droid Serif", FontWeight.BOLD, 16));
        price.setBackground(new Background(new BackgroundFill(Color.MIDNIGHTBLUE, new CornerRadii(3), new Insets(0))));
        // ADD OTHER DETAILS SAMY NEHLIL MOULOUD
        VBox details = new VBox(typeBien, typeTrans, price, description);
        details.setPrefWidth(700);
        details.setSpacing(10);
        scrollPane.setContent(details);
        scrollPane.setPadding(new Insets(20));
    }

    public Label createMessage(String s) {
        Label etiquette = new Label(s);
        etiquette.setAlignment(Pos.CENTER);
        etiquette.setFont(Font.font("Verdana", 16));
        etiquette.setLineSpacing(3);
        return etiquette;
    }
}