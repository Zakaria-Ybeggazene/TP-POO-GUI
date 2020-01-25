package TPGUI.Noyau;
import java.time.LocalDateTime;

public class Maison extends Habitable {
    private int nbEtages;
    private boolean hasGarage, hasJardin, hasPiscine;
    private float superficieHabitable;

    /*
    public Maison(String adresseExacte,
                  Wilaya wilaya,
                  float superficie,
                  Proprietaire proprietaire,
                  Transaction natureTransaction,
                  double prix,
                  boolean prixNegociable,
                  String descriptif,
                  LocalDateTime dateAjout,
                  boolean hasPhotos,
                  URL[] tabPhotos,
                  int nbPieces,
                  boolean estMeuble,
                  int nbEtages,
                  boolean hasGarage,
                  boolean hasJardin,
                  boolean hasPiscine,
                  float superficieHabitable) {
        super(adresseExacte,
                wilaya,
                superficie,
                proprietaire,
                natureTransaction,
                prix,
                prixNegociable,
                descriptif,
                dateAjout,
                hasPhotos,
                tabPhotos,
                nbPieces,
                estMeuble);
        this.nbEtages = nbEtages;
        this.hasGarage = hasGarage;
        this.hasJardin = hasJardin;
        this.hasPiscine = hasPiscine;
        this.superficieHabitable = superficieHabitable;
    }
     */
    public Maison(String adresseExacte,
                  Wilaya wilaya,
                  float superficie,
                  Proprietaire proprietaire,
                  Transaction natureTransaction,
                  double prix,
                  boolean prixNegociable,
                  String descriptif,
                  LocalDateTime dateAjout,
                  boolean hasPhotos,
                  int nbPieces,
                  boolean estMeuble,
                  int nbEtages,
                  boolean hasGarage,
                  boolean hasJardin,
                  boolean hasPiscine,
                  float superficieHabitable) {
        super(adresseExacte,
                wilaya,
                superficie,
                proprietaire,
                natureTransaction,
                prix,
                prixNegociable,
                descriptif,
                dateAjout,
                hasPhotos,
                nbPieces,
                estMeuble);
        this.nbEtages = nbEtages;
        this.hasGarage = hasGarage;
        this.hasJardin = hasJardin;
        this.hasPiscine = hasPiscine;
        this.superficieHabitable = superficieHabitable;
    }

    public Maison(String adresseExacte,
                  Wilaya wilaya,
                  Wilaya wilayaEchange,
                  float superficie,
                  Proprietaire proprietaire,
                  Transaction natureTransaction,
                  double prix,
                  boolean prixNegociable,
                  String descriptif,
                  LocalDateTime dateAjout,
                  boolean hasPhotos,
                  int nbPieces,
                  boolean estMeuble,
                  int nbEtages,
                  boolean hasGarage,
                  boolean hasJardin,
                  boolean hasPiscine,
                  float superficieHabitable) {
        super(adresseExacte,
                wilaya,
                wilayaEchange,
                superficie,
                proprietaire,
                natureTransaction,
                prix,
                prixNegociable,
                descriptif,
                dateAjout,
                hasPhotos,
                nbPieces,
                estMeuble);
        this.nbEtages = nbEtages;
        this.hasGarage = hasGarage;
        this.hasJardin = hasJardin;
        this.hasPiscine = hasPiscine;
        this.superficieHabitable = superficieHabitable;
    }

    @Override
    public double getPrixNet() {
        return prix;
    }

    @Override
    public double getPrixFinal() {
        double prixFinal = super.getPrixFinal();
        switch (natureTransaction) {
            case VENTE:
            case ECHANGE:
                if(hasGarage || hasJardin || hasPiscine) prixFinal += 0.005 * prix;
                break;
            case LOCATION:
                if(hasPiscine) prixFinal += 50000;
                break;
        }
        return prixFinal;
    }
    
    @Override
    public int getNbPieces() {
        return nbPieces;
    }

    @Override
    public boolean estMeuble() {
        return estMeuble;
    }

    public int getNbEtages() {
        return nbEtages;
    }

    public boolean hasGarage() {
        return hasGarage;
    }

    public boolean hasJardin() {
        return hasJardin;
    }

    public boolean hasPiscine() {
        return hasPiscine;
    }

    public float getSuperficieHabitable() {
        return superficieHabitable;
    }

    @Override
    public void afficherInfo() {
        super.afficherInfo();
        System.out.println("Nombre d'étages : "+nbEtages);
        if(hasGarage) {
            System.out.println("Garage : Oui");
        } else {
            System.out.println("Garage : Non");
        }
        if(hasJardin) {
            System.out.println("Jardin : Oui");
        } else {
            System.out.println("Jardin : Non");
        }
        if(hasPiscine) {
            System.out.println("Piscine : Oui");
        } else {
            System.out.println("Piscine : Non");
        }
        System.out.println("Superficie habitable : "+superficieHabitable);
    }
}