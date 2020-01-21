package TPGUI.Control;

import TPGUI.Noyau.Bien;
import TPGUI.Noyau.ImmoESI;
import TPGUI.Ui.HomeScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ArchiveBienButtonController implements EventHandler<ActionEvent> {
    private Bien bien;
    private HomeScreen homeScreen;

    public ArchiveBienButtonController(Bien bien, HomeScreen homeScreen) {
        this.bien = bien;
        this.homeScreen = homeScreen;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        ImmoESI.getListArchive().add(bien);
        ImmoESI.getListProprietaires().get(ImmoESI.getListProprietaires().indexOf(bien.getProprietaire())).removeBien(bien);
        ImmoESI.getListBiens().remove(bien);
        homeScreen.setScene(homeScreen.getNewScene());
    }
}
