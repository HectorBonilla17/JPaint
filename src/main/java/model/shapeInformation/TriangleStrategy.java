package model.shapeInformation;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import model.interfaces.ShapeStrategy;

//Class: SE 350 Fall 2021
//Name: Hector Bonilla
//Topic: JPaint Assignment 3

/**
 * This is a shape drawer class that implements ShapeStrategy to create a shape object based on the passed arguments in its drawStrategy() method.
 */
public class TriangleStrategy implements ShapeStrategy {

  private Shape shape;

  /**
   * @See model.interfaces.ShapeStrategy
   * This is a runner method. This method will create a Triangle/Polygon object based on ShapeProperties object passed to it and initialize it into its private Shape shape variable.
   */
  @Override
  public void drawStrategy(Graphics2D graphics, ShapeProperties properties) {

    graphics.setColor(properties.primaryColor.value);
    int [] xCoordinates = new int[3];
    int [] yCoordinates = new int[3];

    xCoordinates[0] = properties.startingPoints.getX();
    xCoordinates[1] = properties.startingPoints.getX();
    xCoordinates[2] = properties.endingPoints.getX();

    yCoordinates[0] = properties.startingPoints.getY();
    yCoordinates[1] = properties.endingPoints.getY();
    yCoordinates[2] = properties.endingPoints.getY();

    shape = new Polygon(xCoordinates, yCoordinates, 3);
  }

  /**
   * This is a getter method. This method returns shape to whoever called it. This will be used
   * by its decorator class in order to create the right outline/fill in for the shape.
   */
  @Override
  public Shape getShape() {
    return shape;
  }
}
