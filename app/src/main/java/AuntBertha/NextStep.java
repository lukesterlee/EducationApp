package AuntBertha;

import com.google.gson.annotations.Expose;

public class NextStep {

    @Expose
    private String action;
    @Expose
    private String contact;
    @Expose
    private String channel;

    /**
     *
     * @return
     * The action
     */
    public String getAction() {
        return action;
    }

    /**
     *
     * @param action
     * The action
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     *
     * @return
     * The contact
     */
    public String getContact() {
        return contact;
    }

    /**
     *
     * @param contact
     * The contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     *
     * @return
     * The channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     *
     * @param channel
     * The channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

}