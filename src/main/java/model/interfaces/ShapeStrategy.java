package model.interfaces;

import java.awt.Graphics2D;
import java.awt.Shape;
import model.shapeInformation.ShapeProperties;

//Class: SE 350 Fall 2021
//Name: Hector Bonilla
//Topic: JPaint Assignment 3

/**
 * Interface class used to create shape strategies.
 */
public interface ShapeStrategy {

  /**
   * This method will be used to draw the shape based on strategy class it is implemented. Whether that be an Ellipse, Rectangle, or Triangle.
   */
  void drawStrategy(Graphics2D graphics, ShapeProperties properties);

  /**
   * This method will be used to the get Shape object which in initialized in either the EllipseStrategy, RectangleStrategy, or TriangleStrategy. This will be used
   * by the decorator classes for ShapeStrategy, in order to create the right outline/fill in for each shape.
   */
  Shape getShape();
}
