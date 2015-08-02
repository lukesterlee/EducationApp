package hackaccess.c4q.nyc.educationapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by Willee on 8/1/15.
 */
public class Program implements Parcelable, Serializable {

    public static final Creator<Program> CREATOR = new Creator<Program>() {
        @Override
        public Program createFromParcel(Parcel parcel) {
            return new Program(parcel);
        }

        @Override
        public Program[] newArray(int size) {
            return new Program[size];
        }
    };


    private String name;
    private double distance;
    private double latitude;
    private double longitude;
    private String description;
    private String language;
    private String lastUpdated;
    private String phoneNumber;

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(distance);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(description);
        dest.writeString(language);
        dest.writeString(lastUpdated);
        dest.writeString(phoneNumber);
    }


    public Program() {
        name = "";
        distance = 0;
        latitude = 0;
        longitude = 0;
        description = "";
        language = "";
        lastUpdated = "";
        phoneNumber = "";
    }

    public Program(Parcel parcel) {
        name = parcel.readString();
        distance = parcel.readDouble();
        latitude = parcel.readDouble();
        longitude = parcel.readDouble();
        description = parcel.readString();
        language = parcel.readString();
        lastUpdated = parcel.readString();
        phoneNumber = parcel.readString();
    }

    public Program(String description, double distance, String language, String lastUpdated, double latitude, double longitude, String name, String phoneNumber) {
        this.description = description;
        this.distance = distance;
        this.language = language;
        this.lastUpdated = lastUpdated;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getDistance() {
        return distance + " mi";
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }


}

