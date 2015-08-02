package hackaccess.c4q.nyc.educationapp;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Willee on 8/1/15.
 */
public class Program {

    private String name;
    private double distance;
    private LatLng latLng;

    public Program() {

    }

    public Program(double distance, String name, LatLng latLng) {
        this.distance = distance;
        this.name = name;
        this.latLng = latLng;
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

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public LatLng getLatLng() {
        return latLng;
    }

}

