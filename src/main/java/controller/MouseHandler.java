package controller;

import controller.command.CommandController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.shapeInformation.CoordinatePair;

/**
 * MouseHandler is responsible for propagating mouse coordinates into our application
 * classes. This is a boundary class so very little code should be added here.
 */
public class MouseHandler extends MouseAdapter {

  private CommandController controller;
  private CoordinatePair startingPair;

  /**
   * This is a constructor method. This method initializes the passed CommandController into the private field controller.
   */
  public MouseHandler(CommandController commandController) {
    this.controller = commandController;
  }


  /**
   * This is an event method. This method is used when a mouse button is pressed
   * and stores the coordinates of where the mouse was into the private field startingPair.
   */
  @Override
  public void mousePressed(MouseEvent e) {
    startingPair = new CoordinatePair(e.getX(), e.getY());
  }

  /**
   * This is an event method. This method is used when a mouse button is released
   * and stores the coordinates of where the mouse was into the private field endingPair. It then calls
   * upon the CommandController's getAndDoCommand with the startingPair & endingPair, which uses a Command Factory
   * to get the necessary command based on the active mouse mode.
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    CoordinatePair endingPair = new CoordinatePair(e.getX(), e.getY());
    controller.getAndDoCommand(startingPair, endingPair);
  }
}
