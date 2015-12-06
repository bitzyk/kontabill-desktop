package main.java.kontabill.layout.elements.factories;

import main.java.kontabill.layout.elements.buttons.Button1;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class ButtonFactory {


    public static JButton createButtonGrayControlPanel(String text, Boolean isActive)
    {
        Button1 button = new Button1(text);
        button.setBackgroundColorDefault(Color.decode("#a5a5a5"));
        button.setHoverBackgroundColorDefault(Color.decode("#3f3f3f"));
        button.setForegroundColor(Color.WHITE);
        button.setBorderColorDefault(Color.decode("#444444"));
        button.setBorderColorHover(Color.BLACK);
        button.setPaddingPx(8, 8, 8, 8);
        button.setBorderSizePx(1, 1, 1, 1);
        button.setIsActive(isActive);

        button.init();
        return button;
    }

    public static JButton createButtonWhiteControlPanel(String text, Boolean isActive)
    {
        Button1 button = new Button1(text);
        button.setBackgroundColorDefault(Color.WHITE);
        button.setHoverBackgroundColorDefault(Color.decode("#eeeeee"));
        button.setForegroundColor(Color.BLACK);
        button.setBorderColorDefault(Color.decode("#d2d2d2"));
        button.setBorderColorHover(Color.decode("#d2d2d2"));
        button.setPaddingPx(6, 6, 6, 6);
        button.setBorderSizePx(1, 1, 1, 1);
        button.setIsActive(isActive);

        button.init();
        return button;
    }

    public static JButton createButtonGreenSubmitControlPanel(String text)
    {
        Button1 button = new Button1(text);
        button.setBackgroundColorDefault(Color.decode("#99bd2c"));
        button.setHoverBackgroundColorDefault(Color.decode("#99bd2c"));
        button.setForegroundColor(Color.WHITE);
        button.setBorderColorDefault(Color.decode("#658406"));
        button.setBorderColorHover(Color.decode("#658406"));
        button.setPaddingPx(8, 60, 5, 60);
        button.setBorderSizePx(1, 1, 4, 1);
        // @todo - font default read from config, font size always computed relative to default from config
        button.setButtonFont("Dialog", Font.BOLD, 16);

        button.init();
        return button;
    }


    public static JButton createButtonEditDefault(String text)
    {
        Button1 button = new Button1(text);
        button.setBackgroundColorDefault(Color.decode("#99bd2c"));
        button.setHoverBackgroundColorDefault(Color.decode("#658406"));
        button.setForegroundColor(Color.WHITE);
        button.setBorderColorDefault(Color.decode("#658406"));
        button.setBorderColorHover(Color.decode("#658406"));
        button.setPaddingPx(6, 6, 5, 6);
        button.setBorderSizePx(1, 1, 2, 1);

        button.init();
        return button;
    }

    public static JButton createButtonDeleteDefault(String text)
    {
        Button1 button = new Button1(text);
        button.setBackgroundColorDefault(Color.decode("#df5757"));
        button.setHoverBackgroundColorDefault(Color.decode("#be1e1e"));
        button.setForegroundColor(Color.WHITE);
        button.setBorderColorDefault(Color.decode("#be1e1e"));
        button.setBorderColorHover(Color.decode("#be1e1e"));
        button.setPaddingPx(6, 6, 5, 6);
        button.setBorderSizePx(1, 1, 2, 1);

        button.init();
        return button;
    }


    //

}
