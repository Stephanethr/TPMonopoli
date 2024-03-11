import java.util.List;

public class Joueur {
    // Attributs
    private String pseudo;
    private int position;
    private int nbTours;
    private int argent;
    private List<Propriete> listeProprietes; // Liste des propriétés possédées par le joueur

    public Joueur(String pseudo) {
        this.pseudo = pseudo;
        this.position = 0;
        this.nbTours = 0;
        this.argent = 1500;
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
        // this.argent = argent;
    }

    public List<Propriete> getProprietes() {
        return listeProprietes;
    }

    public void setProprietes(List<Propriete> proprietes) {
        this.listeProprietes = proprietes;
    }

    // Méthode toString

    public String toString() {
        return "Joueur [pseudo=" + pseudo + ", position=" + position + ", nbTours=" + nbTours + ", argent=" + argent
                + ", listeProprietes=" + listeProprietes + "]";
    }

    // Autres méthodes

    public void deplacement(int deValue) {
        if (this.position + deValue >= 40) {
            this.position = (this.position + deValue) - 40;
        } else {
            this.position += deValue;
        }
    }
}
