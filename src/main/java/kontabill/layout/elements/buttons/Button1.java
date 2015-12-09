package main.java.kontabill.layout.elements.buttons;

import main.java.kontabill.layout.elements.UIElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 */
public class Button1 extends JButton implements UIElement {

    private Color backgroundColor;

    private Color hoverBackgroundColor;

    private Color foregroundColor;

    private Color borderColorDefault;

    private Color borderColorHover;

    private int[] paddingPx = new int[4];

    private int[] borderSizePx = new int[4];

    private Font buttonFont = null;

    private Boolean isActive = false;

    public Button1(String text)
    {
        super(text);
    }

    @Override
    public void init()
    {
        // if is active set the background color to be equal to hover color (the active color)
        if(isActive) {
           backgroundColor = hoverBackgroundColor;
        }

        setBackground(backgroundColor);

        setDefaultBorder();
        setForeground(foregroundColor);
        if(buttonFont == null) {
            setButtonFontDefault();
        }
        setFont(buttonFont);

        // set margin right for the button

        // add hover background mouseListener
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBackgroundColor);
                setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createMatteBorder(borderSizePx[0], borderSizePx[1], borderSizePx[2], borderSizePx[3], borderColorHover),
                                BorderFactory.createMatteBorder(paddingPx[0], paddingPx[1], paddingPx[2], paddingPx[3], hoverBackgroundColor)
                        )
                );
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(backgroundColor);
                setDefaultBorder();
            }
        });

        // normalize mousee pressed -> solution: https://community.oracle.com/thread/1142547
        setContentAreaFilled(false);
        setOpaque(true);

    }

    private void setDefaultBorder()
    {
        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(borderSizePx[0], borderSizePx[1], borderSizePx[2], borderSizePx[3], borderColorDefault),
                        BorderFactory.createMatteBorder(paddingPx[0], paddingPx[1], paddingPx[2], paddingPx[3], backgroundColor)
                )
        );
    }

    public void setBackgroundColorDefault(Color backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public void setHoverBackgroundColorDefault(Color hoverBackgroundColor)
    {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public void setForegroundColor(Color foregroundColor)
    {
        this.foregroundColor = foregroundColor;
    }

    public void setBorderColorDefault(Color borderColorDefault)
    {
        this.borderColorDefault = borderColorDefault;
    }

    public void setBorderColorHover(Color borderColorHover)
    {
        this.borderColorHover = borderColorHover;
    }

    public void setPaddingPx(int top, int left, int bottom, int right)
    {
        this.paddingPx[0] = top;
        this.paddingPx[1] = left;
        this.paddingPx[2] = bottom;
        this.paddingPx[3] = right;
    }

    public void setBorderSizePx(int top, int left, int bottom, int right)
    {
        this.borderSizePx[0] = top;
        this.borderSizePx[1] = left;
        this.borderSizePx[2] = bottom;
        this.borderSizePx[3] = right;
    }

    public void setButtonFont(String fontFamily, int fontStyle, int fontSize)
    {
        buttonFont = new Font(fontFamily, fontStyle, fontSize);
    }

    public void setButtonFontDefault()
    {
        // @todo - font default read from config, font size always computed relative to default from config
        setButtonFont("Dialog", Font.BOLD, 12);
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}