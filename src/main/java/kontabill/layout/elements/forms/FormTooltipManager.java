package main.java.kontabill.layout.elements.forms;

import main.java.kontabill.layout.elements.inputs.FormElement;
import main.java.kontabill.layout.elements.tooltips.TooltipDefault;
import net.java.balloontip.BalloonTip;

import javax.swing.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by cbitoi on 08/12/15.
 */
public class FormTooltipManager {


    private Map<String, BalloonTip> tooltipContainer = new HashMap<>();


    public void addTooltip(String formKey, JComponent formElement, String label)
    {
        closeTooltip(formKey);

        tooltipContainer.put(formKey,
                new TooltipDefault(
                        formElement,
                        label

                )
        );
    }

    public void closeTooltip(String formKey)
    {
        BalloonTip tooltip = tooltipContainer.get(formKey);

        if(tooltip instanceof  BalloonTip) {
            tooltip.closeBalloon();
        }
    }


    public void closeAllTooltipsWarning()
    {
        Set<String> keys = tooltipContainer.keySet();
        Iterator<String> iterator = keys.iterator();

        while (iterator.hasNext()) {
            String key = iterator.next();
            closeTooltip(key);
        }
    }

}
