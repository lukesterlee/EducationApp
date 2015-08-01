package AuntBertha;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Program {

    @SerializedName("next_steps")
    @Expose
    private List<NextStep> nextSteps = new ArrayList<NextStep>();
    @SerializedName("attribute_tags")
    @Expose
    private List<String> attributeTags = new ArrayList<String>();
    @SerializedName("grain_location")
    @Expose
    private List<String> grainLocation = new ArrayList<String>();
    @SerializedName("provider_numeric_id")
    @Expose
    private String providerNumericId;
    @Expose
    private String id;
    @SerializedName("update_date")
    @Expose
    private String updateDate;
    @SerializedName("wl_score")
    @Expose
    private Double wlScore;
    @Expose
    private String availability;
    @Expose
    private Double score;
    @SerializedName("entry_date")
    @Expose
    private String entryDate;
    @Expose
    private String grain;
    @SerializedName("provider_name")
    @Expose
    private String providerName;
    @SerializedName("supported_languages")
    @Expose
    private List<String> supportedLanguages = new ArrayList<String>();
    @SerializedName("website_url")
    @Expose
    private String websiteUrl;
    @Expose
    private Boolean isOfficeAvailable;
    @Expose
    private String description;
    @Expose
    private List<String> rules = new ArrayList<String>();
    @SerializedName("facebook_url")
    @Expose
    private String facebookUrl;
    @SerializedName("free_or_reduced")
    @Expose
    private String freeOrReduced;
    @Expose
    private String directions;
    @Expose
    private Double distance;
    @Expose
    private String name;
    @SerializedName("twitter_id")
    @Expose
    private String twitterId;
    @SerializedName("program_numeric_id")
    @Expose
    private String programNumericId;
    @SerializedName("service_tags")
    @Expose
    private List<String> serviceTags = new ArrayList<String>();
    @Expose
    private List<Office> offices = new ArrayList<Office>();

    /**
     *
     * @return
     * The nextSteps
     */
    public List<NextStep> getNextSteps() {
        return nextSteps;
    }

    /**
     *
     * @param nextSteps
     * The next_steps
     */
    public void setNextSteps(List<NextStep> nextSteps) {
        this.nextSteps = nextSteps;
    }



    /**
     *
     * @return
     * The attributeTags
     */
    public List<String> getAttributeTags() {
        return attributeTags;
    }

    /**
     *
     * @param attributeTags
     * The attribute_tags
     */
    public void setAttributeTags(List<String> attributeTags) {
        this.attributeTags = attributeTags;
    }

    /**
     *
     * @return
     * The grainLocation
     */
    public List<String> getGrainLocation() {
        return grainLocation;
    }

    /**
     *
     * @param grainLocation
     * The grain_location
     */
    public void setGrainLocation(List<String> grainLocation) {
        this.grainLocation = grainLocation;
    }

    /**
     *
     * @return
     * The providerNumericId
     */
    public String getProviderNumericId() {
        return providerNumericId;
    }

    /**
     *
     * @param providerNumericId
     * The provider_numeric_id
     */
    public void setProviderNumericId(String providerNumericId) {
        this.providerNumericId = providerNumericId;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The updateDate
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     *
     * @param updateDate
     * The update_date
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     *
     * @return
     * The wlScore
     */
    public Double getWlScore() {
        return wlScore;
    }

    /**
     *
     * @param wlScore
     * The wl_score
     */
    public void setWlScore(Double wlScore) {
        this.wlScore = wlScore;
    }

    /**
     *
     * @return
     * The availability
     */
    public String getAvailability() {
        return availability;
    }

    /**
     *
     * @param availability
     * The availability
     */
    public void setAvailability(String availability) {
        this.availability = availability;
    }

    /**
     *
     * @return
     * The score
     */
    public Double getScore() {
        return score;
    }

    /**
     *
     * @param score
     * The score
     */
    public void setScore(Double score) {
        this.score = score;
    }

    /**
     *
     * @return
     * The entryDate
     */
    public String getEntryDate() {
        return entryDate;
    }

    /**
     *
     * @param entryDate
     * The entry_date
     */
    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    /**
     *
     * @return
     * The grain
     */
    public String getGrain() {
        return grain;
    }

    /**
     *
     * @param grain
     * The grain
     */
    public void setGrain(String grain) {
        this.grain = grain;
    }

    /**
     *
     * @return
     * The providerName
     */
    public String getProviderName() {
        return providerName;
    }

    /**
     *
     * @param providerName
     * The provider_name
     */
    public void setProviderName(String providerName) {
        this.providerName = providerName;
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

    /**
     *
     * @return
     * The websiteUrl
     */
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    /**
     *
     * @param websiteUrl
     * The website_url
     */
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    /**
     *
     * @return
     * The isOfficeAvailable
     */
    public Boolean getIsOfficeAvailable() {
        return isOfficeAvailable;
    }

    /**
     *
     * @param isOfficeAvailable
     * The isOfficeAvailable
     */
    public void setIsOfficeAvailable(Boolean isOfficeAvailable) {
        this.isOfficeAvailable = isOfficeAvailable;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The rules
     */
    public List<String> getRules() {
        return rules;
    }

    /**
     *
     * @param rules
     * The rules
     */
    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    /**
     *
     * @return
     * The facebookUrl
     */
    public String getFacebookUrl() {
        return facebookUrl;
    }

    /**
     *
     * @param facebookUrl
     * The facebook_url
     */
    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    /**
     *
     * @return
     * The freeOrReduced
     */
    public String getFreeOrReduced() {
        return freeOrReduced;
    }

    /**
     *
     * @param freeOrReduced
     * The free_or_reduced
     */
    public void setFreeOrReduced(String freeOrReduced) {
        this.freeOrReduced = freeOrReduced;
    }

    /**
     *
     * @return
     * The directions
     */
    public String getDirections() {
        return directions;
    }

    /**
     *
     * @param directions
     * The directions
     */
    public void setDirections(String directions) {
        this.directions = directions;
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
     * The twitterId
     */
    public String getTwitterId() {
        return twitterId;
    }

    /**
     *
     * @param twitterId
     * The twitter_id
     */
    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    /**
     *
     * @return
     * The programNumericId
     */
    public String getProgramNumericId() {
        return programNumericId;
    }

    /**
     *
     * @param programNumericId
     * The program_numeric_id
     */
    public void setProgramNumericId(String programNumericId) {
        this.programNumericId = programNumericId;
    }

    /**
     *
     * @return
     * The serviceTags
     */
    public List<String> getServiceTags() {
        return serviceTags;
    }

    /**
     *
     * @param serviceTags
     * The service_tags
     */
    public void setServiceTags(List<String> serviceTags) {
        this.serviceTags = serviceTags;
    }

    /**
     *
     * @return
     * The offices
     */
    public List<Office> getOffices() {
        return offices;
    }

    /**
     *
     * @param offices
     * The offices
     */
    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

}