package TPGUI.Ui;

import TPGUI.Noyau.ImmoESI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SettingsScreen extends Stage {
	private ImmoESI model;

	public SettingsScreen(ImmoESI model) {
		this.model = model;
		this.setTitle("Settings");
		this.setResizable(false);
		this.setMaxHeight(300);
		this.setMaxWidth(400);
		BorderPane scaffold = new BorderPane();
		this.setScene(new Scene(scaffold, 400, 200));
		scaffold.setOnKeyPressed(keyEvent -> {
			if(keyEvent.getCode().equals(KeyCode.ESCAPE)) close();
		});
		Label passwordLabel1 = createMessage("Actual password    : ");
		Label passwordLabel2 = createMessage("New password       : ");
		Label passwordLabel3 = createMessage("Confirm                   : ");
		passwordLabel1.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
		passwordLabel2.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
		passwordLabel3.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
		PasswordField passwordField1 = new PasswordField();
		passwordField1.setPrefWidth(160);
		PasswordField passwordField2 = new PasswordField();
		passwordField2.setPrefWidth(160);
		PasswordField passwordField3 = new PasswordField();
		passwordField3.setPrefWidth(160);
		HBox top = new HBox(passwordLabel1, passwordField1);
		top.setSpacing(12);
		HBox mid = new HBox(passwordLabel2, passwordField2);
		mid.setSpacing(12);
		HBox bottom = new HBox(passwordLabel3, passwordField3);
		bottom.setSpacing(8);
		Button changePasswordButton = new Button("Change");
		changePasswordButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		changePasswordButton.setPrefSize(70, 30);
		VBox layout = new VBox(top, mid, bottom, changePasswordButton);
		layout.setAlignment(Pos.CENTER);
		layout.setSpacing(10);
		layout.setMaxWidth(350);
		passwordField1.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode().equals(KeyCode.ENTER)) {
					PasswordField[] tab = new PasswordField[3];
					tab[0] = passwordField1;
					tab[1] = passwordField2;
					tab[2] = passwordField3;
					changePasswordGraphic(tab, layout);
				}
			}
		});
		passwordField2.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode().equals(KeyCode.ENTER)) {
					PasswordField[] tab = new PasswordField[3];
					tab[0] = passwordField1;
					tab[1] = passwordField2;
					tab[2] = passwordField3;
					changePasswordGraphic(tab, layout);
				}
			}
		});
		passwordField3.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode().equals(KeyCode.ENTER)) {
					PasswordField[] tab = new PasswordField[3];
					tab[0] = passwordField1;
					tab[1] = passwordField2;
					tab[2] = passwordField3;
					changePasswordGraphic(tab, layout);
				}
			}
		});
		changePasswordButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				PasswordField[] tab = new PasswordField[3];
				tab[0] = passwordField1;
				tab[1] = passwordField2;
				tab[2] = passwordField3;
				changePasswordGraphic(tab, layout);
			}
		});
		scaffold.setCenter(layout);
	}

	private void changePasswordGraphic(PasswordField[] tab, VBox layout) {
		Service<Void> service = new ProcessService();
		if (!service.isRunning()) {
			service.start();
		}
		if (tab[0].getText().equals(model.getPassword())) {
			tab[0].setText("");
			if (tab[1].getText().equals("") || tab[2].getText().equals("")) {
				tab[1].setText("");
				tab[2].setText("");
				Label emptyField = createMessage("Empty field");
				emptyField.setTextFill(Color.RED);
				if (layout.getChildren().size() > 4)
					layout.getChildren().remove(4);
				layout.getChildren().add(emptyField);
			} else {
				if (tab[1].getText().equals(tab[2].getText())) {
					model.setPassword(tab[1].getText());
					tab[1].setText("");
					tab[2].setText("");
					Label changedSuccesfully = createMessage("Password changed Successfully");
					changedSuccesfully.setTextFill(Color.GREEN);
					if (layout.getChildren().size() > 4)
						layout.getChildren().remove(4);
					layout.getChildren().add(changedSuccesfully);
					service.setOnSucceeded(e -> {
						this.close();
						service.reset();
					});

				} else {
					tab[1].setText("");
					tab[2].setText("");
					Label wrongConfirmation = createMessage("Confirmation failed");
					wrongConfirmation.setTextFill(Color.RED);
					if (layout.getChildren().size() > 4)
						layout.getChildren().remove(4);
					layout.getChildren().add(wrongConfirmation);
				}
			}
		} else {
			tab[0].setText("");
			tab[1].setText("");
			tab[2].setText("");
			Label wrongPassword = createMessage("Wrong password");
			wrongPassword.setTextFill(Color.RED);
			if (layout.getChildren().size() > 4)
				layout.getChildren().remove(4);
			layout.getChildren().add(wrongPassword);
		}

	}

	static class ProcessService extends Service<Void> {

		@Override
		protected Task<Void> createTask() {
			return new Task<Void>() {

				@Override
				protected Void call() throws Exception { // Computations takes 0.7
					Thread.sleep(1400);
					return null;
				}
			};
		}
	}

	private Label createMessage(String s) {
		Label etiquette = new Label(s);
		etiquette.setAlignment(Pos.CENTER);
		etiquette.setFont(Font.font("Verdana", 16));
		etiquette.setLineSpacing(3);
		return etiquette;
	}
}
