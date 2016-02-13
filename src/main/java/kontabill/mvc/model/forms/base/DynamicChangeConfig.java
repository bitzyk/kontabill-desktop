package main.java.kontabill.mvc.model.forms.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cbitoi on 09/02/16.
 */
public class DynamicChangeConfig {

    private String forElementWithKey;

    private String forElementWithValue;

    private List<String> showElements = new ArrayList<>();

    private boolean removeValidatorsForHiddenElements = true;


    public String getForElementWithKey() {
        return forElementWithKey;
    }

    public void setForElementWithKey(String forElementWithKey) {
        this.forElementWithKey = forElementWithKey;
    }

    public String getForElementWithValue() {
        return forElementWithValue;
    }

    public void setForElementWithValue(String forElementWithValue) {
        this.forElementWithValue = forElementWithValue;
    }

    public List<String> getShowElements() {
        return showElements;
    }

    public void setShowElements(List<String> showElements) {
        this.showElements = showElements;
    }

    public boolean isRemoveValidatorsForHiddenElements() {
        return removeValidatorsForHiddenElements;
    }

    public void setRemoveValidatorsForHiddenElements(boolean removeValidatorsForHiddenElements) {
        this.removeValidatorsForHiddenElements = removeValidatorsForHiddenElements;
    }
}
