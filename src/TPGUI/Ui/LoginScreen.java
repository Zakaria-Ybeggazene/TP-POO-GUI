package TPGUI.Ui;

import TPGUI.Noyau.ImmoESI;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.security.Provider;

public class LoginScreen extends Stage {
    ImmoESI model;
    HomeScreen homeScreen;

    public LoginScreen(ImmoESI model, HomeScreen homeScreen) {
        this.model = model;
        this.homeScreen = homeScreen;
        this.setTitle("Login");
        this.setResizable(false);
        this.setMaxHeight(200);
        this.setMaxWidth(400);
        BorderPane scaffold = new BorderPane();
        this.setScene(new Scene(scaffold, 400, 200));
        Label passwordLabel = createMessage("Entrez le mot de passe de l'admin");
        passwordLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        loginButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        loginButton.setPrefSize(70, 30);
        VBox layout = new VBox(passwordLabel, passwordField, loginButton);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(5);
        layout.setMaxWidth(350);
        passwordField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    loginGraphic(passwordField, layout);
                }
            }
        });
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loginGraphic(passwordField, layout);
            }
        });
        scaffold.setCenter(layout);
    }

    private Label createMessage(String s) {
        Label etiquette = new Label(s);
        etiquette.setAlignment(Pos.CENTER);
        etiquette.setFont(Font.font("Verdana", 16));
        etiquette.setLineSpacing(3);
        return etiquette;
    }

    private void loginGraphic(PasswordField passwordField, VBox layout) {
        Service service = new ProcessService();
        if(!service.isRunning()) {
            service.start();
        }
        model.login(passwordField.getText());
        if (model.isAuthenticated()) {
            passwordField.setText("");
            Label correctPass = createMessage("You are authenticated");
            correctPass.setTextFill(Color.GREEN);
            if (layout.getChildren().size() > 3) layout.getChildren().remove(3);
            layout.getChildren().add(correctPass);
            service.setOnSucceeded(e -> {
                this.close();
                homeScreen.setScene(homeScreen.getNewScene());
                homeScreen.show();
                service.reset();
            });
        } else {
            passwordField.setText("");
            Label wrongPass = createMessage("Wrong Password");
            wrongPass.setTextFill(Color.RED);
            if (layout.getChildren().size() > 3) layout.getChildren().remove(3);
            layout.getChildren().add(wrongPass);
        }
    }
    class ProcessService extends Service<Void> {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    // Computations takes 3 seconds
                    // Calling Thread.sleep instead of random computation
                    Thread.sleep(1500);
                    return null;
                }
            };
        }
    }
}
