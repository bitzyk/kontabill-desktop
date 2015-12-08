package main.java.kontabill.layout.elements.forms;

import main.java.kontabill.Kontabill;
import main.java.kontabill.layout.containers.FrameMain;
import main.java.kontabill.layout.elements.labels.LabelForm;
import main.java.kontabill.mvc.model.forms.base.BaseAbstractForm;
import main.java.kontabill.mvc.model.forms.base.ElementConfig;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class FormLayoutDialog extends FormLayoutBaseAbstract {

    private JDialog dialogForm;

    public FormLayoutDialog(BaseAbstractForm form, JPanel panel) {
        super(form, panel);
    }

    @Override
    protected void initLayout()
    {
        initDialog();
        initFormElements();

        // set size and location
        dialogForm.pack();
        setDialogLocation();

        //. make dalog visible
        dialogForm.setVisible(true);

    }

    private void initDialog()
    {
        dialogForm = new JDialog(
                getDialogFrame(),
                "Editeaza delegat",
                false
        );

        dialogForm.setResizable(false);

        dialogForm.setContentPane(getPanel());
        dialogForm.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        MigLayout migLayout = new MigLayout();
        getPanel().setLayout(migLayout);
    }


    private void initFormElements()
    {
        getPanel().add(new JLabel(), "wrap, gap top 40");

        Map<String, ElementConfig> elementConfigMap = getForm().getFormElementConfig().getElementConfigMap();
        Set<String> keys = elementConfigMap.keySet();
        Iterator<String> iterator = keys.iterator();

        while (iterator.hasNext()) {
            ElementConfig elementConfig = elementConfigMap.get(iterator.next());

            //System.out.println("elements: " + elementConfig.getInputKey());

            JComponent formElement = (JComponent) getForm().getFormElements().get(elementConfig.getInputKey());
            String label = elementConfig.getInputLabel();

            getPanel().add(new LabelForm(label), "gapleft 150, gapright 30");
            getPanel().add(formElement, "wrap, gap bottom 13, gapright 150");
        }

        getPanel().add(new JLabel(), "wrap, gap top 40");
    }

    private FrameMain getDialogFrame()
    {
        return Kontabill.getInstance().getLayout().getFrame();
    }

    private void setDialogLocation()
    {
        // middle on x axis
        int width = (getDialogFrame().getWidthF()) / 2 - (dialogForm.getWidth() / 2);

        // the bottom stop on the middle if the dialog is less than half of the screen, if not will continue starting from top
        int height = (getDialogFrame().getHeightF() / 2) - (dialogForm.getHeight());

        dialogForm.setLocation(width, height);
    }




}