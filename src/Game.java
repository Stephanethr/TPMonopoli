import java.util.ArrayList;

/**
 * Game
 */
public class Game {

    public static void main(String[] args) {

        System.out.println("Bienvenue dans le jeu Monopoly");
        System.out.println("Combiens de tours voulez-vous jouer ? (entrez un nombre)");
        int nbTours = 0;
        int compteurTours = 1;
        try {
            nbTours = Integer.parseInt(System.console().readLine());
        } catch (NumberFormatException e) {
            System.out.println("Vous devez entrer un nombre");
            return;
        }

        if (nbTours < 1) {
            System.out.println("Le nombre de tours doit être supérieur à 0");
            return;
        }

        // Création des joueurs
        System.out.println("Combien de joueurs ?");
        int nbJoueurs = 0;
        try{
            nbJoueurs = Integer.parseInt(System.console().readLine());
        } catch (NumberFormatException e) {
            System.out.println("Vous devez entrer un nombre");
            return;
        }
        if (nbJoueurs < 2) {
            System.out.println("Il faut au moins 2 joueurs pour jouer");
            return;
        }
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
                System.out.println("------------------ TOUR "+ compteurTours + " DE " + joueur.getPseudo() + " ---------------------");
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
            compteurTours++;
            if (compteurTours-1 == nbTours) {
                System.out.println("Fin de la partie");
                return;
            }

        }

    }
}