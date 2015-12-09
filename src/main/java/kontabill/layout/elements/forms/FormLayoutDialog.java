package main.java.kontabill.layout.elements.forms;

import main.java.kontabill.Kontabill;
import main.java.kontabill.layout.containers.FrameMain;
import main.java.kontabill.layout.elements.factories.ButtonFactory;
import main.java.kontabill.layout.elements.labels.LabelForm;
import main.java.kontabill.layout.elements.separators.LineSeparator;
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


    private static final JButton CANCEL_BUTTON = ButtonFactory.createButtonCancelDefault("Anuleaza");

    private static final JButton SUBMIT_BUTTON = ButtonFactory.createButtonGreenSubmitSmall("Salveaza editare");

    private JPanel headerPanel;

    private JPanel formPanel;

    private JPanel footerPanel;


    @Override
    protected void initLayout()
    {
        initDialog();

        initHeaderSection();
        initFormSection();
        initFooterSection();

        composeDialogSections();

        dialogForm.pack(); // this will set the initial with)
        setDialogLocation(); // set size and location

        //. make dalog visible
        dialogForm.setVisible(true);

        initButtonListeners();
    }

    private void composeDialogSections()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 15, 0);
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        getPanel().add(headerPanel, gbc);

        // constraint for formPanel
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 1;

        getPanel().add(formPanel, gbc);

        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        getPanel().add(footerPanel, gbc);
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


        getPanel().setLayout(new GridBagLayout()); // set gridBagLayout to main panle
        getPanel().setBackground(Color.WHITE);

    }


    private void initFormSection()
    {
        this.formPanel = new JPanel(new MigLayout()); // set migLayout to form panel
        this.formPanel.setBackground(Color.WHITE);

        Map<String, ElementConfig> elementConfigMap = getForm().getFormElementConfig().getElementConfigMap();
        Set<String> keys = elementConfigMap.keySet();
        Iterator<String> iterator = keys.iterator();

        while (iterator.hasNext()) {
            ElementConfig elementConfig = elementConfigMap.get(iterator.next());

            //System.out.println("elements: " + elementConfig.getInputKey());

            JComponent formElement = (JComponent) getForm().getFormElements().get(elementConfig.getInputKey());
            String label = elementConfig.getInputLabel();

            this.formPanel.add(new LabelForm(label), "gapleft 100, gapright 30");
            this.formPanel.add(formElement, "wrap, gap bottom 13, gapright 100");
        }
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


    private void initFooterSection()
    {
        footerPanel = new JPanel();
        footerPanel.setLayout(new GridBagLayout());
        footerPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel lineSeparator = new LineSeparator();
        footerPanel.add(lineSeparator, gbc);

        JPanel panelButtons = new JPanel();
        panelButtons.setBackground(Color.WHITE);
        panelButtons.setLayout(new GridBagLayout());

        GridBagConstraints gbcPanelButtons = new GridBagConstraints();
        gbcPanelButtons.gridy = 0;
        gbcPanelButtons.gridx = 0;
        gbcPanelButtons.insets = new Insets(0, 0, 0, 5);
        panelButtons.add(CANCEL_BUTTON, gbcPanelButtons);

        gbcPanelButtons.insets = new Insets(0, 5, 0, 0);
        gbcPanelButtons.gridx = 1;
        panelButtons.add(SUBMIT_BUTTON, gbcPanelButtons);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        footerPanel.add(panelButtons, gbc);
    }

    private void initHeaderSection()
    {
        headerPanel = new JPanel();
        headerPanel.setLayout(new GridBagLayout());
        headerPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;

        JLabel label = new JLabel("Editeaza delegat xxxxxxxx");
        label.setFont(new Font("Dialog", Font.BOLD, 16));

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel lineSeparator = new LineSeparator();


        headerPanel.add(label);
        headerPanel.add(lineSeparator, gbc);

    }


    private void initButtonListeners()
    {
        CANCEL_BUTTON.addActionListener(e -> {
            dialogForm.dispose();
        });
    }


}