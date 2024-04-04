package iteration4.model;

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
        String cheminChance = System.getProperty("user.dir") + "/src/main/java/iteration4/json/chance.json";
        String cheminCaisseCommunaute = System.getProperty("user.dir")
                + "/src/main/java/iteration4/json/caisseCommunaute.json";

        try {
            JSONArray jsonArrayChance = (JSONArray) parser.parse(new FileReader(cheminChance));
            JSONArray jsonArrayCaisseCommunaute = (JSONArray) parser.parse(new FileReader(cheminCaisseCommunaute));

            // Méthode de création des cartes pour les deux types
            getCartesFromJson(jsonArrayChance, this.chance);
            getCartesFromJson(jsonArrayCaisseCommunaute, this.caisseCommunaute);
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    private void getCartesFromJson(JSONArray jsonArray, ArrayList<Carte> listeCartes) {
        for (Object o : jsonArray) {
            JSONObject node = (JSONObject) o;
            String type = (String) node.get("type");
            String description = (String) node.get("description");
            JSONArray actionsJson = (JSONArray) node.get("action");

            ArrayList<Action> actions = new ArrayList<>();
            for (Object actionObj : actionsJson) {
                JSONObject actionNode = (JSONObject) actionObj;

                // Utilisation de get() et de gestion manuelle des valeurs null
                String actionType = (String) actionNode.get("type");
                String destination = actionNode.get("destination") != null ? (String) actionNode.get("destination")
                        : null;
                int amount = actionNode.get("amount") != null ? ((Long) actionNode.get("amount")).intValue() : 0;
                
                String deckName = actionNode.get("deckName") != null ? (String) actionNode.get("deckName") : null;
                String from = actionNode.get("from") != null ? (String) actionNode.get("from") : null;
                String to = actionNode.get("to") != null ? (String) actionNode.get("to") : null;

                Action action = new Action(actionType, destination, amount, deckName, from, to);
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

    public String actionCarte(Carte carte, Joueur joueur, ArrayList<Joueur> autresJoueurs) {
    StringBuilder message = new StringBuilder();
    message.append(carte.getDescription()).append("\n");

    for (Action action : carte.getActions()) {
        switch (action.getType()) {
            case "move":
                // Déplacement vers une case spécifique
                if ("Départ".equals(action.getDestination())) {
                    joueur.setPosition(0); // Assurez-vous que 0 est l'index de la case Départ
                    message.append("Avancez jusqu'à la case Départ. ");
                } else if ("Prison".equals(action.getDestination())) {
                    joueur.allerEnPrison();
                    message.append("Allez en prison. ");
                } else {
                    // Gérer les déplacements spéciaux si nécessaire
                }
                break;
            case "credit":
                if (action.getFrom() != null && "all".equals(action.getFrom())) {
                    // Le joueur reçoit de l'argent de tous les autres joueurs
                    for (Joueur autreJoueur : autresJoueurs) {
                        autreJoueur.payer(action.getAmount());
                        joueur.gagnerArgent(action.getAmount());
                    }
                    message.append("Recevez ").append(action.getAmount()).append(" € de chaque joueur. ");
                } else {
                    joueur.gagnerArgent(action.getAmount());
                    message.append("Recevez ").append(action.getAmount()).append(" €. ");
                }
                break;
            case "debit":
                if (action.getTo() != null && "all".equals(action.getTo())) {
                    // Le joueur paye de l'argent à tous les autres joueurs
                    for (Joueur autreJoueur : autresJoueurs) {
                        joueur.payer(action.getAmount());
                        autreJoueur.gagnerArgent(action.getAmount());
                    }
                    message.append("Payez ").append(action.getAmount()).append(" € à chaque joueur. ");
                } else {
                    joueur.payer(action.getAmount());
                    message.append("Payez ").append(action.getAmount()).append(" €. ");
                }
                break;
            case "pioche":
                if ("Chance".equals(action.getdeckName())) {
                    // Piocher une carte Chance et exécuter son action
                    Carte nouvelleCarteChance = tirerCarteChance();
                    message.append("Piochez une carte Chance. ").append(actionCarte(nouvelleCarteChance, joueur, autresJoueurs));
                } else if ("Caisse de Communauté".equals(action.getdeckName())) {
                    // Piocher une carte Caisse de Communauté et exécuter son action
                    Carte nouvelleCarteCaisse = tirerCarteCaisseCommunaute();
                    message.append("Piochez une carte Caisse de Communauté. ").append(actionCarte(nouvelleCarteCaisse, joueur, autresJoueurs));
                }
                break;
            default:
                message.append("Action inconnue. ");
                break;
        }
    }
    return message.toString();
}


    public void createPlateau() {
        this.plateau = new ArrayList<Case>();

        try {
            // Créer un objet JSONParser pour lire le fichier JSON
            JSONParser parser = new JSONParser();
            String chemin = System.getProperty("user.dir") + "/src/main/java/iteration4/json/plateau.json";

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
            des[i].lancer();
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