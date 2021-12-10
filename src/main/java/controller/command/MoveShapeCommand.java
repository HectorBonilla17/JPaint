package controller.command;

import controller.interfaces.Command;
import controller.interfaces.Undoable;
import java.util.ArrayList;
import java.util.List;
import model.shapeInformation.CanvasLists;
import model.shapeInformation.CoordinatePair;
import model.shapeInformation.ShapeProperties;

//Class: SE 350 Fall 2021
//Name: Hector Bonilla
//Topic: JPaint Assignment 3

/**
 * This is a class that functions as a command. It calls upon when needed from the CommandFactory
 * class, where, if called, it creates a new MoveShapeCommand object, which can modify an existing shapes coordinates/position.
 * In addition, it repaints() the canvas based on whenever a shape was moved, moved was undone, or move was redone.
 */
public class MoveShapeCommand implements Command, Undoable {

  private List<ShapeProperties> selectedShapeList = new ArrayList<>();
  private CanvasLists shapeList;
  private int xDelta;
  private int yDelta;

  /**
   * This is a constructor method. This method initializes the X-Delta, Y-Delta, and canvasList into the private variables xDelta, yDelta, and shapeList.
   */
  public MoveShapeCommand(CoordinatePair starting, CoordinatePair ending, CanvasLists list){
    this.shapeList = list;
    this.xDelta = ending.getX() - starting.getX();
    this.yDelta = ending.getY() - starting.getY();
  }

  /**
   * @See controller.interfaces.Command
   * This is a runner method. This method loops through the given shapeList, where it checks if the shape's isSelected attribute
   * is true and if true it calls editCoordinates to modify that shape and adds that shape to the selectedShapeList.
   * In addition, its adds this MoveShapeCommand object to CommandHistory, only if any shape object were actually moved & added to the selectedShapeList.
   */
  @Override
  public void run() {
    for (ShapeProperties i: shapeList.getShapeList()) {
      if(i.isSelected) {
        editCoordinates(i);
        selectedShapeList.add(i);
      }
    }

    if(!selectedShapeList.isEmpty()){
      CommandHistory.add(this);
    }
  }

  /**
   * This is a getter method. This method returns selectedShapeList to whoever called it.
   */
  public List<ShapeProperties> getSelectedShapeList() { return selectedShapeList; }

  /**
   * This is a setter method. This method sets a shape's startingPoints, and endingPoints to
   * their initial values plus the xDelta & yDelta to their corresponding x and y values.
   * It also sets the shape's x & y values to its new startingPoint x & y.
   */
  public void editCoordinates(ShapeProperties shape) {
    shape.startingPoints.setX( shape.startingPoints.getX() + xDelta );
    shape.startingPoints.setY( shape.startingPoints.getY() + yDelta );
    shape.endingPoints.setX( shape.endingPoints.getX() + xDelta );
    shape.endingPoints.setY( shape.endingPoints.getY() + yDelta );

    shape.x = shape.startingPoints.getX();
    shape.y = shape.startingPoints.getY();
  }

  /**
   * @See controller.interfaces.Undoable
   * This is an event method. This method moves (by subtracting from the coordinates) the shapes in the selectedShapeList
   * based on the xDelta & yDelta. It then repaints the canvas with the shape(s)'s new coordinates.
   */
  @Override
  public void undo() {
    for (ShapeProperties i: selectedShapeList) {
        i.startingPoints.setX( i.startingPoints.getX() - xDelta );
        i.startingPoints.setY( i.startingPoints.getY() - yDelta );
        i.endingPoints.setX( i.endingPoints.getX() - xDelta );
        i.endingPoints.setY( i.endingPoints.getY() - yDelta );

        i.x = i.startingPoints.getX();
        i.y = i.startingPoints.getY();
    }
  }

  /**
   * @See controller.interfaces.Undoable
   * This is an event method. This method undoes the moves of shapes in the selectedShapeList
   * by adding back the xDelta & yDelta to their coordinates, the opposite of is needed for the initial move(s).
   * It then repaints the canvas with the shape(s)'s new coordinates.
   */
  @Override
  public void redo() {
    for (ShapeProperties i: selectedShapeList) {
        i.startingPoints.setX( i.startingPoints.getX() + xDelta );
        i.startingPoints.setY( i.startingPoints.getY() + yDelta );
        i.endingPoints.setX( i.endingPoints.getX() + xDelta );
        i.endingPoints.setY( i.endingPoints.getY() + yDelta );

        i.x = i.startingPoints.getX();
        i.y = i.startingPoints.getY();
    }
  }
}
