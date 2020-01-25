package TPGUI.Control;

import TPGUI.Ui.SetBienScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AddBienButtonController implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        SetBienScreen stage = new SetBienScreen();
        // search an icon later
        stage.show();
    }
}
