package TPGUI.Noyau;
import java.time.LocalDateTime;

public abstract class Habitable extends Bien {
    protected int nbPieces;
    protected boolean estMeuble;

    /*
    public Habitable(String adresseExacte,
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
                     boolean estMeuble) {
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
                tabPhotos);
        this.nbPieces = nbPieces;
        this.estMeuble = estMeuble;
    }
     */
    public Habitable(String adresseExacte,
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
                     boolean estMeuble) {
        super(adresseExacte,
                wilaya,
                superficie,
                proprietaire,
                natureTransaction,
                prix,
                prixNegociable,
                descriptif,
                dateAjout,
                hasPhotos);
        this.nbPieces = nbPieces;
        this.estMeuble = estMeuble;
    }

    public Habitable(String adresseExacte,
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
                     boolean estMeuble) {
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
                hasPhotos);
        this.nbPieces = nbPieces;
        this.estMeuble = estMeuble;
    }
    public abstract int getNbPieces();
    public abstract boolean estMeuble();
    public void afficherInfo() {
        super.afficherInfo();
        System.out.println("Nombre de pièces : "+nbPieces);
        if (estMeuble) {
            System.out.println("Meublé : Oui");
        } else {
            System.out.println("Meublé : Non");
        }
    }
}