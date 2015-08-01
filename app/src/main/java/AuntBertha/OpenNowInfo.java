package auntbertha;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpenNowInfo {

    @SerializedName("day_of_the_week")
    @Expose
    private String dayOfTheWeek;
    @SerializedName("open_now")
    @Expose
    private Boolean openNow;

    /**
     *
     * @return
     * The dayOfTheWeek
     */
    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    /**
     *
     * @param dayOfTheWeek
     * The day_of_the_week
     */
    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    /**
     *
     * @return
     * The openNow
     */
    public Boolean getOpenNow() {
        return openNow;
    }

    /**
     *
     * @param openNow
     * The open_now
     */
    public void setOpenNow(Boolean openNow) {
        this.openNow = openNow;
    }

}