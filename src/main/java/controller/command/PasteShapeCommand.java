package controller.command;

import controller.interfaces.Command;
import controller.interfaces.Undoable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.shapeInformation.CanvasLists;
import model.shapeInformation.CoordinatePair;
import model.shapeInformation.ShapeProperties;

//Class: SE 350 Fall 2021
//Name: Hector Bonilla
//Topic: JPaint Assignment 3

/**
 * This is a class that functions as a command. It calls upon when needed from the CommandFactory class, where, if called, it creates a
 * new PasteShapeCommand object, which can be used to "paste" the shape(s) in listManager's clipBoardList. It "pastes"/recreates
 * the "copied" shape(s) by redrawing the same shape, with a difference reference address and with a coordinate offset.
 */
public class PasteShapeCommand implements Command, Undoable {

  private List<ShapeProperties> pastedShapeList = new ArrayList<>();
  private CanvasLists listManager;

  /**
   * This is a constructor method. This method that initializes list into the private CanvasLists listManager variable.
   */
  public PasteShapeCommand(CanvasLists list) {
    listManager = list;
  }

  /**
   * @See controller.interfaces.Command
   * This is a runner method. This method loops through listManager's clipBoardList, where it creates a new ShapeProperties object using each ShapeProperties object's coordinates with an offset
   * (the offset being 100 * the key's value), primaryColor, secondaryColor, shapeType, and shapeShadingType. It then adds the new ShapeProperties object to pastedShapeList and then adds it to listManager's shapeList.
   * In addition, its adds this PasteShapeCommand object to CommandHistory, only if any ShapeProperties object is in pastedShapeList.
   */
  @Override
  public void run() {
    for(Map.Entry<ShapeProperties, Integer> i : listManager.getClipBoardList().entrySet()) {
      ShapeProperties key = i.getKey();
      Integer value = i.getValue();

      CoordinatePair newStart = new CoordinatePair(key.startingPoints.getX() + (100 * value), key.startingPoints.getY() + (100 * value));
      CoordinatePair newEnd = new CoordinatePair(key.endingPoints.getX() + (100 * value), key.endingPoints.getY() + (100 * value));
      ShapeProperties shape = new ShapeProperties(newStart, newEnd, key.primaryColor, key.secondaryColor, key.shapeType, key.shapeShadingType);

      pastedShapeList.add(shape);
      listManager.addToShapeList(shape);
      listManager.getClipBoardList().put(key, value + 1);
    }

    if(!pastedShapeList.isEmpty()){
      CommandHistory.add(this);
    }
  }

  /**
   * @See controller.interfaces.Undoable
   * This is an event method. This method remove all the shape(s) in pastedShapeList from listManager's shapeList and decrease the value of every ShapeProperties object in clipBoardList by 1, in order balance out the add on run().
   */
  @Override
  public void undo() {
    for(Map.Entry<ShapeProperties, Integer> i : listManager.getClipBoardList().entrySet()) {
      ShapeProperties key = i.getKey();
      Integer value = i.getValue();
      listManager.getClipBoardList().put(key, value - 1);
    }

    for (ShapeProperties i: pastedShapeList) {
      listManager.removeFromShapeList(i);
    }
  }

  /**
   * @See controller.interfaces.Undoable
   * This is an event method. This method adds all the shape(s) in pastedShapeList back into listManager's shapeList.
   */
  @Override
  public void redo() {
    for (ShapeProperties i: pastedShapeList) {
      listManager.addToShapeList(i);
    }
  }
}
