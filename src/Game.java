import java.util.ArrayList;

/**
 * Game
 */
public class Game {

    public static void main(String[] args) {
        System.out.println("Hello World");

        while (true){
            System.out.println("Bienvenue dans le jeu Monopoly");
            System.out.println("Quel est votre pseudo ?");
            String pseudo = System.console().readLine();
            // Création du joueur
            Joueur joueur = new Joueur(pseudo);
            System.out.println("Bienvenue " + joueur.getPseudo());
            System.out.println("Voulez-vous commencer une partie ? (y/n)");
            String reponse = System.console().readLine();
            if (reponse.equals("n")){
                break;
            }
            // Création du dé
            De de1 = new De();
            De de2 = new De();

            // Création du plateau
            ArrayList<Case> plateau = new ArrayList<Case>();
            plateau.add(new Case(0, "Départ", "départ"));
            while (plateau.size() < 40){
                plateau.add(new Case(plateau.size(), "Case " + plateau.size(), "normal"));
            }
            System.out.println(plateau);


        }

    
    }
}