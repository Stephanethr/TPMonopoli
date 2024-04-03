package iteration4;

public abstract class Case {
    private int position;
    private String type;
    private String nom;
    
    // Constructeur
    public Case(int position, String type, String nom) {
        this.position = position;
        this.type = type;
        this.nom = nom;
    }
    
    // Méthodes pour accéder et modifier les attributs (getters/setters)

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    // Méthode toString
    public String toString() {
        return "Case position=" + position + ", type=" + type + "\n";
    }
}
