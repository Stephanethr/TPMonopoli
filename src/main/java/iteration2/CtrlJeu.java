package iteration2;

import java.util.ArrayList;

public class CtrlJeu {
    private Game game;

    public CtrlJeu() {

    }

    public void createGame(int nbTours, int nbJoueurs) {
        game = new Game(nbTours, nbJoueurs);

    }

    public ArrayList<Joueur> getListeJoueur() {
        return game.getListeJoueur();
    }

    public int getCompteurTours() {
        return game.getCompteurTours();
    }

    public int getNbTours() {
        return game.getNbTours();
    }

    public ArrayList<Case> getPlateau() {
        return game.getPlateau();
    }

    public De[] getDes() {
        return game.getDes();
    }

    public void setCompteurTours(int compteurTours) {
        game.setCompteurTours(compteurTours);
    }

    public void setPlateau() {
        game.setPlateau();
    }

    public void createDes() {
        game.createDes();
    }
}
