package TPGUI.Control;

import TPGUI.Noyau.Bien;
import TPGUI.Noyau.ImmoESI;
import TPGUI.Ui.SetBienScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ModifyBienButtonController implements EventHandler<ActionEvent> {
    private ImmoESI model;
    private Bien bien;

    public ModifyBienButtonController(ImmoESI model, Bien bien) {
        this.model = model;
        this.bien = bien;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        SetBienScreen stage = new SetBienScreen(model, bien);
        //add an icon later
        stage.show();
    }
}
