import java.util.ArrayList;

/**
 * Game
 */
public class Game {

    public static void main(String[] args) {

        System.out.println("Bienvenue dans le jeu Monopoly");
        System.out.println("Voulez-vous commencer une partie ? (y/n)");
        String reponse = System.console().readLine();
        if (reponse.equals("n")) {
            System.out.println("Au revoir !");
            System.exit(0);
        }

        // Création des joueurs
        System.out.println("Combien de joueurs ?");
        int nbJoueurs = Integer.parseInt(System.console().readLine());
        ArrayList<Joueur> listeJoueur = new ArrayList<Joueur>();
        for (int i = 0; i < nbJoueurs; i++) {
            System.out.println("Quel est le pseudo du joueur " + (i + 1) + " ?");
            String pseudo = System.console().readLine();
            Joueur joueur = new Joueur(pseudo);
            listeJoueur.add(joueur);
            System.out.println("Bienvenue " + joueur.getPseudo());
        }

        // Création des dés
        De de1 = new De();
        De de2 = new De();

        // Création du plateau
        ArrayList<Case> plateau = new ArrayList<Case>();
        plateau.add(new Case(0));
        while (plateau.size() < 40) {
            plateau.add(new Case(plateau.size()));
        }

        while (true) {
            for (Joueur joueur : listeJoueur) {
                System.out.println("------------------ TOUR DE " + joueur.getPseudo() + " ---------------------");
                System.out.println("Appuyez sur entrée pour lancer le dé");
                System.console().readLine();
                de1.lancer();
                de2.lancer();
                System.out.println("valeur des dé : " + de1.getValue() + " et " + de2.getValue());

                int deValue = de1.getValue() + de2.getValue();
                System.out.println(joueur.getPseudo() + " a fait " + deValue);
                joueur.deplacement(deValue);
                Case caseCourante = plateau.get(joueur.getPosition());
                System.out.println(joueur.getPseudo() + " est sur la case " + caseCourante + "\n");

                // Si le joueur fait un double, il rejoue
                // To Do: gérer le cas où le joueur va en prison s'il fait 3 doubles de suite
                while (de1.getValue() == de2.getValue()) {
                    System.out.println("Vous avez fait un double !");
                    System.out.println("Vous pouvez rejouer");
                    System.console().readLine();
                    de1.lancer();
                    de2.lancer();
                    System.out.println("valeur des dé : " + de1.getValue() + " et " + de2.getValue());
                    deValue = de1.getValue() + de2.getValue();
                    System.out.println(joueur.getPseudo() + " a fait " + deValue);
                    joueur.deplacement(deValue);
                    caseCourante = plateau.get(joueur.getPosition());
                    System.out.println(joueur.getPseudo() + " est sur la case " + caseCourante + "\n");
                }
            }

        }

    }
}