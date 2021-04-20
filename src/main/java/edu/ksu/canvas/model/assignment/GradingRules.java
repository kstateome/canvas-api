package edu.ksu.canvas.model.assignment;

import java.io.Serializable;
import java.util.List;

import edu.ksu.canvas.model.BaseCanvasModel;

/**
 * Object to represent Grading Rules which can optionally be embedded inside of an assignment group object
 */
public class GradingRules extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    // Number of lowest scores to be dropped for each user.
    private Integer dropLowest;
    // Number of highest scores to be dropped for each user.
    private Integer dropHighest;
    // Assignment IDs that should never be dropped.
    private List<Integer> neverDrop;

    public Integer getDropLowest() {
        return dropLowest;
    }

    public void setDropLowest(Integer dropLowest) {
        this.dropLowest = dropLowest;
    }

    public Integer getDropHighest() {
        return dropHighest;
    }

    public void setDropHighest(Integer dropHighest) {
        this.dropHighest = dropHighest;
    }

    public List<Integer> getNeverDrop() {
        return neverDrop;
    }

    public void setNeverDrop(List<Integer> neverDrop) {
        this.neverDrop = neverDrop;
    }

}
