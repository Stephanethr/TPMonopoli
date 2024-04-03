package iteration4;

// Classe représentant une action individuelle associée à une carte
class Action {
    private String type;
    private String destination; // Peut être null pour certains types d'action
    private Integer amount; // Peut être null pour certains types d'action
    private String deckName; // Peut être null sauf pour "pioche"
    private String from;
    private String to;

    // Constructeur
    public Action(String type, String destination, Integer amount, String deckName, String from, String to) {
        this.type = type;
        this.destination = destination;
        this.amount = amount;
        this.deckName = deckName;
        this.from = from;
        this.to = to;
    }

    // Getters et Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDestination() {
        return destination;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getdeckName() {
        return deckName;
    }

    public void setdeckName(String deckName) {
        this.deckName = deckName;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    // Méthode toString pour affichage
    @Override
    public String toString() {
        return " Action [type=" + type + ", destination=" + destination + ", amount=" + amount + ", deckName="
                + deckName + "] \n";
    }
}