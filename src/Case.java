public abstract class Case {
    private int position;
    private String type;    
    
    // Constructeur
    public Case(int position, String type) {
        this.position = position;
        this.type = type;
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
