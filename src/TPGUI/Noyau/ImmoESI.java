package TPGUI.Noyau;

import java.util.*;

public class ImmoESI {
	private static List<Bien> listBiens = new LinkedList<>();
	private static List<Bien> listArchive = new LinkedList<>();
	private static List<Proprietaire> listProprietaires = new LinkedList<>();
	private static List<String> listMessages = new ArrayList<>();
	private static Map<String, Object> criteriaMap = new HashMap<>(7);
	private boolean isAuthenticated = false;
	private String password = "123";

	public ImmoESI() {
		criteriaMap.put("type_transaction", null);
		criteriaMap.put("wilaya", null);
		criteriaMap.put("prix_min", -1);
		criteriaMap.put("prix_max", -1);
		criteriaMap.put("type_bien", "");
		criteriaMap.put("superficie_min", -1);
		criteriaMap.put("nbpieces_min", -1);
	}

	public void login(String password) {
		// String password = "1mM0€5i_=";
		if (password.equals(this.password))
			isAuthenticated = true;
		else
			System.err.println("Wrong Password");
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}

	public void logout() {
		isAuthenticated = false;
		System.out.println("Disconnected");
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public static List<Bien> getListBiens() {
		return listBiens;
	}

	public void afficherListBiens() {
		System.out.println("Liste des biens dans le système :");
		System.out.println("  Id   Type       Date Ajout");
		for (Bien bien : listBiens) {
			System.out.println("  " + bien.getIdentifiant() + "   " + bien.getClass().getSimpleName() + " "
					+ bien.getDateAjoutString());
		}
	}

	public void afficherPrixBiens() {
		System.out.println("Liste des biens dans le système :");
		System.out.println("  Id   Type              Date Ajout                              Prix Final");
		for (Bien bien : listBiens) {
			System.out.print("  " + bien.getIdentifiant() + "   " + bien.getClass().getSimpleName() + "              "
					+ bien.getDateAjoutString() + "             ");
			System.out.printf("%.3f DA\n", bien.getPrixFinal());
		}
	}

	public Bien fetchBien(int id) throws NullPointerException {
		if (isAuthenticated) {
			for (Bien bien : ImmoESI.listBiens) {
				if (bien.getIdentifiant() == id)
					return bien;
			}
		} else {
			System.err.println("Your Should be Authenticated as Admin first");
		}
		return null;
	}

	public boolean addBien(Bien b, Proprietaire proprietaire) {
		if (isAuthenticated) {
			if (listBiens.contains(b)) {
				System.err.println("Bien already existent in Liste de Biens");
				return false;
			}
			ImmoESI.getListProprietaires().get(ImmoESI.getListProprietaires().indexOf(proprietaire)).addBien(b);
			return listBiens.add(b);
		} else {
			System.err.println("Your Should be Authenticated as Admin first");
			return false;
		}
	}

	public boolean removeBien(Bien b, Proprietaire proprietaire) throws NullPointerException {
		if (isAuthenticated) {
			ImmoESI.getListProprietaires().get(ImmoESI.getListProprietaires().indexOf(proprietaire)).removeBien(b);
			return listBiens.remove(b);
		} else {
			System.err.println("Your Should be Authenticated as Admin first");
			return false;
		}
	}

	public boolean archiveBien(Bien b, Proprietaire proprietaire) {
		if (isAuthenticated) {
			if (removeBien(b, proprietaire))
				return listArchive.add(b);
			else {
				System.err.println("Something went wrong, could not archive Bien");
				return false;
			}
		} else {
			System.err.println("Your Should be Authenticated as Admin first");
			return false;
		}
	}

	public static List<Proprietaire> getListProprietaires() {
		return listProprietaires;
	}

	public void afficherListProprietaires() {
		if (isAuthenticated) {
			System.out.println("Liste des propriétaires dans le système :");
			for (Proprietaire p : listProprietaires) {
				p.afficherInfoProp();
			}
		} else {
			System.err.println("Your Should be Authenticated as Admin first");
		}
	}

	public void afficherListProprietairesBrief() {
		if (isAuthenticated) {
			System.out.println("Liste des propriétaires dans le système :");
			for (Proprietaire p : listProprietaires) {
				p.afficherInfoPropBrief();
			}
		} else {
			System.err.println("Your Should be Authenticated as Admin first");
		}
	}

	public void afficherListBiensProp(Proprietaire p) {
		if (isAuthenticated && p != null)
			p.afficherListBiens();
		else
			System.err.println("Your Should be Authenticated as Admin first");
	}

	public boolean addProp(Proprietaire p) {
		if (isAuthenticated) {
			if (listProprietaires.contains(p)) {
				System.err.println("Propriétaire already existent in Liste Proprietaires");
				return false;
			} else {
				return listProprietaires.add(p);
			}
		} else {
			System.err.println("Your Should be Authenticated as Admin first");
			return false;
		}
	}

	public boolean removeProp(Proprietaire p) {
		if (isAuthenticated) {
			System.out.println("Warning: Removing propriétaire will remove all of his Biens");
			if (listProprietaires.contains(p)) {
				for (Bien b : listBiens) {
					if (b.getProprietaire().equals(p))
						removeBien(b, p);
				}
				return listProprietaires.remove(p);
			} else {
				System.err.println("Something went wrong, unable to find Propriétaire");
				return false;
			}
		} else {
			System.err.println("Your Should be Authenticated as Admin first");
			return false;
		}
	}

	public static List<Bien> getListArchive() {
		return listArchive;
	}

	public void afficherArchive() {
		if (isAuthenticated) {
			System.out.println("Liste des Biens archivés :");
			System.out.println("  Id  Type        Date Ajout");
			for (Bien bien : listArchive) {
				System.out.println("  " + bien.getIdentifiant() + "   " + bien.getClass().getSimpleName()
						+ "                " + bien.getDateAjoutString());
			}
		} else
			System.err.println("Your Should be Authenticated as Admin first");
	}

	public static List<String> getListMessages() {
		return listMessages;
	}

	public void contacterAdmin() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Laissez nous un message :");
		String message = sc.nextLine();
		listMessages.add(message);
	}

	public void contacterAdmin(String message) {
		listMessages.add(message);
	}

	public void afficherListMessages() {
		/*
		 * if(isAuthenticated) { int i = 1;
		 * System.out.println("Liste des Messages de Clients :"); for (String message :
		 * listMessages) { System.out.println("Message "+i+" :\n"+message); i++; } }
		 * else System.err.println("Your Should be Authenticated as Admin first");
		 */
		int i = 1;
		System.out.println("Liste des Messages de Clients :");
		for (String message : listMessages) {
			System.out.println("Message " + i + " :\n" + message);
			i++;
		}
	}

	public void filter(Transaction transaction, Wilaya wilaya, double prixMax, double prixMin, String typeBien,
			float superficieMin, int nbPiecesMin) {
		criteriaMap.put("type_transaction", transaction);
		criteriaMap.put("wilaya", wilaya);
		criteriaMap.put("prix_max", prixMax);
		criteriaMap.put("prix_min", prixMin);
		criteriaMap.put("type_bien", typeBien);
		criteriaMap.put("superficie_min", superficieMin);
		criteriaMap.put("nbpieces_min", nbPiecesMin);

		for (Bien b : listBiens) {
			if (b.getNatureTransaction() == criteriaMap.get("type_transaction")
					|| criteriaMap.get("type_transaction") == null)
				if (b.getWilaya() == criteriaMap.get("wilaya") || criteriaMap.get("wilaya") == null)
					if (b.getPrixFinal() <= (double) criteriaMap.get("prix_max")
							|| (double) criteriaMap.get("prix_max") == -1)
						if (b.getPrixFinal() >= (double) criteriaMap.get("prix_min")
								|| (double) criteriaMap.get("prix_min") == -1)
							if (b.getClass().getSimpleName().equals(criteriaMap.get("type_bien").toString())
									|| criteriaMap.get("type_bien").equals(""))
								if (b.getSuperficie() >= (float) criteriaMap.get("superficie_min")
										|| (float) criteriaMap.get("superficies_min") == -1)
									if ((((Habitable) b).getNbPieces() >= (int) criteriaMap.get("nbpieces_min"))
											|| (int) criteriaMap.get("nbpieces_min") == -1)
										b.afficherInfo();
		}
	}
	/*
	 * public void filtrer() { boolean stop = false; Scanner sc = new
	 * Scanner(System.in); int choix = 0; String transaction = ""; String wilaya;
	 * double prixMinimum = 0; double prixMaximum = 0; String typeBien = ""; float
	 * superficieMin = 0; short nbPieceMin = 0;
	 * 
	 * System.out.print("choisir les criteres de recherche\n\n");
	 * System.out.print("1)type de transaction\n"); System.out.print("2)wilaya\n");
	 * System.out.print("3)prix minimum\n"); System.out.print("4)prix maximum\n");
	 * System.out.print("5)type du bien\n");
	 * System.out.print("6)superficie minimale\n");
	 * System.out.print("7)nombre minimal de pieces\n"); while (!stop) { choix =
	 * sc.nextInt(); if (choix < 1 || choix > 7) {
	 * System.out.print("entrée invalide \n1)reessayer		2)retour au menu");
	 * choix = sc.nextInt(); if (choix == 2) stop = true; } else { switch (choix) {
	 * case 1: System.out.print("choisir type de transaction\n\n");
	 * System.out.print("vente\n"); System.out.print("location\n");
	 * System.out.print("echange\n"); transaction = sc.nextLine(); break; case 2:
	 * System.out.print("choisir wilaya\n\n"); System.out.print("ALGER\n");
	 * System.out.print("TIZIOUZOU\n"); System.out.print("ORAN\n");
	 * System.out.print("BEJAIA\n"); wilaya = sc.nextLine(); break; case 3:
	 * System.out.print("entrer le prix minimum\n\n"); prixMinimum =
	 * sc.nextDouble(); break; case 4:
	 * System.out.print("entrer le prix maxximum\n\n"); prixMaximum =
	 * sc.nextDouble(); break; case 5: System.out.print("choisir type de bien\n\n");
	 * System.out.print("Maison\n"); System.out.print("Terrain\n");
	 * System.out.print("Appartement\n"); typeBien = sc.nextLine(); break; case 6:
	 * System.out.print("entrer la superficie minimale\n\n"); superficieMin =
	 * sc.nextFloat(); break; case 7:
	 * System.out.print("entrer le nombre minimal de pieces\n\n"); nbPieceMin =
	 * sc.nextShort(); break; }// end of switch
	 * System.out.print("1)choisir de nouveau		2)afficher les résultats\n");
	 * choix = sc.nextInt(); if (choix == 2) stop = true; } // end of else } // end
	 * of while for (Bien b : listBiens) { if
	 * (b.getNatureTransaction().getNomTrans().compareTo(transaction) == 0 ||
	 * transaction.equals("")) if (b.getWilaya().compareTo(wilaya) == 0 ||
	 * wilaya.equals("")) if (b.getPrixFinal() >= prixMinimum || prixMinimum == 0)
	 * if (b.getPrixFinal() <= prixMaximum || prixMaximum == 0) if
	 * (b.getClass().toString().compareTo(typeBien) == 0 || typeBien.equals("")) if
	 * (b.superficie >= superficieMin || superficieMin == 0) b.afficherInfo();
	 * 
	 * } }// end of filtrer
	 */
}