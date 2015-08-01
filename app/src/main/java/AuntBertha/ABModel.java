package AuntBertha;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kadeemmaragh on 8/1/15.
 */
public class ABModel {
    @Expose
    private String count;
    @Expose
    private List<Program> programs = new ArrayList<Program>();
    @Expose
    private String kind;
    @Expose
    private String etag;

    /**
     *
     * @return
     * The count
     */
    public String getCount() {
        return count;
    }

    /**
     *
     * @param count
     * The count
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     *
     * @return
     * The programs
     */
    public List<Program> getPrograms() {
        return programs;
    }

    /**
     *
     * @param programs
     * The programs
     */
    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    /**
     *
     * @return
     * The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     *
     * @param kind
     * The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     *
     * @return
     * The etag
     */
    public String getEtag() {
        return etag;
    }

    /**
     *
     * @param etag
     * The etag
     */
    public void setEtag(String etag) {
        this.etag = etag;
    }
}
