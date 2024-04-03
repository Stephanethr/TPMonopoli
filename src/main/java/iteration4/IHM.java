package iteration4;

import java.util.Scanner;

public class IHM {
    public static void main(String[] args) {
        int nbToursMax;
        int nbJoueurs;
        int de1;
        int de2;
        int compteurDouble = 0;
        int totalDe;
        String pseudo;
        Case caseCourante;
        String choix;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans le jeu Monopoly");
        System.out.println("Combien de tours voulez-vous jouer ? (entrez un nombre)");
        nbToursMax = scanner.nextInt();

        System.out.println("Combien de joueurs ?");
        nbJoueurs = scanner.nextInt();

        CtrlJeu ctrlJeu = new CtrlJeu(nbToursMax, nbJoueurs);
        System.out.println("LE PLATEAU : " + ctrlJeu.getPlateau());
        System.out.println("les cartes chance : " + ctrlJeu.getChance());
        System.out.println("les cartes caisse de communauté : " + ctrlJeu.getCaisseCommunaute());

        while (ctrlJeu.getCompteurTours() <= ctrlJeu.getNbTours() && ctrlJeu.getListeJoueur().size() > 1){
            for (Joueur joueur : ctrlJeu.getListeJoueur()) {
                pseudo = ctrlJeu.getPseudo(joueur);
                System.out
                        .println("\n------------------ TOUR " + ctrlJeu.getCompteurTours() + " DE " + pseudo
                                + " ---------------------\n");
                caseCourante = ctrlJeu.getPlateau().get(ctrlJeu.getPosition(joueur));
                System.out.println(
                        pseudo + " est sur la case " + caseCourante.getNom());
                System.out.println("Argent de " + pseudo + " : " + ctrlJeu.getArgent(joueur));
                System.out
                        .println("Propriétés de " + pseudo + " : \n" + ctrlJeu.afficherProprietes(joueur) + "\n");

                if (ctrlJeu.getEnPrison(joueur)){
                    System.out.println("Vous êtes en prison !");
                    System.out.println("Vous avez 3 tours pour faire un double et sortir de prison.");
                    System.out.println("Appuyez sur entrée pour lancer le dé" + "\n");
                    // scanner.nextLine();

                    // lancement des dés
                    de1 = ctrlJeu.getDes()[0].getValue();
                    de2 = ctrlJeu.getDes()[1].getValue();

                    // Total des dés
                    totalDe = de1 + de2;

                    System.out.println("Valeur des dés : " + de1 + " et " + de2);

                    if (de1 == de2){
                        System.out.println("Vous avez fait un double ! Vous sortez de prison et avancez de " + totalDe + " cases.");
                        ctrlJeu.sortirDePrison(joueur);
                        ctrlJeu.deplacement(joueur, totalDe);
                    } else {
                        System.out.println("Vous n'avez pas fait de double.");
                        ctrlJeu.incrementerNbToursPrison(joueur);
                        if (ctrlJeu.getNbToursPrison(joueur) == 3){
                            System.out.println("Vous avez fait 3 tours en prison. Vous devez payer 50€ pour sortir.");
                            ctrlJeu.payer(joueur,50);
                            ctrlJeu.sortirDePrison(joueur);
                        }
                    }
                }else{
                System.out.println("Appuyez sur entrée pour lancer le dé" + "\n");
                // scanner.nextLine();

                // lancement des dés
                de1 = ctrlJeu.getDes()[0].getValue();
                de2 = ctrlJeu.getDes()[1].getValue();

                // Total des dés
                totalDe = de1 + de2;

                System.out.println("Valeur des dés : " + de1 + " et " + de2);

                System.out.println(pseudo + " a fait " + totalDe);
                ctrlJeu.deplacement(joueur, totalDe);
                caseCourante = ctrlJeu.getPlateau().get(ctrlJeu.getPosition(joueur));
                System.out.println(pseudo + " est sur la case " + caseCourante);

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
                                System.out.println(pseudo + " a acheté la propriété !");
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
                                        pseudo + " a payé " + propriete.getLoyer() + " de loyer à "
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
                while (de1 == de2 && compteurDouble < 3) {
                    compteurDouble++;
                    if (compteurDouble == 3) {
                        System.out.println("Vous avez fait 3 doubles de suite. Vous allez en prison.");
                        ctrlJeu.allerEnPrison(joueur);

                    } else {

                        System.out.println("Vous avez fait un double !");
                        System.out.println("Vous pouvez rejouer");
                        //scanner.nextLine();

                        // lancement des dés
                        de1 = ctrlJeu.getDes()[0].getValue();
                        de2 = ctrlJeu.getDes()[1].getValue();

                        // Total des dés
                        totalDe = de1 + de2;

                        System.out.println("Valeur des dés : " + de1 + " et " + de2);
                        System.out.println(pseudo + " a fait " + totalDe);
                        ctrlJeu.deplacement(joueur, totalDe);
                        caseCourante = ctrlJeu.getPlateau().get(ctrlJeu.getPosition(joueur));
                        System.out.println(pseudo + " est sur la case " + caseCourante);
                        System.out.println("Argent de " + pseudo + " : " + ctrlJeu.getArgent(joueur));
                        System.out.println(
                                "Propriétés de " + pseudo + " : \n" + ctrlJeu.afficherProprietes(joueur) + "\n");

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
                                        System.out.println(pseudo + " a acheté la propriété !");
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
                                                ctrlJeu.getPseudo(joueur) + " a payé " + propriete.getLoyer() + " de loyer à "
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
