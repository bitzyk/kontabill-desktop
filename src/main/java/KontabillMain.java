package main.java;

import main.java.kontabill.Kontabill;

/**
 *   Class to instantiate application and parse args
 */
public class KontabillMain {

    public static void main(String[] args)
    {
        Kontabill kontabillApp = Kontabill.getInstance();

        kontabillApp.run();
    }

}
