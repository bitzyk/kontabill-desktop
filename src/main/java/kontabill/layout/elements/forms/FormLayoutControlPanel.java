package main.java.kontabill.layout.elements.forms;

import main.java.kontabill.Kontabill;
import main.java.kontabill.layout.elements.inputs.FormElement;
import main.java.kontabill.layout.elements.labels.LabelForm;
import main.java.kontabill.mvc.model.forms.base.BaseAbstractForm;

import javax.swing.*;
import java.util.Map;

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

/** test method **/
    public void testHide() {

        String[][] elementsDefinition = getForm().getElementsDefinition();
        Map<String, FormElement> formElementMap =  getForm().getFormElements();

        for (int i = 0; i < elementsDefinition.length; i++) {

            String elementKeyId = elementsDefinition[i][BaseAbstractForm.ELEMENT_DEFINITION_KEY_KEYID];
            String label = elementsDefinition[i][BaseAbstractForm.ELEMENT_DEFINITION_KEY_LABEL];

            JComponent formElement = (JComponent)formElementMap.get(elementKeyId);

            getPanel().remove(formElement);


        }
        Kontabill.getInstance().getLayout().getFrame().setVisible(true);
    }

    /** test method **/
    public void testShow() {


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

            getPanel().add(formElement, constaints);

        }

        Kontabill.getInstance().getLayout().getFrame().setVisible(true);
    }

}
