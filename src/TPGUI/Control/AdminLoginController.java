package TPGUI.Control;

import TPGUI.Noyau.ImmoESI;
import TPGUI.Ui.HomeScreen;
import TPGUI.Ui.LoginScreen;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;

public class AdminLoginController implements EventHandler<ActionEvent> {
    private ImmoESI model;
    private HomeScreen homeScreen;

    public AdminLoginController(ImmoESI model, HomeScreen homeScreen) {
        this.homeScreen = homeScreen;
        this.model = model;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(!model.isAuthenticated()) {
            LoginScreen stage = new LoginScreen(model, homeScreen);
    		stage.getIcons().add(new Image(("file:key.png")));
            stage.show();
        } else {
            model.logout();
            homeScreen.setScene(homeScreen.getNewScene(FXCollections.observableList(ImmoESI.getListBiens())));
            homeScreen.show();
        }
    }
}
