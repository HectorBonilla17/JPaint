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
 * This is a class that functions as a Command Factory. It is called upon by the CommandController when it needs to create a Command object. Returning
 * either a CreateShapeCommand, SelectShapeCommand, or MoveShapeCommand, which decided in CommandController based on the MouseMode. In addition, a CopyShapeCommand, PasteShapeCommand,
 * or DeleteShapeCommand Command object can be returned based on GUI button clicked.
 */
public class CommandFactory {

  /**
   * @See controller.interfaces.Command
   * This is a Command object creator method. This method creates a CreateShapeCommand object with its passed arguments and returns
   * the object back to CommandController.
   */
  public static Command makeDrawer(CoordinatePair start, CoordinatePair end, UserChoicesImpl choices, CanvasLists canvasList) {
    return new CreateShapeCommand(start, end, choices, canvasList);
  }

  /**
   * @See controller.interfaces.Command
   * This is a Command object creator method. This method creates a SelectShapeCommand object with its passed arguments and returns
   * the object back to CommandController.
   */
  public static Command makeSelector(CoordinatePair start, CoordinatePair end, CanvasLists canvasList, PaintCanvas canvas) {
    return new SelectShapeCommand(start, end, canvasList, canvas);
  }

  /**
   * @See controller.interfaces.Command
   * This is a Command object creator method. This method creates a MoveShapeCommand object with its passed arguments and returns
   * the object back to CommandController.
   */
  public static Command makeMover(CoordinatePair start, CoordinatePair end, CanvasLists canvasList) {
    return new MoveShapeCommand(start, end, canvasList);
  }

  /**
   * @See controller.interfaces.Command
   * This is a Command object creator method. This method creates a CopyShapeCommand object with its passed arguments and returns
   * the object back to CommandController.
   */
  public static Command makeCopier(CanvasLists canvasList){
    return new CopyShapeCommand(canvasList);
  }

  /**
   * @See controller.interfaces.Command
   * This is a Command object creator method. This method creates a PasteShapeCommand object with its passed arguments and returns
   * the object back to CommandController.
   */
  public static Command makePaster(CanvasLists canvasList){
    return new PasteShapeCommand(canvasList);
  }

  /**
   * @See controller.interfaces.Command
   * This is a Command object creator method. This method creates a DeleteShapeCommand object with its passed arguments and returns
   * the object back to CommandController.
   */
  public static Command makeDeleter(CanvasLists canvasList) {
    return new DeleteShapeCommand(canvasList);
  }
}
