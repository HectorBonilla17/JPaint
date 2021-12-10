package controller.command;

import controller.interfaces.Command;
import model.persistence.UserChoicesImpl;
import model.shapeInformation.CanvasLists;
import model.shapeInformation.CoordinatePair;
import view.gui.PaintCanvas;

//Class: SE 350 Fall 2021
//Name: Hector Bonilla
//Topic: JPaint Assignment 4

/**
 * This is a class that functions as a command controller. It calls upon the needed command based on
 * whether the user is in draw, select, or move mode.
 */
public class CommandController {

  private final PaintCanvas canvas;
  private final UserChoicesImpl choices;
  private final CanvasLists canvasList;

  /**
   * This is a constructor method. This method initializes the paintCanvas, appState, and list into the
   * private variables canvas, choices, and canvasList.
   */
  public CommandController(PaintCanvas paintCanvas, UserChoicesImpl appState, CanvasLists list){
    this.canvas = paintCanvas;
    this.choices = appState;
    this.canvasList = list;
  }

  /**
   * This is a CommandFactory getter & runner method. This method gets a Command object from ShapeFactory based on the current
   * MouseMode & by passing the needed variables based on the MouseMode. It then calls the Command object's run() method to either create a shape,
   * select shape(s), or move shape(s). Afterwards canvas.repaint() is called to show the affects of the Command objects.
   */
  public void getAndDoCommand(CoordinatePair start, CoordinatePair end) {
    Command cmd = null;
    switch (choices.getActiveMouseMode()) {
      case DRAW:
        cmd = CommandFactory.makeDrawer(start, end, choices, canvasList);
        break;
      case SELECT:
        cmd = CommandFactory.makeSelector(start, end, canvasList, canvas);
        break;
      case MOVE:
        cmd = CommandFactory.makeMover(start, end, canvasList);
        break;
    }

    cmd.run();
    canvas.repaint();
  }

  /**
   * This is an event method. This method gets a Command object from CommandFactory by calling CommandFactory.makeCopier(canvasList). It then runs said Command object (CopyShapeCommand) from the canvas,
   * which puts copies the selected shapes and puts them into the clipBoardList, and the canvas is then repainted.
   */
  public void onCopy() {
    Command cmd = CommandFactory.makeCopier(canvasList);
    cmd.run();
    canvas.repaint();
  }

  /**
   * This is an event method. This method gets a Command object from CommandFactory by calling CommandFactory.makePaster(canvasList). It then runs said Command object (PasteShapeCommand) from the canvas,
   * which get the shapes copies from clipBoardList then puts them into shapeList and repaints the canvas for the pasted shapes to show up.
   */
  public void onPaste() {
    Command cmd = CommandFactory.makePaster(canvasList);
    cmd.run();
    canvas.repaint();
  }

  /**
   * This is an event method. This method that calls upon CommandHistory.undo() to push the most recent Command object from the undo stack to
   * the redo stack. Which undoes/removes said Command object from the canvas and canvas is repaint to how it was prior to that command.
   */
  public void onUndo() {
    CommandHistory.undo();
    canvas.repaint();
  }

  /**
   * This is an event method. This method gets a Command object from CommandFactory by calling CommandFactory.makeDeleter(canvasList). It then runs said Command object (DeleteShapeCommand) from the canvas,
   * which removes/deletes the selected shape(s) from canvasList's shapeList, where the "shapes" are stored, and then repaints the canvas so the deleted shapes to disappear from the canvas.
   */
  public void onDelete() {
    Command cmd = CommandFactory.makeDeleter(canvasList);
    cmd.run();
    canvas.repaint();
  }

  /**
   * This is an event method. This method that calls upon CommandHistory.redo() to push the most recent Command object from the redo stack to
   * the undo stack. Which redoes/adds said Command object back to the canvas and canvas is repaint to how it when the command was initially ran.
   */
  public void onRedo() {
    CommandHistory.redo();
    canvas.repaint();
  }
}

