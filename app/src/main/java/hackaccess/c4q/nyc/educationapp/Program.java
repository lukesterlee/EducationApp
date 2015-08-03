package hackaccess.c4q.nyc.educationapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

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
    private String distance;
    private double latitude;
    private double longitude;
    private String description;
    private String language;
    private String lastUpdated;
    private String phoneNumber;
    private String programId;
    private String zipcode;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;
    private String address;

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(distance);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(description);
        dest.writeString(language);
        dest.writeString(lastUpdated);
        dest.writeString(phoneNumber);
        dest.writeString(programId);
        dest.writeString(zipcode);
        dest.writeString(monday);
        dest.writeString(tuesday);
        dest.writeString(wednesday);
        dest.writeString(thursday);
        dest.writeString(friday);
        dest.writeString(saturday);
        dest.writeString(sunday);
        dest.writeString(address);

    }


    public Program() {
        name = "";
        distance = "";
        latitude = 0;
        longitude = 0;
        description = "";
        language = "";
        lastUpdated = "";
        phoneNumber = "";
        programId = "";
        zipcode = "";
        address = "";

    }

    public Program(Parcel parcel) {
        name = parcel.readString();
        distance = parcel.readString();
        latitude = parcel.readDouble();
        longitude = parcel.readDouble();
        description = parcel.readString();
        language = parcel.readString();
        lastUpdated = parcel.readString();
        phoneNumber = parcel.readString();
        programId = parcel.readString();
        zipcode = parcel.readString();
        monday = parcel.readString();
        tuesday = parcel.readString();
        wednesday = parcel.readString();
        thursday = parcel.readString();
        friday = parcel.readString();
        saturday = parcel.readString();
        sunday = parcel.readString();
        address = parcel.readString();
    }

    public Program(String description, String distance,
                   String language, String lastUpdated,
                   double latitude, double longitude,
                   String name, String phoneNumber,
                   String programId, String zipcode, String monday,
                   String tuesday, String wednesday, String thursday,
                   String friday, String saturday, String sunday, String address) {
        this.description = description;
        this.distance = distance;
        this.language = language;
        this.lastUpdated = lastUpdated;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.programId = programId;
        this.zipcode = zipcode;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
        this.address = address;

    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
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

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }


}

