import java.util.Scanner;

public class IHM {
    public static void main(String[] args) {
        int nbToursMax;
        int nbJoueurs;
        int de1;
        int de2;
        int totalDe;
        String choix;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans le jeu Monopoly");
        System.out.println("Combien de tours voulez-vous jouer ? (entrez un nombre)");
        nbToursMax = scanner.nextInt();

        System.out.println("Combien de joueurs ?");
        nbJoueurs = scanner.nextInt();

        CtrlJeu ctrlJeu = new CtrlJeu();
        ctrlJeu.createGame(nbToursMax, nbJoueurs);
        System.out.println(ctrlJeu.getPlateau());

        while (ctrlJeu.getCompteurTours() - 1 < ctrlJeu.getNbTours()) {
            for (Joueur joueur : ctrlJeu.getListeJoueur()) {
                System.out
                        .println("\n------------------ TOUR " + ctrlJeu.getCompteurTours() + " DE " + joueur.getPseudo()
                                + " ---------------------\n");
                System.out.println(
                        joueur.getPseudo() + " est sur la case " + ctrlJeu.getPlateau().get(joueur.getPosition()));
                System.out.println("Argent de " + joueur.getPseudo() + " : " + joueur.getArgent());
                System.out.println("Propriétés de " + joueur.getPseudo() + " : " + joueur.getProprietes() + "\n");

                System.out.println("Appuyez sur entrée pour lancer le dé" + "\n");
                scanner.nextLine();

                // lancement des dés
                de1 = ctrlJeu.getDes()[0].getValue();
                de2 = ctrlJeu.getDes()[1].getValue();

                // Total des dés
                totalDe = de1 + de2;

                System.out.println("Valeur des dés : " + de1 + " et " + de2);

                System.out.println(joueur.getPseudo() + " a fait " + totalDe);
                ctrlJeu.deplacement(joueur, totalDe);
                Case caseCourante = ctrlJeu.getPlateau().get(joueur.getPosition());
                System.out.println(joueur.getPseudo() + " est sur la case " + caseCourante);

                if (caseCourante instanceof CasePropriete) {
                    CasePropriete propriete = (CasePropriete) caseCourante;
                    System.out.println("C'est une propriété !");

                    if (propriete.getProprietaire() == null) {
                        System.out.println("La propriété est disponible à l'achat.");
                        System.out.println("Voulez-vous acheter la propriété ? (y/n)");
                        choix = scanner.nextLine();

                        if (choix.equalsIgnoreCase("y")) {
                            try {
                                ctrlJeu.acheterPropriete(joueur, propriete);
                                System.out.println(joueur.getPseudo() + " a acheté la propriété !");
                            } catch (Exception e) {
                                System.out.println("Vous n'avez pas assez d'argent pour acheter la propriété.");
                            }
                        }
                    } else {
                        if (propriete.getProprietaire() != joueur) {
                            System.out.println("La propriété appartient à " + propriete.getProprietaire().getPseudo());
                            System.out.println("Vous devez payer le loyer !");
                            try {
                                ctrlJeu.payerLoyer(joueur, propriete, totalDe);
                                System.out.println(
                                        joueur.getPseudo() + " a payé le loyer à "
                                                + propriete.getProprietaire().getPseudo());
                            } catch (Exception e) {
                                System.out.println(
                                        "Vous n'avez pas assez d'argent pour payer le loyer. Vous avez perdu la partie.");
                            }

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
                    System.out.println("Valeur des dés : " + de1 + " et " + de2);

                    totalDe = de1 + de2;
                    System.out.println(joueur.getPseudo() + " a fait " + totalDe);
                    ctrlJeu.deplacement(joueur, totalDe);
                    caseCourante = ctrlJeu.getPlateau().get(joueur.getPosition());
                    System.out.println(joueur.getPseudo() + " est sur la case " + caseCourante);

                    if (caseCourante instanceof CasePropriete) {
                        CasePropriete propriete = (CasePropriete) caseCourante;
                        System.out.println("C'est une propriété !");

                        if (propriete.getProprietaire() == null) {
                            System.out.println("La propriété est disponible à l'achat.");
                            System.out.println("Voulez-vous acheter la propriété ? (y/n)");
                            choix = scanner.nextLine();

                            if (choix.equalsIgnoreCase("y")) {
                                try {
                                    ctrlJeu.acheterPropriete(joueur, propriete);
                                    System.out.println(joueur.getPseudo() + " a acheté la propriété !");
                                } catch (Exception e) {
                                    System.out.println("Vous n'avez pas assez d'argent pour acheter la propriété.");
                                }
                            }
                        } else {
                            if (propriete.getProprietaire() != joueur) {
                                System.out.println(
                                        "La propriété appartient à " + propriete.getProprietaire().getPseudo());
                                System.out.println("Vous devez payer le loyer !");
                                try {
                                    ctrlJeu.payerLoyer(joueur, propriete, totalDe);
                                    System.out.println(
                                            joueur.getPseudo() + " a payé le loyer à "
                                                    + propriete.getProprietaire().getPseudo());
                                } catch (Exception e) {
                                    System.out.println(
                                            "Vous n'avez pas assez d'argent pour payer le loyer. Vous avez perdu la partie.");
                                }

                            } else {
                                System.out.println("Vous possédez déjà cette propriété.");
                            }
                        }
                    }
                }
                // TODO
                // Gérer le cas de la case départ
                if (joueur.getPosition() == 0) {
                    joueur.gagnerArgent(1000);
                    System.out.println(joueur.getPseudo() + " a passé la case départ et gagne 200€");
                }

            }
            ctrlJeu.setCompteurTours(ctrlJeu.getCompteurTours() + 1);
        }
        scanner.close();
    }
}
