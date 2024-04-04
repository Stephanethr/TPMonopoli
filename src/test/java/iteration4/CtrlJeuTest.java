package iteration4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import iteration4.controleur.CtrlJeu;
import iteration4.model.CasePropriete;
import iteration4.model.Joueur;

import static org.junit.jupiter.api.Assertions.*;

class CtrlJeuTest {
    private CtrlJeu ctrlJeu;

    @BeforeEach
    void setUp() {
        ctrlJeu = new CtrlJeu(10, 2);
    }

    @Test
    void deplacement() {
        Joueur joueurMock = Mockito.mock(Joueur.class);
        int totalDe = 5;

        ctrlJeu.deplacement(joueurMock, totalDe);

        Mockito.verify(joueurMock, Mockito.times(1)).deplacement(totalDe);
    }

    @Test
    void acheterPropriete() {
        Joueur joueurMock = Mockito.mock(Joueur.class);
        CasePropriete proprieteMock = Mockito.mock(CasePropriete.class);

        ctrlJeu.acheterPropriete(joueurMock, proprieteMock);

        Mockito.verify(joueurMock, Mockito.times(1)).acheterPropriete(proprieteMock);
    }

    @Test
    void afficherProprietes() {
        Joueur joueurMock = Mockito.mock(Joueur.class);

        ctrlJeu.afficherProprietes(joueurMock);

        Mockito.verify(joueurMock, Mockito.times(1)).afficherProprietes();
    }

    @Test
    void getPseudo() {
        Joueur joueurMock = Mockito.mock(Joueur.class);

        ctrlJeu.getPseudo(joueurMock);

        Mockito.verify(joueurMock, Mockito.times(1)).getPseudo();
    }

    @Test
    void getPosition() {
        Joueur joueurMock = Mockito.mock(Joueur.class);

        ctrlJeu.getPosition(joueurMock);

        Mockito.verify(joueurMock, Mockito.times(1)).getPosition();
    }

    @Test
    void getArgent() {
        Joueur joueurMock = Mockito.mock(Joueur.class);

        ctrlJeu.getArgent(joueurMock);

        Mockito.verify(joueurMock, Mockito.times(1)).getArgent();
    }

    @Test
    void payer() {
        Joueur joueurMock = Mockito.mock(Joueur.class);
        int montant = 100;

        ctrlJeu.payer(joueurMock, montant);

        Mockito.verify(joueurMock, Mockito.times(1)).payer(montant);
    }
}