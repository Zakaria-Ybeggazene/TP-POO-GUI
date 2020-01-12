package TPGUI.Noyau;
public enum Transaction {
    LOCATION("Location"),
    VENTE("Vente"),
    ECHANGE("Echange");

    private String nomTrans;

    Transaction(String nomTrans) {
        this.nomTrans = nomTrans;
    }

    public String getNomTrans() {
        return nomTrans;
    }
}