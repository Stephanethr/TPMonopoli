package iteration4.model;

import java.util.ArrayList;
import java.util.List;


public class Carte {
    private String type;
    private String description;
    private List<Action> actions; // Liste d'actions associées à la carte

    // Constructeur
    public Carte(String type, String description, List<Action> actions) {
        this.type = type;
        this.description = description;
        this.actions = actions;
    }

    // Getters et Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "Carte [type=" + type + ", description=" + description + ", actions=" + actions + "]\n";
    }
    // Méthode pour ajouter une action à la liste
    public void addAction(Action action) {
        if (this.actions == null) {
            this.actions = new ArrayList<>();
        }
        this.actions.add(action);
    }
}