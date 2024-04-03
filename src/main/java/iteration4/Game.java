package iteration4;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.IOException;
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
        createPlateau();
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

    public void createPlateau() {
        this.plateau = new ArrayList<Case>();

        try {
            // Créer un objet JSONParser pour lire le fichier JSON
            JSONParser parser = new JSONParser();
            String chemin = System.getProperty("user.dir") + "/src/main/java/iteration4/plateau.json";

            // Lire le fichier JSON
            Object obj = parser.parse(new FileReader(chemin));

            // Convertir l'objet JSON en JSONObject
            JSONArray jsonArray = (JSONArray) obj;

            // Parcourir le JSONArray et créer les cases correspondantes
            for (Object o : jsonArray) {
                JSONObject node = (JSONObject) o;

                String type = (String) node.get("type");
                String nom = (String) node.get("nom");

                Case nouvelleCase = null;

                if (type.equals("Speciale")) {
                    nouvelleCase = new CaseSpeciale(type, nom);
                } else {
                    int prix = ((Long) node.get("prix")).intValue();
                    nouvelleCase = new CasePropriete(type, nom, prix);
                }

                if (nouvelleCase != null) {
                    this.plateau.add(nouvelleCase);
                }
            }
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
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