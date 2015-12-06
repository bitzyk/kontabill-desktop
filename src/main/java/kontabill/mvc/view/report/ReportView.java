package main.java.kontabill.mvc.view.report;

import main.java.kontabill.layout.Layout;
import main.java.kontabill.mvc.MVC;
import main.java.kontabill.mvc.controller.BaseAbstractController;
import main.java.kontabill.mvc.view.BaseAbstractView;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class ReportView extends BaseAbstractView {

    public ReportView(BaseAbstractController controller) {
        super(controller);
    }

    @Override
    public void render() {
        System.out.println(
                "Render"
        );
        controllerPanel.setBackground(Color.RED);
        controllerPanel.add(new JButton("Report"));
    }

}
