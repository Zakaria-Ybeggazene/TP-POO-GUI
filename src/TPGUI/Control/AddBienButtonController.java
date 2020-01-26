package TPGUI.Control;

import TPGUI.Noyau.ImmoESI;
import TPGUI.Ui.SetBienScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AddBienButtonController implements EventHandler<ActionEvent> {
    private ImmoESI model;

    public AddBienButtonController(ImmoESI model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        SetBienScreen stage = new SetBienScreen(model);
        // search an icon later
        stage.show();
    }
}
