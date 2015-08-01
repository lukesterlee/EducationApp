package AuntBertha;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Office {

    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @Expose
    private String city;
    @Expose
    private String name;
    @Expose
    private Double distance;
    @Expose
    private String address1;
    @Expose
    private Hours hours;
    @SerializedName("open_now_info")
    @Expose
    private OpenNowInfo openNowInfo;
    @Expose
    private String state;
    @Expose
    private Location location;
    @SerializedName("office_numeric_id")
    @Expose
    private String officeNumericId;
    @Expose
    private String postal;
    @SerializedName("supported_languages")
    @Expose
    private List<String> supportedLanguages = new ArrayList<String>();

    /**
     *
     * @return
     * The phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber
     * The phone_number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The distance
     */
    public Double getDistance() {
        return distance;
    }

    /**
     *
     * @param distance
     * The distance
     */
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    /**
     *
     * @return
     * The address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     *
     * @param address1
     * The address1
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     *
     * @return
     * The hours
     */
    public Hours getHours() {
        return hours;
    }

    /**
     *
     * @param hours
     * The hours
     */
    public void setHours(Hours hours) {
        this.hours = hours;
    }

    /**
     *
     * @return
     * The openNowInfo
     */
    public OpenNowInfo getOpenNowInfo() {
        return openNowInfo;
    }

    /**
     *
     * @param openNowInfo
     * The open_now_info
     */
    public void setOpenNowInfo(OpenNowInfo openNowInfo) {
        this.openNowInfo = openNowInfo;
    }

    /**
     *
     * @return
     * The state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     * The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     * The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     * @return
     * The officeNumericId
     */
    public String getOfficeNumericId() {
        return officeNumericId;
    }

    /**
     *
     * @param officeNumericId
     * The office_numeric_id
     */
    public void setOfficeNumericId(String officeNumericId) {
        this.officeNumericId = officeNumericId;
    }

    /**
     *
     * @return
     * The postal
     */
    public String getPostal() {
        return postal;
    }

    /**
     *
     * @param postal
     * The postal
     */
    public void setPostal(String postal) {
        this.postal = postal;
    }

    /**
     *
     * @return
     * The supportedLanguages
     */
    public List<String> getSupportedLanguages() {
        return supportedLanguages;
    }

    /**
     *
     * @param supportedLanguages
     * The supported_languages
     */
    public void setSupportedLanguages(List<String> supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }

}