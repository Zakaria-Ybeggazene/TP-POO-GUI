package TPGUI.Noyau;
import java.net.URL;

import java.time.LocalDateTime;

public abstract class Bien {
    private static int idCounter = 0; // à revoir
    protected int identifiant; // pour le moment on considère l'identifiant comme un entier à incrémenter
    protected String adresseExacte;
    protected Wilaya wilaya;
    protected Wilaya wilayaEchange;
    protected float superficie;
    protected Proprietaire proprietaire;
    protected Transaction natureTransaction;
    protected double prix;
    protected boolean prixNegociable;
    protected String descriptif;
    protected LocalDateTime dateAjout;
    protected boolean hasPhotos;
    protected URL[]tabPhotos;

    //Constructeur s'il a des photos
    /*public Bien(String adresseExacte,
                Wilaya wilaya,
                float superficie,
                Proprietaire proprietaire,
                Transaction natureTransaction,
                double prix,
                boolean prixNegociable,
                String descriptif,
                LocalDateTime dateAjout,
                boolean hasPhotos,
                URL[] tabPhotos) {
        this.identifiant = Bien.idCounter;
        idCounter++;
        this.adresseExacte = adresseExacte;
        this.wilaya = wilaya;
        this.superficie = superficie;
        this.proprietaire = proprietaire;
        this.natureTransaction = natureTransaction;
        this.prix = prix;
        this.prixNegociable = prixNegociable;
        this.descriptif = descriptif;
        this.dateAjout = dateAjout;
        this.hasPhotos = hasPhotos;
        this.tabPhotos = tabPhotos;
    }
     */

    //constructeur s'il n'a pas de photos
    public Bien(String adresseExacte,
                Wilaya wilaya,
                float superficie,
                Proprietaire proprietaire,
                Transaction natureTransaction,
                double prix,
                boolean prixNegociable,
                String descriptif,
                LocalDateTime dateAjout,
                boolean hasPhotos) {
        this.identifiant = Bien.idCounter;
        idCounter++;
        this.adresseExacte = adresseExacte;
        this.wilaya = wilaya;
        this.superficie = superficie;
        this.proprietaire = proprietaire;
        this.natureTransaction = natureTransaction;
        this.prix = prix;
        this.prixNegociable = prixNegociable;
        this.descriptif = descriptif;
        this.dateAjout = dateAjout;
        this.hasPhotos = hasPhotos;
    }
    // Si on ajoute la wilaya d'échange
    public Bien(String adresseExacte,
                Wilaya wilaya,
                Wilaya wilayaEchange,
                float superficie,
                Proprietaire proprietaire,
                Transaction natureTransaction,
                double prix,
                boolean prixNegociable,
                String descriptif,
                LocalDateTime dateAjout,
                boolean hasPhotos) {
        this.identifiant = Bien.idCounter;
        idCounter++;
        this.adresseExacte = adresseExacte;
        this.wilaya = wilaya;
        this.wilayaEchange = wilayaEchange;
        this.superficie = superficie;
        this.proprietaire = proprietaire;
        this.natureTransaction = natureTransaction;
        this.prix = prix;
        this.prixNegociable = prixNegociable;
        this.descriptif = descriptif;
        this.dateAjout = dateAjout;
        this.hasPhotos = hasPhotos;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public String getAdresseExacte() {
        return adresseExacte;
    }

    public Wilaya getWilaya() {
        return wilaya;
    }

    public float getSuperficie() {
        return superficie;
    }

    public Proprietaire getProprietaire() {
        return proprietaire;
    }

    public Transaction getNatureTransaction() {
        return natureTransaction;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public boolean hasPhotos() {
        return hasPhotos;
    }

    public boolean isPrixNegociable() {
        return prixNegociable;
    }

    public void displayDateAjout() {
        System.out.println("Bien ajouté le : "+dateAjout.getDayOfWeek().toString()+", "+dateAjout.getDayOfMonth()+
                " "+dateAjout.getMonth().toString()+" "+dateAjout.getYear());
    }

    public abstract double getPrixNet();
    public double getPrixFinal() {
        double prixFinal = 0;
        switch (natureTransaction) {
            case VENTE:
            case ECHANGE:
                if(prix < 5000000) prixFinal = wilaya.getPrixMetreCarre() < 50000 ? 1.03 * prix : 1.035 * prix;
                else if(prix >= 5000000 && prix <= 15000000)
                    prixFinal = wilaya.getPrixMetreCarre() < 50000 ? 1.02 * prix : 1.025 * prix;
                else prixFinal = wilaya.getPrixMetreCarre() < 70000 ? 1.01 * prix : 1.02 * prix;
                break;
            case LOCATION:
                if(superficie < 60) prixFinal = wilaya.getPrixMetreCarre() < 50000 ? 1.01 * prix : 1.015 * prix;
                else if(superficie >= 60 && superficie <= 150)
                    prixFinal = wilaya.getPrixMetreCarre() < 50000 ? 1.02 * prix : 1.025 * prix;
                else prixFinal = wilaya.getPrixMetreCarre() < 50000 ? 1.03 * prix : 1.035 * prix;
                break;
        }
        if(natureTransaction == Transaction.ECHANGE && wilayaEchange != wilaya) prixFinal += 0.0025 * prix;
        return prixFinal;
    }

    public String getDateAjoutString() {
        String dateString;
        dateString = dateAjout.getDayOfWeek().toString()+", "+dateAjout.getDayOfMonth()+
                " "+dateAjout.getMonth().toString()+" "+dateAjout.getYear()+" at "+dateAjout.getHour()+
                ":"+dateAjout.getMinute()+":"+dateAjout.getSecond();
        return dateString;
    }
    public void afficherInfo() {
        System.out.println("---------------------------------------------------------");
        System.out.println("Id : "+identifiant);
        System.out.println("Adresse Exacte du bien : "+adresseExacte);
        System.out.println("Superficie : "+superficie);
        System.out.println("Info Propriétaire :\n\tNom : "+proprietaire.getNom()+"\n\tPrenom : "+proprietaire.getPrenom()+
                "\n\tAdresse : "+proprietaire.getAdresse()+"\n\tEmail : "+proprietaire.getEmail()+"\n\tTel : "
                +proprietaire.getTel());
        System.out.println("Nature de la transaction : "+natureTransaction.getNomTrans());
        System.out.println("Prix négociable : "+prixNegociable);
        System.out.println("Descriptif : "+descriptif);
        System.out.println("Date Ajout : "+getDateAjoutString());
        System.out.println("Possède des photos : "+hasPhotos);
        if (hasPhotos) {
            System.out.println("Lien vers photos : " + tabPhotos);
        } else {
            System.out.println("Lien vers photos : indisponible");
        }
    }

    public void setAdresseExacte(String adresseExacte) {
        this.adresseExacte = adresseExacte;
    }

    public void setWilaya(Wilaya wilaya) {
        this.wilaya = wilaya;
    }

    public void setWilayaEchange(Wilaya wilayaEchange) {
        this.wilayaEchange = wilayaEchange;
    }

    public void setSuperficie(float superficie) {
        this.superficie = superficie;
    }

    public void setProprietaire(Proprietaire proprietaire) {
        this.proprietaire = proprietaire;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public void setPrixNegociable(boolean prixNegociable) {
        this.prixNegociable = prixNegociable;
    }

    public void setHasPhotos(boolean hasPhotos) {
        this.hasPhotos = hasPhotos;
    }

    public void setTabPhotos(URL[] tabPhotos) {
        this.tabPhotos = tabPhotos;
    }
}