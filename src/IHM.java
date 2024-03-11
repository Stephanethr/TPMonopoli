import java.util.Scanner;

public class IHM {
    public static void main(String[] args) {
        int nbTours;
        int nbJoueurs;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans le jeu Monopoly");
        System.out.println("Combien de tours voulez-vous jouer ? (entrez un nombre)");
        nbTours = scanner.nextInt();

        System.out.println("Combien de joueurs ?");
        nbJoueurs = scanner.nextInt();

        CtrlJeu ctrlJeu = new CtrlJeu(nbTours, nbJoueurs);
        ctrlJeu.createGame();

        while (true) {
            for (Joueur joueur : ctrlJeu.getListeJoueur()) {
                System.out.println("------------------ TOUR " + ctrlJeu.getCompteurTours() + " DE " + joueur.getPseudo()
                        + " ---------------------");
                System.out.println("Appuyez sur entrée pour lancer le dé");
                scanner.nextLine(); // Pour consommer la nouvelle ligne laissée par nextInt()

                System.out.println(
                        "Valeur des dés : " + ctrlJeu.getDes()[0].getValue() + " et " + ctrlJeu.getDes()[1].getValue());

                int deValue = ctrlJeu.getDes()[0].getValue() + ctrlJeu.getDes()[1].getValue();
                System.out.println(joueur.getPseudo() + " a fait " + deValue);
                joueur.deplacement(deValue);
                Case caseCourante = ctrlJeu.getPlateau().get(joueur.getPosition());
                System.out.println(joueur.getPseudo() + " est sur la case " + caseCourante);

                if (caseCourante instanceof Propriete) {
                    Propriete propriete = (Propriete) caseCourante;
                    System.out.println("C'est une propriété !");

                    if (propriete.getProprietaire() == null) {
                        System.out.println("La propriété est disponible à l'achat.");
                        System.out.println("Voulez-vous acheter la propriété ? (y/n)");
                        String choix = scanner.nextLine();

                        if (choix.equalsIgnoreCase("y")) {
                            propriete.acheter(joueur);
                            System.out.println(joueur.getPseudo() + " a acheté la propriété !");
                        }
                    } else {
                        if (propriete.getProprietaire() != joueur) {
                            System.out.println("La propriété appartient à " + propriete.getProprietaire().getPseudo());
                            System.out.println("Vous devez payer le loyer !");
                            propriete.payerLoyer(joueur);
                            System.out.println(
                                    joueur.getPseudo() + " a payé le loyer à "
                                            + propriete.getProprietaire().getPseudo());
                        } else {
                            System.out.println("Vous possédez déjà cette propriété.");
                        }
                    }
                }

                // Gérer le cas des doubles
                while (ctrlJeu.getDes()[0].getValue() == ctrlJeu.getDes()[1].getValue()) {
                    System.out.println("Vous avez fait un double !");
                    System.out.println("Vous pouvez rejouer");
                    scanner.nextLine();
                    System.out.println("Valeur des dés : " + ctrlJeu.getDes()[0].getValue() + " et "
                            + ctrlJeu.getDes()[1].getValue());
                    deValue = ctrlJeu.getDes()[0].getValue() + ctrlJeu.getDes()[1].getValue();
                    System.out.println(joueur.getPseudo() + " a fait " + deValue);
                    joueur.deplacement(deValue);
                    caseCourante = ctrlJeu.getPlateau().get(joueur.getPosition());
                    System.out.println(joueur.getPseudo() + " est sur la case " + caseCourante);

                    if (caseCourante instanceof Propriete) {
                        Propriete propriete = (Propriete) caseCourante;
                        System.out.println("C'est une propriété !");

                        if (propriete.getProprietaire() == null) {
                            System.out.println("La propriété est disponible à l'achat.");
                            System.out.println("Voulez-vous acheter la propriété ? (y/n)");
                            String choix = scanner.nextLine();

                            if (choix.equalsIgnoreCase("y")) {
                                propriete.acheter(joueur);
                                System.out.println(joueur.getPseudo() + " a acheté la propriété !");
                            }
                        } else {
                            if (propriete.getProprietaire() != joueur) {
                                System.out.println(
                                        "La propriété appartient à " + propriete.getProprietaire().getPseudo());
                                System.out.println("Vous devez payer le loyer !");
                                propriete.payerLoyer(joueur);
                                System.out.println(
                                        joueur.getPseudo() + " a payé le loyer à "
                                                + propriete.getProprietaire().getPseudo());
                            } else {
                                System.out.println("Vous possédez déjà cette propriété.");
                            }
                        }
                    }
                }
                System.out.println();
            }
            ctrlJeu.setCompteurTours(ctrlJeu.getCompteurTours() + 1);
            if (ctrlJeu.getCompteurTours() - 1 == ctrlJeu.getNbTours()) {
                System.out.println("Fin de la partie");
                scanner.close();
                return;
            }
        }
    }
}
