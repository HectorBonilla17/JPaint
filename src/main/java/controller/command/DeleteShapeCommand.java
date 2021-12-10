package controller.command;

import controller.interfaces.Command;
import controller.interfaces.Undoable;
import java.util.ArrayList;
import java.util.List;
import model.shapeInformation.CanvasLists;
import model.shapeInformation.ShapeProperties;

//Class: SE 350 Fall 2021
//Name: Hector Bonilla
//Topic: JPaint Assignment 4

public class DeleteShapeCommand implements Command, Undoable {

  private List<ShapeProperties> deletedShapeList = new ArrayList<>();
  private CanvasLists shapeList;

  /**
   * This is a constructor method. This method initializes list into the private variable shapeList.
   */
  public DeleteShapeCommand(CanvasLists list) {
    this.shapeList = list;
  }

  /**
   * @See controller.interfaces.Command
   * This is a runner method. This method loops through the given shapeList, where it checks if the shape's isSelected attribute
   * is true, if it is then the ShapeProperties object is added to deletedShapeList. Then for each ShapeProperties in deletedShapeList, it removes that
   * shape from shapeList. In addition, it adds this DeleteShapeCommand object to CommandHistory, only if any ShapeProperties object were deleted/added to deletedShapeList.
   */
  @Override
  public void run() {
    for (ShapeProperties i: shapeList.getShapeList()) {
      if(i.isSelected) {
        deletedShapeList.add(i);
      }
    }

    for(ShapeProperties i: deletedShapeList) {
      shapeList.removeFromShapeList(i);
    }

    if(!deletedShapeList.isEmpty()){
      CommandHistory.add(this);
    }
  }

  /**
   * @See controller.interfaces.Undoable
   * This is an event method. This method adds backs the deleted shape(s) to the shape list, based on deletedShapeList.
   */
  @Override
  public void undo() {
    for (ShapeProperties i: deletedShapeList) {
      shapeList.addToShapeList(i);
    }
  }

  /**
   * @See controller.interfaces.Undoable
   * This is an event method. This method removes shape(s) from the shape list, based on deletedShapeList.
   */
  @Override
  public void redo() {
    for (ShapeProperties i: deletedShapeList) {
      shapeList.removeFromShapeList(i);
    }
  }
}
