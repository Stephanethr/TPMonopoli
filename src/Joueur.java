public class Joueur {
    private String pseudo;
    private int position;
    private int nbTours;

    public Joueur(String pseudo) {
        this.pseudo = pseudo;
        this.position = 0;
        this.nbTours = 0;
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

    // Autres méthodes

    public void deplacement(int deValue) {
        if (this.position + deValue >= 40) {
            this.position = (this.position + deValue) - 40;
        } else {
            this.position += deValue;
        }
    }
}
