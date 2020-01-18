package TPGUI.Control;

import TPGUI.Noyau.ImmoESI;
import TPGUI.Ui.HomeScreen;
import TPGUI.Ui.LoginScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
            stage.show();
        } else {
            model.logout();
            homeScreen.setScene(homeScreen.getNewScene());
            homeScreen.show();
        }
    }
}
