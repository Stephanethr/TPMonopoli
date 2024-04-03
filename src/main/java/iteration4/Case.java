package iteration4;

public abstract class Case {
    private String type;
    private String nom;
    
    // Constructeur
    public Case(String type, String nom) {
        this.type = type;
        this.nom = nom;
    }
    
    // Méthodes pour accéder et modifier les attributs (getters/setters)

    public String getType() {
        return type;
    }

    public String getNom() {
        return nom;
    }

    // Méthode toString
    public String toString() {
        return "Case " + nom +"\n";
    }
}
