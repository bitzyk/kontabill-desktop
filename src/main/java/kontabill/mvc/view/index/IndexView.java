package main.java.kontabill.mvc.view.index;

import main.java.kontabill.layout.Layout;
import main.java.kontabill.mvc.MVC;
import main.java.kontabill.mvc.controller.BaseAbstractController;
import main.java.kontabill.mvc.view.BaseAbstractView;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class IndexView extends BaseAbstractView {

    public IndexView(BaseAbstractController controller) {
        super(controller);
    }

    @Override
    public void render() {
        System.out.println(
                "Render index"
        );

        controllerPanel.add(new JButton("Index"));
    }

}
