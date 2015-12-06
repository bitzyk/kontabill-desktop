package main.java.kontabill;

import main.java.kontabill.db.Database;
import main.java.kontabill.layout.Layout;
import main.java.kontabill.mvc.MVC;
import main.java.kontabill.mvc.controller.BaseAbstractController;
import main.java.kontabill.mvc.controller.IndexController;

/**
 * App class instance
 *  - singleton pattern
 */
public class Kontabill {

    private static Kontabill instance;

    private Layout layout;

    private MVC mvc;

    private Database db;

    private Kontabill()
    {
        initLayout();
        initMVC();
        initDatabase();
    }

    public static Kontabill getInstance()
    {
        if (! (instance instanceof Kontabill) ) {
            instance = new Kontabill();
        }

        return instance;
    }

    /**
     * Layout initialization
     */
    private void initLayout()
    {
        layout = new Layout(this);
    }

    /**
     * MVC initialization
     */
    private void initMVC()
    {
        mvc = new MVC(this);
    }

    /**
     * Database initialization
     */
    private void initDatabase()
    {
        db = Database.getInstance();
        Database.getInstance();
    }

    /**
     * Running the app
     */
    public void run()
    {
        System.out.println(
                "-- kontabill run --"
        );

        // run the layout
        layout.run();

        // get initial controller @todo - should be based on configuration
        BaseAbstractController initialController = new IndexController(this);

        // set initial controller to mvc
        mvc.setController(initialController);

        // run the MVC
        mvc.run();
    }

    /**
     * Return mvc instance layer of the apllication
     */
    public MVC getMVC()
    {
        return mvc;
    }

    /**
     * Return layout instance layer of the application
     */
    public Layout getLayout()
    {
        return layout;
    }


    /**
     * Return database reference
     *
     * @return
     */
    public Database getDb() {
        return db;
    }
}
