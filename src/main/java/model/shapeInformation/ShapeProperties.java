package model.shapeInformation;

import java.awt.Graphics2D;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.ShapeStrategy;

//Class: SE 350 Fall 2021
//Name: Hector Bonilla
//Topic: JPaint Assignment 3

/**
 * This is a class that functions as a shape object. Its purpose is to contain all the
 * user-inputted options for a shape.
 */
public class ShapeProperties {

  public boolean isSelected;
  public int x;
  public int y;
  public int length;
  public int height;
  public CoordinatePair startingPoints;
  public CoordinatePair endingPoints;
  public ShapeColor primaryColor;
  public ShapeColor secondaryColor;
  public ShapeType shapeType;
  public ShapeShadingType shapeShadingType;

  /**
   * This is a constructor method. This method initializes all the respective private variables
   * needed to create a shape object, besides the graphics object.
   */
  public ShapeProperties(CoordinatePair startingPoint, CoordinatePair endingPoint, ShapeColor activePrimaryColor, ShapeColor activeSecondaryColor, ShapeType activeShapeType, ShapeShadingType activeShapeShadingType){
    this.isSelected = false;
    normalizeCoordinates(startingPoint, endingPoint);
    this.x = startingPoints.getX();
    this.y = startingPoints.getY();
    this.length = Math.abs(startingPoints.getX() - endingPoints.getX());
    this.height = Math.abs(startingPoints.getY() - endingPoints.getY());
    this.primaryColor = activePrimaryColor;
    this.secondaryColor = activeSecondaryColor;
    this.shapeType = activeShapeType;
    this.shapeShadingType = activeShapeShadingType;
  }

  /**
   * This is a drawer getter method.
   * 1. This method gets the needed ShapeStrategy object needed to draw the shape based on shapeType, whether that be
   * RectangleStrategy, EllipseStrategy, or TriangleStrategy.
   * 2. It then takes the previous shapeStrategy object and based on the shapeShadingType case, which tells the decorator class which shape type needs to be drawn
   * and how it needs to be drawn in terms of its dimensions, reassigns the shapeStrategy variable to the decorator class, so that it can draw a shape in either Outline,
   * Filled-In, or Outline and Fill-in mode based on the case.
   * 3. If the ShapeProprieties's isSelect variable is true, it then creates another decorator around the "outline/fill-in" decorator in order to create a dotted outline based on the shape
   * around the shape.
   * It then calls upon the ShapeStrategy's drawStrategy method, passing the Graphics2D object
   * in its argument and this ShapeProperties object.
   */
  public void draw(Graphics2D graphic) {
    ShapeStrategy shapeStrategy = null;

    switch (shapeType) {
      case RECTANGLE:
        shapeStrategy = new RectangleStrategy();
        break;
      case ELLIPSE:
        shapeStrategy = new EllipseStrategy();
        break;
      case TRIANGLE:
        shapeStrategy = new TriangleStrategy();
        break;
    }

    switch (shapeShadingType) {
      case OUTLINE:
        shapeStrategy = new OutlineStrategy(shapeStrategy);
        break;
      case FILLED_IN:
        shapeStrategy = new FilledInStrategy(shapeStrategy);
        break;
      case OUTLINE_AND_FILLED_IN:
        shapeStrategy = new OutlineAndFilledInStrategy(shapeStrategy);
        break;
    }

    if(isSelected) {
      shapeStrategy = new SelectionOutlineStrategy(shapeStrategy);
    }

    shapeStrategy.drawStrategy(graphic, this);
  }

  /**
   * This is a coordinate normalizer/setter method. This method gets called in the ShapeProperties's constructor with the two CoordinatePair objects passed to it.
   * It then creates two new CoordinatePair objects based on whether start.getX() > end.getX() & start.getY() > end.getY(), which sets startingPoint with
   * both the smallest X & Y coordinates while setting endingPoints with the biggest X & Y coordinates. This results in a shape drawn from the bottom left to the
   * top right coordinates.
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

    startingPoints = new CoordinatePair(newStartX, newStartY);
    endingPoints = new CoordinatePair(newEndX, newEndY);
  }
}
