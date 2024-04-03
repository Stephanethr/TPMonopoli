package iteration2;

import java.util.Random;

public class CasePropriete extends Case {

    private int prix;
    private int loyer;
    private Joueur proprietaire;
    private static Random random = new Random();

    public CasePropriete(int position) {
        super(position);
        this.prix = random.nextInt(1000) + 100;
        this.loyer = prix / 10;
        this.proprietaire = null;
    }

    public int getPrix() {
        return prix;
    }

    public int getLoyer() {
        return loyer;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String toString() {
        return "Propriete [position=" + getPosition() + ", prix=" + getPrix() + ", loyer=" + getLoyer() +", proprietaire=" + getProprietaire() + "]";
    }

}
