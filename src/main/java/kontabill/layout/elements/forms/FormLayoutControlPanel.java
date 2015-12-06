package main.java.kontabill.layout.elements.forms;

import main.java.kontabill.layout.elements.inputs.FormElement;
import main.java.kontabill.layout.elements.labels.LabelForm;
import main.java.kontabill.mvc.model.entities.table_models.BaseAbstract;
import main.java.kontabill.mvc.model.forms.base.BaseAbstractForm;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.BalloonTipStyle;
import net.java.balloontip.styles.EdgedBalloonStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class FormLayoutControlPanel extends FormLayoutBaseAbstract {

    public FormLayoutControlPanel(BaseAbstractForm form, JPanel panel)
    {
        super(form, panel);
    }

    @Override
    protected void initLayout() {

        String constaints = null;

        String[][] elementsDefinition = getForm().getElementsDefinition();
        Map<String, FormElement> formElementMap =  getForm().getFormElements();

        for (int i = 0; i < elementsDefinition.length; i++) {

            String elementKeyId = elementsDefinition[i][BaseAbstractForm.ELEMENT_DEFINITION_KEY_KEYID];
            String label = elementsDefinition[i][BaseAbstractForm.ELEMENT_DEFINITION_KEY_LABEL];

            JComponent formElement = (JComponent)formElementMap.get(elementKeyId);

            if(elementsDefinition.length == (i+1)) {
                constaints = "wrap";
            } else {
                constaints = "wrap, gap bottom 13";
            }

            getPanel().add(new LabelForm(label));
            getPanel().add(formElement, constaints);

        }
    }
}
