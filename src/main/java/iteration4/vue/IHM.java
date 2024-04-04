package iteration4.vue;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import iteration4.controleur.CtrlJeu;
import iteration4.model.Carte;
import iteration4.model.Case;
import iteration4.model.CasePropriete;
import iteration4.model.CaseSpeciale;
import iteration4.model.Joueur;

public class IHM {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        IHM ihm = new IHM();
        ihm.startGame();
    }

    private void startGame() {
    System.out.println("Bienvenue dans le jeu Monopoly");
    int nbToursMax = askForNumber("Combien de tours voulez-vous jouer ?");
    int nbJoueurs = askForNumber("Combien de joueurs ?");

    CtrlJeu ctrlJeu = new CtrlJeu(nbToursMax, nbJoueurs);
    System.out.println("LE PLATEAU : " + ctrlJeu.getPlateau());
    System.out.println("les cartes chance : " + ctrlJeu.getChance());
    System.out.println("les cartes caisse de communauté : " + ctrlJeu.getCaisseCommunaute());

    List<Joueur> listeJoueursCopy = new ArrayList<>(ctrlJeu.getListeJoueur()); // Copie de la liste des joueurs

    while (!ctrlJeu.isGameOver()) {
        for (Joueur joueur : listeJoueursCopy) {
            playTurn(ctrlJeu, joueur);

            System.out.println("\n Argent de " + ctrlJeu.getPseudo(joueur) + " : " + ctrlJeu.getArgent(joueur));
        }
        ctrlJeu.incrementerCompteurTours();
    }

    System.out.println("La partie est terminée !");
    System.out.println("Le joueur gagnant est : " + ctrlJeu.getJoueurGagnant().getPseudo());
    System.out.println(ctrlJeu.getPlateau());

    scanner.close();
}


    private void playTurn(CtrlJeu ctrlJeu, Joueur joueur) {
        String pseudo = ctrlJeu.getPseudo(joueur);
        System.out.println("\n------------------ TOUR " + ctrlJeu.getCompteurTours() + " DE " + pseudo + " ---------------------\n");

        if (ctrlJeu.getEnPrison(joueur)) {
            playInJail(ctrlJeu, joueur);
        } else {
            playNormalTurn(ctrlJeu, joueur);
        }
    }

    private void playNormalTurn(CtrlJeu ctrlJeu, Joueur joueur) {
        String pseudo = ctrlJeu.getPseudo(joueur);
        System.out.println(pseudo + " est sur la case " + ctrlJeu.getCaseCourante(joueur).getNom());
        System.out.println("Argent de " + pseudo + " : " + ctrlJeu.getArgent(joueur));

        boolean doubleDice = false;
        int countDoubles = 0;

        do {
            System.out.println("Appuyez sur entrée pour lancer les dés" + "\n");
            scanner.nextLine();
            ctrlJeu.lancerDes();
            int totalDe = ctrlJeu.totalDes();
            System.out.println("Valeur des dés : " + ctrlJeu.getDes()[0].getValue() + " et " + ctrlJeu.getDes()[1].getValue());
            System.out.println(pseudo + " a fait " + totalDe);

            ctrlJeu.deplacement(joueur, totalDe);
            Case caseCourante = ctrlJeu.getCaseCourante(joueur);
            System.out.println(pseudo + " est sur la case " + caseCourante);

            // Gérer les actions spécifiques à la case
            if (caseCourante instanceof CasePropriete) {
                handlePropertyAction(ctrlJeu, joueur, (CasePropriete) caseCourante, totalDe);
            }
            else{
                handleSpecialAction(ctrlJeu, joueur, (CaseSpeciale)caseCourante);
            }

            doubleDice = ctrlJeu.isDouble(ctrlJeu.getDes()[0].getValue(), ctrlJeu.getDes()[1].getValue());
            if (doubleDice) {
                countDoubles++;
                if (countDoubles == 3) {
                    System.out.println("Vous avez fait 3 doubles de suite. Vous allez en prison.");
                    ctrlJeu.allerEnPrison(joueur); // Mettre le joueur en prison
                    return; // Quitter la méthode playNormalTurn
                } else {
                    System.out.println("Vous avez fait un double ! Vous pouvez rejouer.");
                }
            }
        } while (doubleDice);
    }

    private void playInJail(CtrlJeu ctrlJeu, Joueur joueur) {
        String pseudo = ctrlJeu.getPseudo(joueur);
        System.out.println("Vous êtes en prison !");

        for (int i = 0; i < 3; i++) {
            System.out.println("Vous avez " + (3 - i) + " tours restants pour faire un double et sortir de prison.");
            System.out.println("Appuyez sur entrée pour lancer les dés" + "\n");
            scanner.nextLine();
            ctrlJeu.lancerDes();
            int totalDe = ctrlJeu.totalDes();
            System.out.println("Valeur des dés : " + ctrlJeu.getDes()[0].getValue() + " et " + ctrlJeu.getDes()[1].getValue());

            if (ctrlJeu.isDouble(ctrlJeu.getDes()[0].getValue(), ctrlJeu.getDes()[1].getValue())) {
                System.out.println("Vous avez fait un double ! Vous sortez de prison et avancez de " + totalDe + " cases.");
                ctrlJeu.sortirDePrison(joueur);
                ctrlJeu.deplacement(joueur, totalDe);
                Case caseCourante = ctrlJeu.getCaseCourante(joueur);
                System.out.println(pseudo + " est sur la case " + caseCourante);
                if (caseCourante instanceof CasePropriete) {
                    handlePropertyAction(ctrlJeu, joueur, (CasePropriete) caseCourante, totalDe);
                } else {
                    handleSpecialAction(ctrlJeu, joueur, (CaseSpeciale) caseCourante);
                }
                return;
            }
        }

        System.out.println("Vous n'avez pas fait de double après trois tours en prison.");
        System.out.println("Vous devez payer 50€ pour sortir.");
        ctrlJeu.payer(joueur, 50);
        ctrlJeu.sortirDePrison(joueur);
        // Case caseCourante = ctrlJeu.getCaseCourante(joueur);
    }

    private void handlePropertyAction(CtrlJeu ctrlJeu, Joueur joueur, CasePropriete propriete, int totalDe) {
        String pseudo = ctrlJeu.getPseudo(joueur);
        if (propriete.getProprietaire() == null) {
            System.out.println("La propriété est disponible à l'achat.");
            System.out.println("Voulez-vous acheter la propriété ? (y/n)");
            String choix = scanner.nextLine();
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
                    System.out.println(pseudo + " a payé " + propriete.getLoyer() + " de loyer à "
                            + propriete.getProprietaire().getPseudo());
                } catch (Exception e) {
                    System.out.println("Vous n'avez pas assez d'argent pour payer le loyer. Vous avez perdu la partie.");
                    ctrlJeu.getListeJoueur().remove(joueur);
                }
            } else {
                System.out.println("Vous possédez déjà cette propriété.");
            }
        }
    }
    public void handleSpecialAction(CtrlJeu ctrlJeu, Joueur joueur, CaseSpeciale caseCourante){

        switch (ctrlJeu.getNomCase(caseCourante)){
            case "Chance":
                Carte carteChance = ctrlJeu.piocherCarteChance();
                System.out.println(ctrlJeu.actionCarte(carteChance, joueur));
                break;
            case "Caisse de Communauté":
                Carte carteCaisseCommunaute = ctrlJeu.piocherCarteCaisseCommunaute();
                System.out.println(ctrlJeu.actionCarte(carteCaisseCommunaute, joueur));
                break;
            case "Parking Gratuit":
                System.out.println("Vous êtes sur le parc gratuit. Vous ne payez rien.");
                break;
            case "Taxe de Luxe":
                System.out.println("Vous êtes sur la case Taxe de luxe. Vous devez payer 100€.");
                ctrlJeu.payer(joueur, 100);
                break;
            case "Impots":
                System.out.println("Vous êtes sur la case Impots. Vous devez payer 200€.");
                ctrlJeu.payer(joueur, 200);
                break;
            case "Allez en Prison":
                System.out.println("Vous êtes sur la case Allez en prison. Vous allez en prison.");
                ctrlJeu.allerEnPrison(joueur);
                break;
            case "Prison" :
                System.out.println("Vous êtes sur la case Prison. vous vous moquez des autres joueurs.");
                break;
            
            default:
                System.out.println("Case Départ. Vous recevez 200€.");
        }

    }

    private int askForNumber(String message) {
        System.out.println(message);
        return scanner.nextInt();
    }
}
