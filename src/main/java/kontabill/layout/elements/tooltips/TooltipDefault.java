package main.java.kontabill.layout.elements.tooltips;

import main.java.kontabill.Kontabill;
import main.java.kontabill.layout.containers.Observable;
import main.java.kontabill.layout.containers.ObservableEvent;
import main.java.kontabill.layout.elements.Observer;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.BalloonTipStyle;
import net.java.balloontip.styles.EdgedBalloonStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

/**
 *
 */
public class TooltipDefault extends BalloonTip implements Observer {

    public TooltipDefault(JComponent attachedComponent, String label)
    {
        super(
                attachedComponent,
                new JLabel(label),
                new EdgedBalloonStyle(
                        Color.decode("#debfbf"),
                        Color.decode("#be1e1e")
                ),
                BalloonTip.Orientation.LEFT_ABOVE,
                BalloonTip.AttachLocation.NORTHEAST,
                10,
                10,
                true
        );

        getObservable().registerObserver(
                ObservableEvent.ALL_COMPONENTS_REMOVED,
                this
        );
    }

    @Override
    public void closeBalloon() {
        getObservable().removeObserver(ObservableEvent.ALL_COMPONENTS_REMOVED, this);
        super.closeBalloon();

    }

    @Override
    public void update(ObservableEvent observableEvent) {
        super.closeBalloon();
    }

    private Observable getObservable()
    {
        return Kontabill.getInstance().getLayout().getControllerPanel();
    }
}
