package hackaccess.c4q.nyc.educationapp;

/**
 * Created by Willee on 8/1/15.
 */
public class Program {

    private String name;
    private double distance;

    public Program() {

    }

    public Program(double distance, String name) {
        this.distance = distance;
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
