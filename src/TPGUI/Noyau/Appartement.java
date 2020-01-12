package TPGUI.Noyau;
import java.time.LocalDateTime;

public class Appartement extends Habitable {
    private int etage;
    private boolean isDuplex;
    private boolean hasAscenseur;

    /*
    public Appartement(String adresseExacte,
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
                       int etage,
                       boolean isDuplex,
                       boolean hasAscenseur) {
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
        this.etage = etage;
        this.isDuplex = isDuplex;
        if(nbPieces == 1 && isDuplex) {
            System.out.println("Erreur : un studio ne peux pas être un duplex\n"); // rajouter des traitements
        }
        this.hasAscenseur = hasAscenseur;
    }
     */
    public Appartement(String adresseExacte,
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
                       int etage,
                       boolean isDuplex,
                       boolean hasAscenseur) {
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
        this.etage = etage;
        this.isDuplex = isDuplex;
        if(nbPieces == 1 && isDuplex) {
            System.out.println("Erreur : un studio ne peux pas être un duplex\n"); // rajouter des traitements
        }
        this.hasAscenseur = hasAscenseur;
    }

    public Appartement(String adresseExacte,
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
                       int etage,
                       boolean isDuplex,
                       boolean hasAscenseur) {
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
        this.etage = etage;
        this.isDuplex = isDuplex;
        if(nbPieces == 1 && isDuplex) {
            System.out.println("Erreur : un studio ne peux pas être un duplex\n"); // rajouter des traitements
        }
        this.hasAscenseur = hasAscenseur;
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
                if(etage >= 0 && etage <= 2) prixFinal += 50000;
                break;
            case LOCATION:
                if(etage >= 0 && etage <= 2) prixFinal += 5000;
                else if(!hasAscenseur && etage >= 6) prixFinal -= superficie * 500;
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

    public boolean isDuplex() {
        return isDuplex;
    }

    public boolean hasAscenseur() {
        return hasAscenseur;
    }

    @Override
    public void afficherInfo() {
        super.afficherInfo();
        System.out.println("Etage : "+etage);
        if(isDuplex) {
            System.out.println("Type Appartement : Duplex");
        } else {
            System.out.println("Type Appartement : Simplex");
        }
    }
}