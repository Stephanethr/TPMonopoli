package iteration4.controleur;

import java.util.ArrayList;
import iteration4.model.Carte;
import iteration4.model.Case;
import iteration4.model.CasePropriete;
import iteration4.model.De;
import iteration4.model.Game;
import iteration4.model.Joueur;

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

    // Méthodes joueur
    public ArrayList<Joueur> getListeJoueur() {
        return game.getListeJoueur();
    }

    public void acheterPropriete(Joueur joueur, CasePropriete propriete) {
        joueur.acheterPropriete(propriete);
    }

    public String afficherProprietes(Joueur joueur) {
        return joueur.afficherProprietes();
    }
    public void deplacement(Joueur joueur, int totalDe) {
        joueur.deplacement(totalDe);
    }

    public String getPseudo(Joueur joueur) {
        return joueur.getPseudo();
    }

    public int getPosition(Joueur joueur) {
        return joueur.getPosition();
    }

    public int getArgent(Joueur joueur) {
        return joueur.getArgent();
    }

    public void payer(Joueur joueur, int montant) {
        joueur.payer(montant);
    }

    public void gagnerArgent(Joueur joueur, int montant) {
        joueur.gagnerArgent(montant);
    }

    public void payerLoyer(Joueur joueur, CasePropriete propriete, int totalDe) {
        joueur.payerLoyer(propriete, totalDe);
    }

    public void allerEnPrison(Joueur joueur) {
        joueur.allerEnPrison();
    }

    public void sortirDePrison(Joueur joueur) {
        joueur.sortirDePrison();
    }

    public boolean getEnPrison(Joueur joueur) {
        return joueur.getEnPrison();
    }

    public void incrementerNbToursPrison(Joueur joueur) {
        joueur.incrementerNbToursPrison();
    }

    public int getNbToursPrison(Joueur joueur) {
        return joueur.getNbToursPrison();
    }

    // infos des tours
    public int getCompteurTours() {
        return game.getCompteurTours();
    }

    public int getNbTours() {
        return game.getNbTours();
    }

    public void setCompteurTours(int compteurTours) {
        game.setCompteurTours(compteurTours);
    }


    // plateau et cartes
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

    public String actionCarte(Carte carte, Joueur joueur) {
        ArrayList<Joueur> listJoueurs = new ArrayList<Joueur>(getListeJoueur());
        listJoueurs.remove(joueur);
        return game.actionCarte(carte, joueur, listJoueurs);
        
    }

   
    

    // Dés
    public De[] getDes() {
        return game.getDes();
    }

    public void lancerDes() {
        game.lancerDes();
    }

    public int totalDes() {
        return getDes()[0].getValue() + getDes()[1].getValue();
    }

    // Méthodes de jeu
    public Joueur getJoueurGagnant() {
        return game.getJoueurGagnant();
    }

    public String getNomCase(Case caseCourante) {
        return caseCourante.getNom();
    }


    public boolean isGameOver() {
        return game.isGameOver();
    }

    public void incrementerCompteurTours() {
        game.incrementerCompteurTours();
    }

    public Case getCaseCourante(Joueur joueur) {
        return game.getCaseCourante(joueur);
    }

    public boolean canReleaseFromJail(Joueur joueur) {
        if (getEnPrison(joueur)) {
            int de1 = getDes()[0].getValue();
            int de2 = getDes()[1].getValue();
            return isDouble(de1, de2);
        }
        return false;
    }

    public boolean isDouble(int de1, int de2) {
        return de1 == de2;
    }

}
