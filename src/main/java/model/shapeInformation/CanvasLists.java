package model.shapeInformation;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Class: SE 350 Fall 2021
//Name: Hector Bonilla
//Topic: JPaint Assignment 3

/**
 * This is a class that functions as a list manager, it stores the Shape objects that have been drawn.
 */
public class CanvasLists {
  private List<ShapeProperties> shapeList = new ArrayList<>();
  private HashMap<ShapeProperties, Integer> clipBoardList = new HashMap<>();

  /**
   * This is an adder method. This method adds the passed ShapeProperties object to shapeList.
   */
  public void addToShapeList(ShapeProperties shape) {
    shapeList.add(shape);
  }

  /**
   * This is a remover method. This method removes the passed ShapeProperties object from shapeList.
   */
  public void removeFromShapeList(ShapeProperties shape) {
    shapeList.remove(shape);
  }

  /**
   * This is a getter method. This method returns shapeList to whoever called it.
   */
  public List<ShapeProperties> getShapeList() { return shapeList; }

  //---------------------------------------------------------------------------------

  /**
   * This is an adder method. This method adds the passed ShapeProperties object to clipBoardList with a value of 1.
   */
  public void addToClipBoardList(ShapeProperties shape) { clipBoardList.put(shape, 1); }

  /**
   * This is a getter method. This method returns clipBoardList to whoever called it.
   */
  public HashMap<ShapeProperties, Integer> getClipBoardList() { return clipBoardList; }

  //---------------------------------------------------------------------------------

  /**
   * This is a runner method. This method loops through shapeList and calls the draw() method for
   * each ShapeProperties object.
   */
  public void draw(Graphics2D graphics) {
    for (ShapeProperties i: shapeList){
      i.draw(graphics);
    }
  }
}

