package main.java.kontabill.layout.containers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 */
public class FrameMain extends JFrame {

    protected int widthF;

    protected int heightF;

    public FrameMain(String title) throws HeadlessException
    {
        super(title);
        initFrame();
    }

    private void initFrame()
    {
        // set full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // set initial size of the frame
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        widthF = winSize.width;
        heightF = winSize.height;

        // set component listener and window listener
        addComponentListener(new ComponentListenerFrame());
        addWindowListener(new WindowListenerFrame());
    }

    /**
     * Get width of the main frame
     * @return
     */
    public int getWidthF()
    {
        return widthF;
    }

    /**
     * Get height of the main frame
     * @return
     */
    public int getHeightF()
    {
        return heightF;
    }

    class ComponentListenerFrame extends ComponentAdapter {

        /**
         * On resize of the main frame set the new width and height of the FrameMain
         * @param e
         */
        public void componentResized(ComponentEvent e)
        {
            widthF = getSize().width;
            heightF = getSize().height;
        }

    }

    class WindowListenerFrame extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e)
        {
            System.exit(0);
        }
    }
}
