import java.util.ArrayList;

public class Joueur {
    // Attributs
    private String pseudo;
    private int position;
    private int nbTours;
    private int argent;
    private ArrayList<CasePropriete> listeProprietes; // Liste des propriétés possédées par le joueur

    public Joueur(String pseudo) {
        this.pseudo = pseudo;
        this.position = 0;
        this.nbTours = 0;
        this.argent = 10000;
        this.listeProprietes = new ArrayList<CasePropriete>();
    }

    // Méthodes pour accéder et modifier les attributs (getters/setters)

    public String getPseudo() {
        return pseudo;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNbTours() {
        return nbTours;
    }

    public void setNbTours(int nbTours) {
        this.nbTours = nbTours;
    }

    public int getArgent() {
        return argent;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public void gagnerArgent(int montant) {
        this.argent += montant;
    }

    public ArrayList<CasePropriete> getProprietes() {
        return listeProprietes;
    }

    public void setProprietes(ArrayList<CasePropriete> proprietes) {
        this.listeProprietes = proprietes;
    }

    // Méthode toString

    public String toString() {
        return "Joueur [pseudo=" + pseudo + ", position=" + position + ", nbTours=" + nbTours + ", argent=" + argent
                + "]";
    }

    // Autres méthodes

    public void deplacement(int deValue) {
        if (this.position + deValue >= 40) {
            this.position = (this.position + deValue) - 40;
        } else {
            this.position += deValue;
        }
    }

    public void acheterPropriete(CasePropriete propriete) {
        if (this.argent >= propriete.getPrix()) {
            this.argent -= propriete.getPrix();
            this.listeProprietes.add(propriete);
            propriete.setProprietaire(this);
        }
    }

    public void payerLoyer(CasePropriete propriete, int totalDe) {
        if (propriete.getType() == "Gare" || propriete.getType() == "Compagnie") {
            calculLoyer(totalDe);
        }
        if (this.argent >= propriete.getLoyer()) {
            this.argent -= propriete.getLoyer();
            propriete.getProprietaire().gagnerArgent(propriete.getLoyer());
        }
    }

    public void calculLoyer(int totalDe) {

        for (int i = 0; i < listeProprietes.size(); i++) {
            int multiplicateurGare = 0;
            int countCompagnie = 0;
            if (listeProprietes.get(i).getType() == "Gare") {
                multiplicateurGare++;
                listeProprietes.get(i).setLoyer(25 * multiplicateurGare);
            }
            if (listeProprietes.get(i).getType() == "Compagnie") {
                countCompagnie++;
                if (countCompagnie == 1) {
                    listeProprietes.get(i).setLoyer(4*totalDe);
                }
                if (countCompagnie == 2) {
                    listeProprietes.get(i).setLoyer(10*totalDe);
                }
            }
        }

    }

}
