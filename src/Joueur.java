public class Joueur {
    private String pseudo;
    private int position;

    public Joueur(String pseudo) {
        this.pseudo = pseudo;
        this.position = 0;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void deplacement(int deValue) {
        if (this.position + deValue >= 40) {
            this.position = (this.position + deValue) - 40;
        } else {
            this.position += deValue;
        }
    }
}
