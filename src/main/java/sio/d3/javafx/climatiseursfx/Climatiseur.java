package sio.d3.javafx.climatiseursfx;

public class Climatiseur {
    private int id;
    private String marque;
    private String modele;
    private int puissance;
    private int smin;
    private int smax;
    public Climatiseur(String marque, int puissance, String modele, int smin, int smax, int id) {

        this.marque = marque;
        this.modele = modele;
        this.puissance = puissance;
        this.smin = smin;
        this.smax = smax;
        this.id = id;


        if(this.puissance == 7000){
            this.smin=7;
            this.smax=15;
        }

        if(this.puissance == 9000){
            this.smin=7;
            this.smax=15;
        }

        if(this.puissance == 7000){
            this.smin=15;
            this.smax=25;
        }

        if(this.puissance == 12000){
            this.smin=25;
            this.smax=35;
        }

        if(this.puissance == 18000){
            this.smin=35;
            this.smax=50;
        }

        if(this.puissance == 24000){
            this.smin=50;
            this.smax=70;
        }

        if(this.puissance == 30000){
            this.smin=70;
            this.smax=80;
        }
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public int getSmin() {
        return smin;
    }

    public void setSmin(int smin) {
        this.smin = smin;
    }

    public int getSmax() {
        return smax;
    }

    public void setSmax(int smax) {
        this.smax = smax;
    }

    public String getModele() {
        return modele;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }
}
