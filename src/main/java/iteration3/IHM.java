package iteration3;
import java.util.Scanner;

public class IHM {
    public static void main(String[] args) {
        int nbToursMax;
        int nbJoueurs;
        int de1;
        int de2;
        int totalDe;
        Case caseCourante;
        String choix;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans le jeu Monopoly");
        System.out.println("Combien de tours voulez-vous jouer ? (entrez un nombre)");
        nbToursMax = scanner.nextInt();

        System.out.println("Combien de joueurs ?");
        nbJoueurs = scanner.nextInt();

        CtrlJeu ctrlJeu = new CtrlJeu();
        ctrlJeu.createGame(nbToursMax, nbJoueurs);

        while (ctrlJeu.getCompteurTours() < ctrlJeu.getNbTours()+1 && ctrlJeu.getListeJoueur().size() > 1){
            for (Joueur joueur : ctrlJeu.getListeJoueur()) {
                System.out
                        .println("\n------------------ TOUR " + ctrlJeu.getCompteurTours() + " DE " + joueur.getPseudo()
                                + " ---------------------\n");
                caseCourante = ctrlJeu.getPlateau().get(joueur.getPosition());
                System.out.println(
                        joueur.getPseudo() + " est sur la case " + caseCourante.getPosition());
                System.out.println("Argent de " + joueur.getPseudo() + " : " + joueur.getArgent());
                System.out
                        .println("Propriétés de " + joueur.getPseudo() + " : \n" + joueur.afficherProprietes() + "\n");

                System.out.println("Appuyez sur entrée pour lancer le dé" + "\n");
                // scanner.nextLine();

                // lancement des dés
                de1 = ctrlJeu.getDes()[0].getValue();
                de2 = ctrlJeu.getDes()[1].getValue();

                // Total des dés
                totalDe = de1 + de2;

                System.out.println("Valeur des dés : " + de1 + " et " + de2);

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
                        // choix = scanner.nextLine();
                        choix = "y";

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
                                        joueur.getPseudo() + " a payé " + propriete.getLoyer() + " de loyer à "
                                                + propriete.getProprietaire().getPseudo());
                            } catch (Exception e) {
                                System.out.println(
                                        "Vous n'avez pas assez d'argent pour payer le loyer. Vous avez perdu la partie.");
                                ctrlJeu.getListeJoueur().remove(joueur);
                            }

                        } else {
                            System.out.println("Vous possédez déjà cette propriété.");
                        }
                    }
                }

                // Gérer le cas des doubles
                while (de1 == de2) {
                    System.out.println("Vous avez fait un double !");
                    System.out.println("Vous pouvez rejouer");
                    //scanner.nextLine();

                    // lancement des dés
                    de1 = ctrlJeu.getDes()[0].getValue();
                    de2 = ctrlJeu.getDes()[1].getValue();

                    // Total des dés
                    totalDe = de1 + de2;

                    System.out.println("Valeur des dés : " + de1 + " et " + de2);
                    System.out.println(joueur.getPseudo() + " a fait " + totalDe);
                    ctrlJeu.deplacement(joueur, totalDe);
                    caseCourante = ctrlJeu.getPlateau().get(joueur.getPosition());
                    System.out.println(joueur.getPseudo() + " est sur la case " + caseCourante);
                    System.out.println("Argent de " + joueur.getPseudo() + " : " + joueur.getArgent());
                    System.out.println(
                            "Propriétés de " + joueur.getPseudo() + " : \n" + joueur.afficherProprietes() + "\n");

                    if (caseCourante instanceof CasePropriete) {
                        CasePropriete propriete = (CasePropriete) caseCourante;
                        System.out.println("C'est une propriété !");

                        if (propriete.getProprietaire() == null) {
                            System.out.println("La propriété est disponible à l'achat.");
                            System.out.println("Voulez-vous acheter la propriété ? (y/n)");
                            // choix = scanner.nextLine();
                            choix = "y";

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
                                            joueur.getPseudo() + " a payé " + propriete.getLoyer() + " de loyer à "
                                                    + propriete.getProprietaire().getPseudo());
                                } catch (Exception e) {
                                    System.out.println(
                                            "Vous n'avez pas assez d'argent pour payer le loyer. Vous avez perdu la partie.");
                                    ctrlJeu.getListeJoueur().remove(joueur);
                                }

                            } else {
                                System.out.println("Vous possédez déjà cette propriété.");
                            }
                        }
                    }
                }

            }
            ctrlJeu.setCompteurTours(ctrlJeu.getCompteurTours() + 1);
        }
        scanner.close();

        System.out.println("La partie est terminée !");
        System.out.println("Le joueur gagnant est : " + ctrlJeu.getJoueurGagnant().getPseudo());
        System.out.println(ctrlJeu.getPlateau());
    }
}
