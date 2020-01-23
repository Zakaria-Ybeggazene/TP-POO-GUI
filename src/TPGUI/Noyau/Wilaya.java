package TPGUI.Noyau;
public enum Wilaya {
    ALGER(100000),
    TIZIOUZOU(30000),
    BEJAIA(60000);

    private double prixMetreCarre;

    Wilaya(double prixMetreCarre) {
        this.prixMetreCarre = prixMetreCarre;
    }

    public double getPrixMetreCarre() {
        return prixMetreCarre;
    }

    public void setPrixMetreCarre(double prixMetreCarre) {
        this.prixMetreCarre = prixMetreCarre;
    }
}