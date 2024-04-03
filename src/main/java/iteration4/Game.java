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
    private ArrayList<Carte> chance;
    private ArrayList<Carte> caisseCommunaute;
    De des[] = new De[2];
    public Object carte;

    public Game(int nbTours, int nbJoueurs) {
        this.nbTours = nbTours;
        setListJoueurs(nbJoueurs);
        createPlateau();
        createDes();
        createCartes();
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

    /*
     * Créer les cartes du jeu a partir d'un fichier JSON
     * les type sont : Chance, Caisse de communauté
     * chaque carte a une description
     */
    public void createCartes() {
        this.chance = new ArrayList<>();
        this.caisseCommunaute = new ArrayList<>();

        JSONParser parser = new JSONParser();

        // Chemins des fichiers JSON pour les cartes Chance et Caisse de Communauté
        String cheminChance = System.getProperty("user.dir") + "/src/main/java/iteration4/chance.json";
        String cheminCaisseCommunaute = System.getProperty("user.dir")
                + "/src/main/java/iteration4/caisseCommunaute.json";

        try {
            JSONArray jsonArrayChance = (JSONArray) parser.parse(new FileReader(cheminChance));
            JSONArray jsonArrayCaisseCommunaute = (JSONArray) parser.parse(new FileReader(cheminCaisseCommunaute));

            // Méthode de création des cartes pour les deux types
            createCartesFromJson(jsonArrayChance, this.chance);
            createCartesFromJson(jsonArrayCaisseCommunaute, this.caisseCommunaute);
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    private void createCartesFromJson(JSONArray jsonArray, ArrayList<Carte> listeCartes) {
        for (Object o : jsonArray) {
            JSONObject node = (JSONObject) o;
            String type = (String) node.get("type");
            String description = (String) node.get("description");
            JSONArray actionsJson = (JSONArray) node.get("action");

            ArrayList<Action> actions = new ArrayList<>();
            for (Object actionObj : actionsJson) {
                JSONObject actionNode = (JSONObject) actionObj;
                Action action = new Action(
                        (String) actionNode.get("type"),
                        (String) actionNode.getOrDefault("destination", null),
                        actionNode.get("amount") != null ? ((Long) actionNode.get("amount")).intValue() : null,
                        (String) actionNode.getOrDefault("deck", null));
                actions.add(action);
            }

            Carte nouvelleCarte = new Carte(type, description, actions);
            listeCartes.add(nouvelleCarte);
        }
    }

    public ArrayList<Carte> getChance() {
        return chance;
    }

    public ArrayList<Carte> getCaisseCommunaute() {
        return caisseCommunaute;
    }
   

    // Fonction qui permet de tirer une carte chance aléatoirement
    public Carte tirerCarteChance() {
        int index = (int) (Math.random() * chance.size());
        Carte carte = chance.get(index);
        return carte;
    }

    // Fonction qui permet de tirer une carte caisse de communauté aléatoirement
    public Carte tirerCarteCaisseCommunaute() {
        int index = (int) (Math.random() * caisseCommunaute.size());
        Carte carte = caisseCommunaute.get(index);
        return carte;
    }

    public String actionCarte(Carte carte, Joueur joueur) {
        String message = "";
        for (Action action : carte.getActions()) {
            switch (action.getType()) {
                case "gain":
                    joueur.gagnerArgent(action.getAmount());
                    message += carte.getDescription() + "\n";
                    break;
                case "perte":
                    joueur.payer(action.getAmount());
                    message += carte.getDescription() + "\n";
                    break;
                case "move":
                    if (action.getDestination() == "reculer") {
                        joueur.setPosition(joueur.getPosition() - Integer.parseInt(action.getDestination()));
                        message += carte.getDescription() + "\n";
                        break;
                        
                    }
                    else{
                        joueur.setPosition(Integer.parseInt(action.getDestination()));
                        message += carte.getDescription() + "\n";
                        break;

                    }
                    
                case "pioche":
                    if (action.getDeck().equals("chance")) {
                        Carte carteChance = tirerCarteChance();
                        message += carte.getDescription() + "\n";
                        message += actionCarte(carteChance, joueur);
                    } else {
                        Carte carteCaisseCommunaute = tirerCarteCaisseCommunaute();
                        message += carte.getDescription() + "\n";
                        message += actionCarte(carteCaisseCommunaute, joueur);
                    }
                    break;
                default:
                    break;
            }
        }
        return message;
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
                    int prix = (node.get("prix") == null ? 0 : ((Long) node.get("prix")).intValue());

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
        return des;
    }

    public void lancerDes() {
        for (int i = 0; i < des.length; i++) {
            des[i].lancerTest();
        }
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

    public void actionCarte(Carte carte) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionCarte'");
    }

    public boolean isGameOver() {
        return (compteurTours > nbTours || listeJoueur.size() <= 1);
    }

    public void incrementerCompteurTours() {
        compteurTours++;
    }

    public Case getCaseCourante(Joueur joueur) {
        return plateau.get(joueur.getPosition());
    }
}