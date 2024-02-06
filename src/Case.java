public class Case {
    private int position;
    private String nom;
    private String type;

    
    
    // Constructeur
    public Case(int position, String nom, String type) {
        this.position = position;
        this.nom = nom;
        this.type = type;
    }
    
    // Méthodes pour accéder et modifier les attributs (getters/setters)

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
