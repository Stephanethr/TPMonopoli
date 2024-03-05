public class CtrlJeu {
    private Game game;

    public void jouer() {
        int nbTours = 0;
        int nbJoueurs = 0;

        System.out.println("Bienvenue dans le jeu Monopoly");
        System.out.println("Combiens de tours voulez-vous jouer ? (entrez un nombre)");
        nbTours = Integer.parseInt(System.console().readLine());
        System.out.println("Combien de joueurs ?");
        nbJoueurs = Integer.parseInt(System.console().readLine());
        game = new Game(nbTours, nbJoueurs);
        while(true)
        {
            for (Joueur joueur : game.getListeJoueur()) {
                System.out.println("------------------ TOUR " + game.getCompteurTours() + " DE " + joueur.getPseudo()
                        + " ---------------------");
                System.out.println("Appuyez sur entrée pour lancer le dé");
                System.console().readLine();
                System.out.println("valeur des dé : " + game.getDes()[0].getValue() + " et " + game.getDes()[1].getValue());

                int deValue = game.getDes()[0].getValue() + game.getDes()[1].getValue();
                System.out.println(joueur.getPseudo() + " a fait " + deValue);
                joueur.deplacement(deValue);
                Case caseCourante = game.getPlateau().get(joueur.getPosition());
                System.out.println(joueur.getPseudo() + " est sur la case " + caseCourante + "\n");

                // Si le joueur fait un double, il rejoue
                // To Do: gérer le cas où le joueur va en prison s'il fait 3 doubles de suite
                while (game.getDes()[0].getValue() == game.getDes()[1].getValue()) {
                    System.out.println("Vous avez fait un double !");
                    System.out.println("Vous pouvez rejouer");
                    System.console().readLine();
                    System.out.println("valeur des dé : " + game.getDes()[0].getValue() + " et " + game.getDes()[1].getValue());
                    deValue = game.getDes()[0].getValue() + game.getDes()[1].getValue();
                    System.out.println(joueur.getPseudo() + " a fait " + deValue);
                    joueur.deplacement(deValue);
                    caseCourante = game.getPlateau().get(joueur.getPosition());
                    System.out.println(joueur.getPseudo() + " est sur la case " + caseCourante + "\n");
                }
            }
            game.setCompteurTours(game.getCompteurTours() + 1);
            if (game.getCompteurTours() - 1 == game.getNbTours()) {
                System.out.println("Fin de la partie");
                return;
            }

        }
    }
}
