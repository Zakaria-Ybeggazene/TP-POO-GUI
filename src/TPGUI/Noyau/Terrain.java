package TPGUI.Noyau;

import java.time.LocalDateTime;

public class Terrain extends Bien {
    private String statutJuridique; //à revoir
    private int nbFacades;

    /*
    public Terrain(String adresseExacte,
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
                   String statutJuridique,
                   int nbFacades) {
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
        this.statutJuridique = statutJuridique;
        this.nbFacades = nbFacades;
    }
     */

    public Terrain(String adresseExacte,
                   Wilaya wilaya,
                   float superficie,
                   Proprietaire proprietaire,
                   Transaction natureTransaction,
                   double prix,
                   boolean prixNegociable,
                   String descriptif,
                   LocalDateTime dateAjout,
                   boolean hasPhotos,
                   String statutJuridique,
                   int nbFacades) {
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
        this.statutJuridique = statutJuridique;
        this.nbFacades = nbFacades;
    }

    public Terrain(String adresseExacte,
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
                   String statutJuridique,
                   int nbFacades) {
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
        this.statutJuridique = statutJuridique;
        this.nbFacades = nbFacades;
    }
    @Override
    public double getPrixNet() {
        return prix;
    }

    @Override
    public double getPrixFinal() {
        double prixFinal = super.getPrixFinal();
        if(natureTransaction == Transaction.VENTE && nbFacades > 1) prixFinal += 0.005 * nbFacades;
        return prixFinal;
    }

    public String getStatutJuridique() {
        return statutJuridique;
    }

    public int getNbFacades() {
        return nbFacades;
    }

    @Override
    public void afficherInfo() {
        super.afficherInfo();
        System.out.println("Lien vers statut juridique : "+statutJuridique);
        System.out.println("Nombre de façades : "+nbFacades);
    }
}