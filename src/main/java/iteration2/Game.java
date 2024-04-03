package iteration2;
import java.util.ArrayList;


/**
 * Game
 */
public class Game {
    private int nbTours;
    private ArrayList<Joueur> listeJoueur;
    private int compteurTours = 1;
    private ArrayList<Case> plateau;
    De des[] = new De[2];

    public Game(int nbTours, int nbJoueurs) {
        this.nbTours = nbTours;
        setListJoueurs(nbJoueurs);
        setPlateau();
        createDes();
    }

    public void setListJoueurs(int nbJoueurs) {
        this.listeJoueur = new ArrayList<Joueur>();
        for (int i = 0; i < nbJoueurs; i++) {
            Joueur joueur = new Joueur("joueur " + (i+1));
            listeJoueur.add(joueur);
        }
    }

    public ArrayList<Case> getPlateau() {
        return plateau;
    }

    public void setPlateau() {
        this.plateau = new ArrayList<Case>();
        this.plateau.add(new CaseSpeciale(0));
        while (plateau.size() < 40) {
            plateau.add(new CasePropriete(plateau.size()));
        }
    }

    public int getNbTours() {
        return nbTours;
    }

    public ArrayList<Joueur> getListeJoueur() {
        return listeJoueur;
    }

    public int getCompteurTours() {
        return compteurTours;
    }

    public void setCompteurTours(int compteurTours) {
        this.compteurTours = compteurTours;
    }

    public void createDes(){
        for (int i = 0; i < des.length; i++) {
            des[i] = new De();
        }
    }

    public De[] getDes(){
        for (int i = 0; i < des.length; i++) {
            des[i].lancer();
        }
        return des;
    }
}