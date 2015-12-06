package main.java.kontabill.layout.elements.forms;

import main.java.kontabill.layout.elements.forms.model.InputType;
import main.java.kontabill.layout.elements.inputs.TextFieldForm;
import main.java.kontabill.layout.elements.labels.LabelForm;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.util.ArrayList;

/**
 * Created
 */
public class FormLayout {

    JPanel panel;

    private ArrayList<JTextComponent> inputs = new ArrayList<>();

    public FormLayout(JPanel panel) {
        this.panel = panel;
    }

    public void addInput(InputType inputType, String label, String constaints)
    {
        JTextComponent input = null;

        if(inputType == InputType.TEXT_FIELD) {
            input = new TextFieldForm();
        }


        if(constaints == null) {
            constaints = "wrap, gap bottom 5";
        }

        inputs.add(input);

        panel.add(new LabelForm(label));
        panel.add(input, constaints);
    }

    public void addInputs (String[][] inputsConfig)
    {
        for (int i = 0; i < inputsConfig.length; i++) {
            String[] iconfig = inputsConfig[i];

            String constraints = null;
            if(i + 1 == inputsConfig.length) {
                constraints = "wrap";
            }

            addInput(InputType.valueOf(iconfig[0]), iconfig[1], constraints);
        }
    }

}
