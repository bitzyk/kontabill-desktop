package main.java.kontabill.mvc;

import main.java.kontabill.Kontabill;
import main.java.kontabill.lib.core.request.Request;
import main.java.kontabill.mvc.controller.BaseAbstractController;

import java.lang.reflect.Constructor;


/**
 *
 */
public class MVC {

    /**
     * Represent kontabill instance app.
     * Can be used to build layout components according with kontabill instance object state.
     */
    private Kontabill kontabill;

    /**
     * Represents the current controller of MVC instance
     */
    private BaseAbstractController controller;

    private String actionControllerCurrent;

    public MVC(Kontabill kontabill)
    {
        this.kontabill = kontabill;
    }

    /**
     * Set current controller
     */
    public void setController(BaseAbstractController controller)
    {
        // instantiate current controller
        this.controller = controller;
    }

    /**
     * Set the current controller based on controllerName string
     *  - instantiate dinamically controller
     *
     * @param controllerName
     */
    public void setController(String controllerName) throws RuntimeException {

        try {
            Class<?> controllerClass = Class.forName("main.java.kontabill.mvc.controller." + controllerName);
            Constructor<?> constructor = controllerClass.getConstructor(Kontabill.class);

            setController((BaseAbstractController) constructor.newInstance(this.kontabill));
        } catch (Exception e) {
            throw new RuntimeException("The controller `" + controllerName + "` was not implemented. Execution halted.");
        }
    }

    /**
     * Return current controller of the MVCs
     *
     * @return
     */
    public BaseAbstractController getController()
    {
        return controller;
    }

    /**
     * Run the MVC
     * Trigger in chain when Kontabill.run()
     */
    public void run()
    {
        System.out.println(
                "-- Run MVC --"
        );

        runController("indexAction", new Request());
    }


    /**
     * Is called and when JMenuItem is selected
     * @param actionName
     * @throws RuntimeException
     */
    public void runController(String actionName, Request request) throws RuntimeException
    {
        try {
            // remmove previous components from controller panel when repaint is true
            if(request.getRepaintComponents() == true) {
                kontabill.getLayout().getControllerPanel().removeComponents();
            }

            // invoke controller action method -> this will trigger the view and adding components
            actionControllerCurrent = actionName;
            controller.setRequest(request);
            controller.getClass().getMethod(actionName).invoke(controller);

            // repaint and revalid controller panel after view has added the components if repaiang is true
            if(request.getRepaintComponents() == true) {
                kontabill.getLayout().getControllerPanel().repaintComponents();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(
                    e.getMessage()
            );

            throw new RuntimeException("The method `" + actionName + "` for controller `" + controller.getClass().getSimpleName() +
                    "` was not implemented. Execution halted.");
        }
    }


    public String getActionControllerCurrent()
    {
        return actionControllerCurrent;
    }

}
