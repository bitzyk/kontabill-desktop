package main.java.kontabill.layout.elements.forms;

import main.java.kontabill.layout.elements.inputs.FormElement;
import main.java.kontabill.layout.elements.labels.LabelForm;
import main.java.kontabill.mvc.model.forms.base.BaseAbstractForm;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 *
 */
public class FormLayoutControlPanel extends FormLayoutBaseAbstract {

    public FormLayoutControlPanel(BaseAbstractForm form, JPanel panel)
    {
        super(form, panel);
    }

    private static final int INPUT_HEIGHT = 35;

    private static final int INPUT_WIDTH = 450;

    private static final int LABEL_WIDTH = 200;


    @Override
    protected void initLayout()
    {

        String[][] elementsDefinition = getForm().getElementsDefinition();
        Map<String, FormElement> formElementMap =  getForm().getFormElements();

        for (int i = 0; i < elementsDefinition.length; i++) {

            String elementKeyId = elementsDefinition[i][BaseAbstractForm.ELEMENT_DEFINITION_KEY_KEYID];
            String label = elementsDefinition[i][BaseAbstractForm.ELEMENT_DEFINITION_KEY_LABEL];

            JComponent formElement = (JComponent)formElementMap.get(elementKeyId);
            JLabel jLabel = new LabelForm(label);

            // define constraint label and formElement
            String constaintLabel = "width 50:" + LABEL_WIDTH + ":" + LABEL_WIDTH + ", height 20:" + INPUT_HEIGHT + ":" + INPUT_HEIGHT;
            String constaintFormElem = "wrap, gap bottom 13, width 50:" + INPUT_WIDTH + ":" + INPUT_WIDTH + ", height 20:" + INPUT_HEIGHT + ":" + INPUT_HEIGHT;

            addElementsInPanel(elementKeyId, jLabel, constaintLabel);
            addElementsInPanel(elementKeyId, formElement, constaintFormElem);
        }
    }

}
