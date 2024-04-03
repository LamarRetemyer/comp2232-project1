package p1;
public enum Light {
    RED("The light is red"),
    GREEN("The light is green");

    private String description;

    Light(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

        
    
}
