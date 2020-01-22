package TPGUI.Control;

import TPGUI.Noyau.*;
import TPGUI.Ui.HomeScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;

import java.util.ArrayList;
import java.util.List;

public class FilterButtonController implements EventHandler<ActionEvent> {
    HomeScreen homeScreen;
    ObservableList<Bien> bienObservableList;
    ChoiceBox<Transaction> transaction;
    ChoiceBox<Wilaya> wilaya;
    Spinner<Double> prixMax;
    Spinner<Double> prixMin;
    ChoiceBox<String > typeBien;
    Spinner<Double> superficieMin;
    Spinner<Integer> nbMinPieces;

    public FilterButtonController(HomeScreen homeScreen, ObservableList<Bien> bienObservableList,
                                  ChoiceBox<Transaction> transaction, ChoiceBox<Wilaya> wilaya, Spinner<Double> prixMax,
                                  Spinner<Double> prixMin, ChoiceBox<String> typeBien, Spinner<Double> superficieMin,
                                  Spinner<Integer> nbMinPieces) {
        this.homeScreen = homeScreen;
        this.bienObservableList = bienObservableList;
        this.transaction = transaction;
        this.wilaya = wilaya;
        this.prixMax = prixMax;
        this.prixMin = prixMin;
        this.typeBien = typeBien;
        this.superficieMin = superficieMin;
        this.nbMinPieces = nbMinPieces;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        List<Bien> matchingBiens = new ArrayList<>();
        for(Bien b : ImmoESI.getListBiens()) {
            if(bienValid(b)) matchingBiens.add(b);
        }
        bienObservableList = FXCollections.observableList(matchingBiens);
        homeScreen.setScene(homeScreen.getNewScene(bienObservableList));
    }

    private boolean bienValid(Bien b) {
        if(transaction.getValue() != null && b.getNatureTransaction() != transaction.getValue()) return false;
        if(wilaya.getValue() != null && b.getWilaya() != wilaya.getValue()) return false;
        if(prixMax.getValue() != 0 && b.getPrixFinal() > prixMax.getValue()) return false;
        if(prixMin.getValue() != 0 && b.getPrixFinal() < prixMin.getValue()) return false;
        if(typeBien.getValue() != null && !b.getClass().getSimpleName().equals(typeBien.getValue())) return false;
        if(superficieMin.getValue() != 0 && b.getSuperficie() < superficieMin.getValue()) return false;
        return nbMinPieces.getValue() == 0 || (!(b instanceof Terrain) && ((Habitable) b).getNbPieces() >= nbMinPieces.getValue());
    }
}
