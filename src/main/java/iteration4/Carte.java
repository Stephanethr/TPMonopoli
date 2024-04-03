package iteration4;

public class Carte {

    private String type;
    private String description;

    public Carte(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override

    public String toString() {
        return "Carte{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


    
}
