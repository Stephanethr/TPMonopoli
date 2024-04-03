package iteration3;
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
            Joueur joueur = new Joueur("joueur " + (i + 1));
            listeJoueur.add(joueur);
        }
    }

    public ArrayList<Case> getPlateau() {
        return plateau;
    }

    public void setPlateau() {
        this.plateau = new ArrayList<Case>();
        while (plateau.size() < 40) {
            plateau.add(new CasePropriete(plateau.size(), "Propriete"));
        }
        // Cases départ
        this.plateau.set(0, new CaseSpeciale(0, "Depart"));
        // Cases Gare
        this.plateau.set(5, new CasePropriete(5, "Gare", 250));
        this.plateau.set(15, new CasePropriete(15, "Gare", 250));
        this.plateau.set(25, new CasePropriete(25, "Gare", 250));
        this.plateau.set(35, new CasePropriete(35, "Gare", 250));

        // Cases compagnie
        this.plateau.set(12, new CasePropriete(12, "Compagnie", 250));
        this.plateau.set(28, new CasePropriete(28, "Compagnie", 250));
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

    public void createDes() {
        for (int i = 0; i < des.length; i++) {
            des[i] = new De();
        }
    }

    public De[] getDes() {
        for (int i = 0; i < des.length; i++) {
            des[i].lancer();
        }
        return des;
    }

    public Joueur getJoueurGagnant() {
        Joueur joueurGagnant = listeJoueur.get(0);
        for (Joueur joueur : listeJoueur) {
            if (joueur.getArgent() > joueurGagnant.getArgent()) {
                joueurGagnant = joueur;
            }
        }
        return joueurGagnant;
    }

}