package TPGUI.Control;

import TPGUI.Noyau.Bien;
import TPGUI.Ui.SetBienScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ModifyBienButtonController implements EventHandler<ActionEvent> {
    private Bien bien;

    public ModifyBienButtonController(Bien bien) {
        this.bien = bien;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        SetBienScreen stage = new SetBienScreen(bien);
        //add an icon later
        stage.show();
    }
}
