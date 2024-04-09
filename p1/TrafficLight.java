package p1;

public class TrafficLight {
    private int id;
    private Light colour;

    public TrafficLight(int id) {
        this.id = id;
        this.colour = Light.RED; // Initialize with red light
    }

    public void change() {
        // Implement logic to change the traffic light color
        if (colour == Light.RED) {
            colour = Light.GREEN;
        } else {
            colour = Light.RED;
        }
    } 
   
       // Getters
    public int getId() {
        return id;
    }

    public Light getColour() {
        return colour;
    }

    public boolean verify() {
        return colour != null;
    }

    public void setColour(Light colour) {
        this.colour = colour;
    }
    // Setters
    public void setId(int id) {
        this.id = id;
    }
    // Other methods specific to TrafficLight can be added
}
