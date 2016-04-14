package main.java.kontabill.layout.elements.forms;

import main.java.kontabill.Kontabill;
import main.java.kontabill.layout.elements.inputs.FormElement;
import main.java.kontabill.mvc.model.forms.base.BaseAbstractForm;
import main.java.kontabill.mvc.model.forms.base.DynamicChangeConfig;

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

    /**
     * variable to store components added in the panel
     * pattern: formKey -> ListComponents (e.g. JLabel, FormElement)
     */
    private Map<String, List<FormPanelComponent>> panelComponents = new HashMap<>();

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
        addDynamicChangeListeners();
    }

    public boolean validate()
    {
        // close previous tooltips warnings
        formTooltip.closeAllTooltipsWarning();

        boolean isValid = getForm().getFormValidator().validateForm();

        // show tooltips errors if form is not valid
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

    private void addDynamicChangeListeners()
    {
        if(form.getDynamicChangeConfigMap().size() > 0) {

            Set<String> keys = form.getDynamicChangeConfigMap().keySet();
            Iterator<String> iterator = keys.iterator();

            while (iterator.hasNext()) {
                DynamicChangeConfig dynamicChangeConfig = form.getDynamicChangeConfigMap().get(
                        iterator.next()
                );

                FormElement formElement = form.getFormElement(dynamicChangeConfig.getForElementWithKey());

                formElement.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedItem = (String) ((JComboBox) e.getSource()).getSelectedItem();

                        System.out.println(
                                "panel components size " + panelComponents.size()
                        );

                        if(selectedItem == dynamicChangeConfig.getForElementWithValue()) {

                            // 1. hide all elements, less the current form element that trigers dynamic change
                            Iterator<String> iteratorPanelComponents = (panelComponents.keySet()).iterator();
                            while (iteratorPanelComponents.hasNext()) {

                                String formKey = iteratorPanelComponents.next();
                                // do not hide the current element
                                if(formKey == dynamicChangeConfig.getForElementWithKey()) {
                                    continue;
                                }

                                List<FormPanelComponent> componentList = panelComponents.get(formKey);

                                for (int i = 0; i < componentList.size(); i++) {
                                    getPanel().remove(
                                            componentList.get(i).getComponent() // hidding
                                    );
                                }
                            }

                            // 2. show the right elements
                            for (int i = 0; i < dynamicChangeConfig.getShowElements().size(); i++) {
                                List<FormPanelComponent> componentsToShow = panelComponents.get(
                                        dynamicChangeConfig.getShowElements().get(i)
                                );
                                for (int j = 0; j < componentsToShow.size(); j++) {
                                    if(null == componentsToShow.get(j).getConstraint()) {
                                        getPanel().add(
                                                componentsToShow.get(j).getComponent()
                                        );
                                    } else {
                                        getPanel().add(
                                                componentsToShow.get(j).getComponent(),
                                                componentsToShow.get(j).getConstraint()
                                        );
                                    }
                                }
                            }

                            // when remove validators for hidden elements is set to true (default behaviour)
                            if(true == dynamicChangeConfig.isRemoveValidatorsForHiddenElements()) {
                                // 3. remove all validators -> less the validator for current dynamicChange trigger
                                for (String formKey : form.getFormElementConfig().getAllFormKeys()) {

                                    // do not remove validator for current dynamicChange trigger
                                    if(dynamicChangeConfig.getForElementWithKey() == formKey) {
                                        continue;
                                    }

                                    // remove validator for formKey
                                    form.getFormValidator().removeValidatorsForElement(formKey);
                                }

                                // 4. add validators for the right elements
                                for (int i = 0; i < dynamicChangeConfig.getShowElements().size(); i++) {
                                    form.getFormValidator().setValidatorsForElement(
                                            dynamicChangeConfig.getShowElements().get(i)
                                    );
                                }
                            }

                            // 5. close previous tooltips warnings (the previous elements with errors could not be in current form layout)
                            formTooltip.closeAllTooltipsWarning();

                            // repaint components in controller panel
                            Kontabill.getInstance().getLayout().getControllerPanel().repaintComponents();
                        }
                    }
                });

                // trigger actionEvent for current formKey
                ((JComboBox) formElement).setSelectedItem(formElement.getValue());
            }
        }
    }

    protected void addElementsInPanel(String formKey, Component component)
    {
        // add component in the real awt component
        getPanel().add(component);

        addElementsInPanelComp(new FormPanelComponent(formKey, component));
    }

    protected void addElementsInPanel(String formKey, Component component, String constaints)
    {
        // add component in the real awt component
        getPanel().add(component, constaints);

        addElementsInPanelComp(new FormPanelComponent(formKey, component, constaints));
    }


    private void addElementsInPanelComp(FormPanelComponent formPanelComponent)
    {
        List<FormPanelComponent> componentsForFormKey = panelComponents.get(formPanelComponent.getFormKey());

        if(null == componentsForFormKey) {
            // list unitialized (no component in it for key: formKey) -> initialize list
            componentsForFormKey = new ArrayList<>();
            // add list to panel components
            panelComponents.put(formPanelComponent.getFormKey(), componentsForFormKey);
        }

        componentsForFormKey.add(formPanelComponent);
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

            /**
             * algorithm:
             *  - when focus is gained on the current form element get opposite form element which lost focus
             *      - if opposite form element is a form element for the current form (on multi form dialogs this could not be the case)
             *          - if opposite form element is not valid show errors for that opposite element
             */
            Component c = e.getOppositeComponent();

            if(c instanceof FormElement && c instanceof JComponent && getForm().formElementBelongToThisForm((FormElement)c)) {

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


    class FormPanelComponent {

        private String formKey;

        private String constraint;

        private Component component;

        public FormPanelComponent(String formKey, Component component) {
            this.formKey = formKey;
            this.component = component;
        }

        public FormPanelComponent(String formKey, Component component, String constraint) {
            this(formKey, component);
            this.constraint = constraint;
        }

        public Component getComponent() {
            return component;
        }

        public String getConstraint() {
            return constraint;
        }

        public String getFormKey() {
            return formKey;
        }
    }
}
