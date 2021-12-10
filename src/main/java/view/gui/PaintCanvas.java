package view.gui;

import javax.swing.JComponent;
import java.awt.*;
import model.shapeInformation.CanvasLists;

/**
 * PaintCanvas is responsible for responding to the graphics system when it
 * is time to update the display.  This is a boundary class so very little code
 * should be added here.
 */
public class PaintCanvas extends JComponent {

    CanvasLists canvasList;

    /**
     * This is a setter method. This method get the CanvasLists object initialized in main and stores
     * it into the private variable canvasList.
     */
    public PaintCanvas (CanvasLists list) { this.canvasList = list; }


    @Override
    /**
     * This is an event handler.  If this function gets called, its time to
     * draw the entire picture.
     * It you want to force a paint event, call aPaintCanvas.repaint()
     */
    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2d = (Graphics2D) graphics;
        canvasList.draw(graphics2d);
    }
}
