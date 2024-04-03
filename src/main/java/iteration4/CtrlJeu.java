package iteration4;

import java.util.ArrayList;

public class CtrlJeu {
    private Game game;
    int nbToursMax;
    int nbJoueurs;

    public CtrlJeu(int nbToursMax, int nbJoueurs) {
        createGame(nbToursMax, nbJoueurs);
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

    // Cartes

    public ArrayList<Carte> getChance() {
        return game.getChance();
    }

    public ArrayList<Carte> getCaisseCommunaute() {
        return game.getCaisseCommunaute();
    }

    public Carte piocherCarteChance() {
        return game.tirerCarteChance();
    }

    public Carte piocherCarteCaisseCommunaute() {
        return game.tirerCarteCaisseCommunaute();
    }

   
    

    // Dés

    public De[] getDes() {
        return game.getDes();
    }

    public void setCompteurTours(int compteurTours) {
        game.setCompteurTours(compteurTours);
    }

    public void payerLoyer(Joueur joueur, CasePropriete propriete, int totalDe) {
        joueur.payerLoyer(propriete, totalDe);
    }

    public void acheterPropriete(Joueur joueur, CasePropriete propriete) {
        joueur.acheterPropriete(propriete);
    }

    public void deplacement(Joueur joueur, int totalDe) {
        joueur.deplacement(totalDe);
    }

    public Joueur getJoueurGagnant() {
        return game.getJoueurGagnant();
    }

    
}
