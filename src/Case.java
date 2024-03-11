public abstract class Case {
    private int position;    
    
    // Constructeur
    public Case(int position) {
        this.position = position;
    }
    
    // Méthodes pour accéder et modifier les attributs (getters/setters)

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    // Méthode toString
    public String toString() {
        return "Case [position=" + position + "]";
    }
}
