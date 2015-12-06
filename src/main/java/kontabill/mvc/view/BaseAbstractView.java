package main.java.kontabill.mvc.view;

import main.java.kontabill.layout.Layout;
import main.java.kontabill.layout.containers.ControllerJPanel;
import main.java.kontabill.mvc.MVC;
import main.java.kontabill.mvc.Request;
import main.java.kontabill.mvc.controller.BaseAbstractController;

/**
 *
 */
public abstract class BaseAbstractView {

    private Layout layout;

    private MVC mvc;

    protected ControllerJPanel controllerPanel;

    protected Request request;

    protected BaseAbstractController controller;

    public BaseAbstractView(BaseAbstractController controller) {
        layout = controller.getKontabill().getLayout();
        controllerPanel = controller.getKontabill().getLayout().getControllerPanel();
        mvc = controller.getKontabill().getMVC();
        request = controller.getRequest();
        this.controller = controller;
    }

    public abstract void render();


    /**
     * Return layout instance layer of the application
     */
    public Layout getLayout() {
        return layout;
    }

    public MVC getMvc() {
        return mvc;
    }

    public Request getRequest() {
        return request;
    }

    public BaseAbstractController getControllerForView() {
        return controller;
    }

    public ControllerJPanel getControllerPanel() {
        return controllerPanel;
    }
}
