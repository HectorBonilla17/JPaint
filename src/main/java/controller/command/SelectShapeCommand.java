package controller.command;

import controller.interfaces.Command;
import model.shapeInformation.CanvasLists;
import model.shapeInformation.CoordinatePair;
import model.shapeInformation.ShapeProperties;
import view.gui.PaintCanvas;

//Class: SE 350 Fall 2021
//Name: Hector Bonilla
//Topic: JPaint Assignment 3

/**
 * This is a class that functions as a command. It calls upon when needed from the CommandFactory
 * class, where, if called, it creates a new SelectShapeCommand object, which can "select" shapes based on the given selection box coordinates.
 */

public class SelectShapeCommand implements Command {

  CoordinatePair boxStart;
  CoordinatePair boxEnd;
  CanvasLists listReference;
  PaintCanvas canvas;

  /**
   * This is a constructor method. This method that normalizes the starting coordinates & ending coordinates
   * of the select box and initializes sList into listReference.
   */
  public SelectShapeCommand(CoordinatePair starting, CoordinatePair ending, CanvasLists sList, PaintCanvas canvas) {
    normalizeCoordinates(starting, ending);
    this.listReference = sList;
    this.canvas = canvas;
  }

  /**
   * This is a method which sets which shapes are being selected. This method checks for overlap using checkSelectionOverlap and then
   * sets the shape's isSelected attribute accordingly. It then repaints the canvas in order to show which shape will have the selection outline.
   */
  public void selectShapeCommand(CanvasLists shapeList) {
    for (ShapeProperties i: shapeList.getShapeList()){
      i.isSelected = checkSelectionOverlap(i);
    }
    canvas.repaint();
  }

  /**
   * This is a method that checks for shape overlap. This method checks for overlap by checking two things:
   * 1. Whether the top edge of either the select box or shape are above the other
   * 2. Whether the left edge of either the select box or shape are to the left of the other
   * If it passes both statements, the select box and shape overlap.
   */
  public boolean checkSelectionOverlap(ShapeProperties k){
    if(boxEnd.getY() < k.startingPoints.getY() || boxStart.getY() > k.endingPoints.getY()){
      return false;
    }

    if(boxEnd.getX() < k.startingPoints.getX() || boxStart.getX() > k.endingPoints.getX()){
      return false;
    }

    return true;
  }

  /**
   * This is a coordinate normalizer/setter method. This method gets called in the SelectShapeCommand's constructor with the two CoordinatePair objects passed to it.
   * It then creates two new CoordinatePair objects based on whether start.getX() > end.getX() & start.getY() > end.getY(), which result in boxStart having
   * both the smallest X & Y coordinates while boxEnd having the biggest X & Y coordinates. This results in a selection box being drawn from the bottom left to the top right coordinates.
   */
  public void normalizeCoordinates(CoordinatePair start, CoordinatePair end){
    int newStartX;
    int newStartY;
    int newEndX;
    int newEndY;

    if (start.getX() > end.getX()) {
      newStartX = end.getX();
      newEndX = start.getX();
    } else {
      newStartX = start.getX();
      newEndX = end.getX();
    }

    if (start.getY() > end.getY()) {
      newStartY = end.getY();
      newEndY = start.getY();
    } else {
      newStartY = start.getY();
      newEndY = end.getY();
    }

    boxStart = new CoordinatePair(newStartX, newStartY);
    boxEnd = new CoordinatePair(newEndX, newEndY);
  }

  /**
   * @See controller.interfaces.Command
   * This is a runner method. This method calls upon selectShapeCommand to "select" (i.e, change their isSelected attribute to true)
   * the shapes with overlap with the selection box.
   */
  @Override
  public void run() {
    selectShapeCommand(listReference);
  }
}
