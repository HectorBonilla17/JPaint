package controller.command;

import controller.interfaces.Command;
import controller.interfaces.Undoable;
import model.persistence.UserChoicesImpl;
import model.shapeInformation.CanvasLists;
import model.shapeInformation.CoordinatePair;
import model.shapeInformation.ShapeProperties;

//Class: SE 350 Fall 2021
//Name: Hector Bonilla
//Topic: JPaint Assignment 3

/**
 * This is a class that functions as a command. It calls upon when needed from the CommandFactory
 * class, where, if called, it creates a new CreateShapeCommand object, which can be used to create a shape object in the
 * program. In addition, it repaints() the canvas based on whenever a new shape was added, undid, or redid.
 */
public class CreateShapeCommand implements Command, Undoable {

  ShapeProperties shape;
  private CoordinatePair startingPoints;
  private CoordinatePair endingPoints;
  private UserChoicesImpl appState;
  private CanvasLists canvasList;

  /**
   * This is a constructor method. This method initializes the startingPair, endingPair,
   * canvas, appData, and canvasList into their respective private variables for future method calls. In addition,
   * its adds this CreateShapeCommand object to CommandHistory.
   */
  public CreateShapeCommand(CoordinatePair startingPair, CoordinatePair endingPair, UserChoicesImpl choices, CanvasLists list){
    this.startingPoints = startingPair;
    this.endingPoints = endingPair;
    this.appState = choices;
    this.canvasList = list;
    CommandHistory.add(this);
  }

  /**
   * This is a getter method. This method returns shape to whoever called it.
   */
  public ShapeProperties getShape() { return shape; }

  /**
   * @See controller.interfaces.Command
   * This is a runner method. This method creates a new shape, using the ShapeProperties class, based on the
   * private variables initialized by the constructor. It then adds the new shape to the shape list.
   */
  @Override
  public void run() {
    shape = new ShapeProperties(startingPoints, endingPoints, appState.getActivePrimaryColor(), appState.getActiveSecondaryColor(), appState.getActiveShapeType(), appState.getActiveShapeShadingType());
    canvasList.addToShapeList(shape);
  }

  /**
   * @See controller.interfaces.Undoable
   * This is an event method. This method removes the given shape, if not selected
   * then it refers to the shape most recently added, from the shape list.
   */
  @Override
  public void undo() {
    canvasList.removeFromShapeList(shape);
  }

  /**
   * @See controller.interfaces.Undoable
   * This is an event method. This method adds back an undid shape to the shape list.
   */
  @Override
  public void redo() {
    canvasList.addToShapeList(shape);
  }
}
