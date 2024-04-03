package iteration4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
            // Créer un ObjectMapper (objet de Jackson pour la conversion JSON)
            ObjectMapper objectMapper = new ObjectMapper();

            // Lire le fichier JSON et le mapper vers un JsonNode
            JsonNode jsonNode = objectMapper.readTree(new File("./plateau.json"));

            // Parcourir le JSON et créer les cases correspondantes
            for (JsonNode node : jsonNode) {
                String type = node.get("type").asText();
                int position = node.get("position").asInt();
                String nom = node.get("nom").asText();
                Case nouvelleCase = null;

                if (type.equals("Speciale")) {
                    nouvelleCase = new CaseSpeciale(position, type, nom);
                    this.plateau.add(nouvelleCase);
                    continue;
                }
                else{
                    nom = node.get("nom").asText();
                    prix = node.get("prix").asInt();
                    nouvelleCase = new CasePropriete(position, type, nom, prix);
                    break;
                }

                if (nouvelleCase != null) {
                    this.plateau.add(nouvelleCase);
                }
            }
        } catch (IOException e) {
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