package TPGUI.Control;

import TPGUI.Noyau.Bien;
import TPGUI.Noyau.Habitable;
import TPGUI.Noyau.ImmoESI;
import TPGUI.Ui.HomeScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;

public class RemoveBienButtonController implements EventHandler<ActionEvent> {
    private Bien bien;
    private HomeScreen homeScreen;

    public RemoveBienButtonController(Bien bien, HomeScreen homeScreen) {
        this.bien = bien;
        this.homeScreen = homeScreen;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        ImmoESI.getListProprietaires().get(ImmoESI.getListProprietaires().indexOf(bien.getProprietaire())).removeBien(bien);
        ImmoESI.getListBiens().remove(bien);
        homeScreen.setScene(homeScreen.getNewScene(FXCollections.observableList(ImmoESI.getListBiens())));
    }
}
