package model.shapeInformation;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import model.interfaces.ShapeStrategy;

//Class: SE 350 Fall 2021
//Name: Hector Bonilla
//Topic: JPaint Assignment 3

/**
 * This is a shape drawer class that implements ShapeStrategy to create a shape object based on the passed arguments in its drawStrategy() method.
 */
public class EllipseStrategy implements ShapeStrategy {

  private Shape shape;

  /**
   * @See model.interfaces.ShapeStrategy
   * This is a runner method. This method will create an Ellipse object based on ShapeProperties object passed to it and initialize it into its private Shape shape variable.
   */
  @Override
  public void drawStrategy(Graphics2D graphics, ShapeProperties properties) {
    shape = new Ellipse2D.Double(properties.x, properties.y, properties.length, properties.height);
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
