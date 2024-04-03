package iteration4;

import java.util.Random;

public class CasePropriete extends Case {

    private int prix;
    private int loyer;
    private Joueur proprietaire;
    private static Random random = new Random();

    public CasePropriete(String type, String nom, int prix) {
        super(type, nom);
        this.prix = prix;
        this.loyer = prix / 10;
        this.proprietaire = null;
    }

    public int getPrix() {
        return prix;
    }

    public int getLoyer() {
        return loyer;
    }

    public void setLoyer(int loyer) {
        this.loyer = loyer;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String toString() {
        return "Case "+"type="+ getType() +", nom=" + getNom() + ", prix=" + getPrix() + ", loyer=" + getLoyer() +", proprietaire=" + getProprietaire() +"\n";
    }
    
}
