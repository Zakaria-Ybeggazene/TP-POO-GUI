package TPGUI.Ui;

import TPGUI.Noyau.*;

import javafx.application.Application;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		ImmoESI admin = new ImmoESI();
		Proprietaire prop1 = new Proprietaire("nom1", "prenom1", "adresse1", "tel1", "mail1");
		Proprietaire prop2 = new Proprietaire("nom2", "prenom2", "adresse2", "tel2", "mail2");
		Proprietaire prop3 = new Proprietaire("nom3", "prenom3", "adresse3", "tel3", "mail3");
		Proprietaire prop4 = new Proprietaire("nom4", "prenom4", "adresse4", "tel4", "mail4");

		admin.login("123");

		admin.addProp(prop1);
		admin.addProp(prop2);
		admin.addProp(prop3);
		admin.addProp(prop4);

		Bien bien1 = new Appartement("adr", Wilaya.WILAYA2, 120, prop2, Transaction.VENTE, 4000000., false,
				"descdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdesc"
						+ "descdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdesc"
						+ "descdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdesc"
						+ "descdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdesc"
						+ "descdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdesc"
						+ "descdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdesc"
						+ "descdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdescdesc",
				LocalDateTime.now(), false, 4, true, 1, false, true);
		Bien bien2 = new Maison("adr", Wilaya.WILAYA3, 200, prop1, Transaction.VENTE, 10000000., false, "desc",
				LocalDateTime.now(), false, 9, true, 3, false, true, false, 160);
		Bien bien3 = new Terrain("adr", Wilaya.WILAYA1, 500, prop1, Transaction.VENTE, 20000000., false, "desc",
				LocalDateTime.now(), false, "stat", 3);
		Bien bien4 = new Appartement("adr", Wilaya.WILAYA3, 100, prop2, Transaction.LOCATION, 40000., true, "desc",
				LocalDateTime.now(), false, 3, true, 1, false, false);
		Bien bien5 = new Maison("adr", Wilaya.WILAYA2, 160, prop3, Transaction.LOCATION, 150000., true, "desc",
				LocalDateTime.now(), false, 10, true, 2, false, false, true, 120);
		Bien bien6 = new Appartement("adr", Wilaya.WILAYA3, 50, prop2, Transaction.LOCATION, 60000., false, "desc",
				LocalDateTime.now(), false, 1, true, 6, false, false);
		Bien bien7 = new Terrain("adr", Wilaya.WILAYA1, Wilaya.WILAYA1, 650, prop1, Transaction.ECHANGE, 18000000.,
				true, "desc", LocalDateTime.now(), false, "stat", 1);
		Bien bien8 = new Maison("adr", Wilaya.WILAYA2, Wilaya.WILAYA2, 200, prop2, Transaction.ECHANGE, 14000000.,
				false, "desc", LocalDateTime.now(), false, 11, false, 3, true, false, false, 160);

		admin.addBien(bien1, prop2);
		admin.addBien(bien2, prop1);
		admin.addBien(bien3, prop1);
		admin.addBien(bien4, prop2);
		admin.addBien(bien5, prop3);
		admin.addBien(bien6, prop2);
		admin.addBien(bien7, prop1);
		admin.addBien(bien8, prop2);

		admin.logout();
		HomeScreen stage = new HomeScreen(admin);

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
