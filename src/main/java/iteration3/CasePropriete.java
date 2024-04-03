package iteration3;
import java.util.Random;

public class CasePropriete extends Case {

    private int prix;
    private int loyer;
    private Joueur proprietaire;
    private static Random random = new Random();

    public CasePropriete(int position, String type) {
        super(position, type);
        this.prix = random.nextInt(400) + 30;
        this.loyer = prix / 10;
        this.proprietaire = null;
    }
    public CasePropriete(int position, String type, int prix) {
        super(position, type);
        this.prix = prix;
        this.loyer = prix / 10;
        this.proprietaire = null;
    }

    public CasePropriete(int position, String type, int prix, int loyer) {
        super(position, type);
        this.prix = prix;
        this.loyer = loyer;
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
        return "Propriete position=" + getPosition() + ",type="+ getType() + " prix=" + getPrix() + ", loyer=" + getLoyer() +", proprietaire=" + getProprietaire() +"\n";
    }

}
