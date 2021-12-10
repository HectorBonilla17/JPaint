package controller.command;

import controller.interfaces.Command;
import model.shapeInformation.CanvasLists;
import model.shapeInformation.ShapeProperties;

//Class: SE 350 Fall 2021
//Name: Hector Bonilla
//Topic: JPaint Assignment 3

/**
 * This is a class that functions as a command. It calls upon when needed from the CommandFactory
 * class, where, if called, it creates a new CopyShapeCommand object, which can be used to "copy" a shape object in the program.
 */
public class CopyShapeCommand implements Command {

  private CanvasLists listManager;

  /**
   * This is a constructor method. This method that initializes list into the private CanvasLists listManager variable.
   */
  public CopyShapeCommand (CanvasLists list) {
    this.listManager = list;
  }

  /**
   * @See controller.interfaces.Command
   * This is a runner method. This method "copies" the select shape(s) into to listManager's clipBoardList, if there are any shape(s) selected, by adding them in the HashMap with a "copied" ShapeProperties key(i,e. a new ShapeProperties object with the
   * same startingPoints, endingPoints, primaryColor, secondaryColor, shapeType, and shapeShadingType) and value of 1, for future uses in pasting the "copied" shapes.
   */
  @Override
  public void run() {
    boolean somethingIsSelected = false;
    for(ShapeProperties x: listManager.getShapeList()) {
      if (x.isSelected) {
        somethingIsSelected = true;
        break;
      }
    }

    if(somethingIsSelected){
      listManager.getClipBoardList().clear();
      for(ShapeProperties i: listManager.getShapeList()) {
        if (i.isSelected) {
          ShapeProperties shapeCopyReference = new ShapeProperties(i.startingPoints, i.endingPoints, i.primaryColor, i.secondaryColor, i.shapeType, i.shapeShadingType);
          listManager.addToClipBoardList(shapeCopyReference);
        }
      }
    }
  }
}

