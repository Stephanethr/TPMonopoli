package iteration4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {
    @Test
    void pseudoReturnsCorrectValue() {
        Joueur joueur = new Joueur("John");
        assertEquals("John", joueur.getPseudo());
    }

    @Test
    void positionUpdatesCorrectly() {
        Joueur joueur = new Joueur("John");
        joueur.deplacement(5);
        assertEquals(5, joueur.getPosition());
    }

    @Test
    void positionLoopsAfter40() {
        Joueur joueur = new Joueur("John");
        joueur.deplacement(45);
        assertEquals(5, joueur.getPosition());
    }

    @Test
    void argentUpdatesCorrectly() {
        Joueur joueur = new Joueur("John");
        joueur.setArgent(2000);
        assertEquals(2000, joueur.getArgent());
    }

    @Test
    void argentCannotBeNegative() {
        Joueur joueur = new Joueur("John");
        joueur.setArgent(-1000);
        assertEquals(0, joueur.getArgent());
    }
}