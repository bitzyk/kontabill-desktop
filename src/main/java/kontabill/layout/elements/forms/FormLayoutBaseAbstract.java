package main.java.kontabill.layout.elements.forms;

import main.java.kontabill.layout.elements.inputs.FormElement;
import main.java.kontabill.mvc.model.forms.base.BaseAbstractForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 *
 */
abstract public class FormLayoutBaseAbstract {

    private BaseAbstractForm form;

    private JPanel panel;

    private FormTooltipManager formTooltip = new FormTooltipManager();

    public FormLayoutBaseAbstract(BaseAbstractForm form, JPanel panel)
    {
        this.form = form;
        this.panel = panel;

        init();
    }

    private void init()
    {
        initLayout();
        addListeners();
    }

    public boolean validate()
    {
        // close previous tooltips warnings
        formTooltip.closeAllTooltipsWarning();

        boolean isValid = getForm().getFormValidator().validateForm();

        // show tooltips erros if form is not valid
        if(isValid == false) {
            showFormErrors();
        }

        return isValid;
    }

    private void addListeners()
    {
        Map<String, FormElement> formElementMap =  getForm().getFormElements();

        Set<String> keys = formElementMap.keySet();
        Iterator<String> iterator = keys.iterator();

        while (iterator.hasNext()) {
            String formKey = iterator.next();
            JComponent formElement = (JComponent) formElementMap.get(formKey);

            formElement.addKeyListener(new FormElementKeyListener(formKey, formElement));
            formElement.addFocusListener(new FormElementFocusListener(formKey, formElement));

        }
    }

    protected abstract void initLayout();

    public void showForm()
    {

    }

    public BaseAbstractForm getForm() {
        return form;
    }

    public JPanel getPanel() {
        return panel;
    }


    private void showFormErrors()
    {
        Map<String, FormElement> formElementMap =  getForm().getFormElements();

        Set<String> keys = formElementMap.keySet();
        Iterator<String> iterator = keys.iterator();

        while (iterator.hasNext()) {
            String formKey = iterator.next();
            JComponent formElement = (JComponent) formElementMap.get(formKey);
            List<String> errors = getForm().getFormValidator().getValidationElementErrors(formKey);

            if(errors.size() > 0) {
                formTooltip.addTooltip(formKey, formElement, errors.get(0));
            }
        }
    }

    // define listeners
    abstract class FormElementListener {

        protected JComponent formElement;

        protected String formKey;

        public FormElementListener(String formKey, JComponent formElement)
        {
            this.formElement = formElement;
            this.formKey = formKey;
        }
    }

    class FormElementKeyListener extends FormElementListener implements KeyListener {

        public FormElementKeyListener(String formKey, JComponent formElement) {
            super(formKey, formElement);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // on enter inside form run the subbmit block runner behaviour
                form.getSubmitBlockRunner().run();
            }

        }

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {}
    }

    class FormElementFocusListener extends FormElementListener implements FocusListener {

        public FormElementFocusListener(String formKey, JComponent formElement) {
            super(formKey, formElement);
        }

        @Override
        public void focusGained(FocusEvent e)
        {
            // close tooltips for the current input
            formTooltip.closeTooltip(formKey);

            Component c = e.getOppositeComponent();

            if(c instanceof FormElement && c instanceof JComponent) {

                String prevFormkey = getForm().getFormKey((FormElement) c);

                boolean isValid = form.getFormValidator().validateElement(prevFormkey, (FormElement) c);

                // if is not valid show the tooltip with the error
                if (! isValid) {
                    List<String> errors = form.getFormValidator().getValidationElementErrors(prevFormkey);

                    if(errors.size() > 0) {
                        formTooltip.addTooltip(prevFormkey, (JComponent) c, errors.get(0));
                    }
                }

            }

        }

        @Override
        public void focusLost(FocusEvent e)
        {

        }
    }
}
