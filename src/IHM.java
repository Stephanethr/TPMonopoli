public class IHM {
    public static void main(String[] args) {
        int nbTours;
        int nbJoueurs;
        System.out.println("Bienvenue dans le jeu Monopoly");
        System.out.println("Combiens de tours voulez-vous jouer ? (entrez un nombre)");
        nbTours = Integer.parseInt(System.console().readLine());
        System.out.println("Combien de joueurs ?");
        nbJoueurs = Integer.parseInt(System.console().readLine());
        CtrlJeu ctrlJeu = new CtrlJeu(nbTours, nbJoueurs);
        ctrlJeu.createGame();

        while(true)
        {
            for (Joueur joueur : ctrlJeu.getListeJoueur()) {
                System.out.println("------------------ TOUR " + ctrlJeu.getCompteurTours() + " DE " + joueur.getPseudo()
                        + " ---------------------");
                System.out.println("Appuyez sur entrée pour lancer le dé");
                System.console().readLine();
                System.out.println("valeur des dé : " + ctrlJeu.getDes()[0].getValue() + " et " + ctrlJeu.getDes()[1].getValue());

                int deValue = ctrlJeu.getDes()[0].getValue() + ctrlJeu.getDes()[1].getValue();
                System.out.println(joueur.getPseudo() + " a fait " + deValue);
                joueur.deplacement(deValue);
                Case caseCourante = ctrlJeu.getPlateau().get(joueur.getPosition());
                System.out.println(joueur.getPseudo() + " est sur la case " + caseCourante + "\n");

                // Si le joueur fait un double, il rejoue
                // To Do: gérer le cas où le joueur va en prison s'il fait 3 doubles de suite
                while (ctrlJeu.getDes()[0].getValue() == ctrlJeu.getDes()[1].getValue()) {
                    System.out.println("Vous avez fait un double !");
                    System.out.println("Vous pouvez rejouer");
                    System.console().readLine();
                    System.out.println("valeur des dé : " + ctrlJeu.getDes()[0].getValue() + " et " + ctrlJeu.getDes()[1].getValue());
                    deValue = ctrlJeu.getDes()[0].getValue() + ctrlJeu.getDes()[1].getValue();
                    System.out.println(joueur.getPseudo() + " a fait " + deValue);
                    joueur.deplacement(deValue);
                    caseCourante = ctrlJeu.getPlateau().get(joueur.getPosition());
                    System.out.println(joueur.getPseudo() + " est sur la case " + caseCourante + "\n");
                }
            }
            ctrlJeu.setCompteurTours(ctrlJeu.getCompteurTours() + 1);
            if (ctrlJeu.getCompteurTours() - 1 == ctrlJeu.getNbTours()) {
                System.out.println("Fin de la partie");
                return;
            }

        }
    }
}

