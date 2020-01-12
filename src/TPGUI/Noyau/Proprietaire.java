package TPGUI.Noyau;
import java.util.LinkedList;
import java.util.List;

public class Proprietaire {
    List<Bien> listBienProp = new LinkedList<>();
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String email;

    public Proprietaire(String nom, String prenom, String adresse, String tel, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public void afficherInfoProp() {
        System.out.println("Nom :" + this.nom);
        System.out.println("Prenom :" + this.prenom);
        System.out.println("Adresse :" + this.adresse);
        System.out.println("Tel :" + this.tel);
        System.out.println("Email :" + this.email);
        this.afficherListBiens();
        System.out.println("-----------------------------------------");
    }
    public void afficherInfoPropBrief() {
        System.out.println("Nom :" + this.nom);
        System.out.println("Prenom :" + this.prenom);
        System.out.println("Adresse :" + this.adresse);
        System.out.println("Tel :" + this.tel);
        System.out.println("Email :" + this.email);
        System.out.println("-----------------------------------------");
    }

    public boolean addBien(Bien b) {
        return listBienProp.add(b);
    }

    public boolean removeBien(Bien b) {
        return listBienProp.remove(b);
    }

    public void afficherListBiens() {
        System.out.println("Liste des biens de "+nom+" "+prenom+" :");
        for (Bien bien : listBienProp) {
            bien.afficherInfo();
        }
    }
}