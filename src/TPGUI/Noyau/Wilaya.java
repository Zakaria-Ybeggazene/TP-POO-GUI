package TPGUI.Noyau;
public enum Wilaya {
    WILAYA1(100000),
    WILAYA2(30000),
    WILAYA3(60000);

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