public class Propriete extends Case {

    private int prix;
    private Joueur proprietaire;

    public Propriete(int position, int prix) {
        super(position);
        this.prix = prix;
        this.proprietaire = null;
    }

    public int getPrix() {
        return prix;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }

    public void acheter(Joueur joueur) {
        if (joueur != null && joueur.getProprietes() != null && proprietaire == null) {
            if (joueur.getArgent() >= prix) {
                joueur.setArgent(joueur.getArgent() - prix);
                setProprietaire(joueur);
                joueur.getProprietes().add(this);
            }
        }
    }

    public void payerLoyer(Joueur joueur) {
        if (proprietaire != null && proprietaire != joueur) {
            joueur.setArgent(joueur.getArgent() - prix);
            proprietaire.setArgent(proprietaire.getArgent() + prix);
        }
    }

    public String toString() {
        return "Propriete [position=" + getPosition() + ", prix=" + prix + ", proprietaire=" + proprietaire + "]";
    }
    
}
