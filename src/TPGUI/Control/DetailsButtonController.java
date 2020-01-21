package TPGUI.Control;

import TPGUI.Noyau.Bien;

import TPGUI.Ui.DetailsScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;

public class DetailsButtonController implements EventHandler<ActionEvent> {
    private Bien bien;

    public DetailsButtonController(Bien bien) {
        this.bien = bien;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        DetailsScreen stage = new DetailsScreen(bien);
        stage.getIcons().add(new Image(("file:home.png")));
        stage.show();
    }
}
