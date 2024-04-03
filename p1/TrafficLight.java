package p1;

public class TrafficLight {
    private int id;
    private Light colour;

    public TrafficLight(int id, Light colour) {
        this.id = id;
        this.colour = colour;
    }

    public void change() {
        if (colour == Light.RED) {
            colour = Light.GREEN;
        } else {
            colour = Light.RED;
        }
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Light getColour() {
        return colour;
    }

    public void setColour(Light colour) {
        this.colour = colour;
    }
}

