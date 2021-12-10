package model.shapeInformation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import model.interfaces.ShapeStrategy;

//Class: SE 350 Fall 2021
//Name: Hector Bonilla
//Topic: JPaint Assignment 3

/**
 * This is a shape drawer class that implements ShapeStrategy to draw a shape object based on the passed arguments in its drawStrategy() method
 * and from the passed ShapeStrategy object's shape object gotten via its getShape() method.
 */
public class SelectionOutlineStrategy implements ShapeStrategy {

  private final ShapeStrategy shape;

  /**
   * This is a constructor method. This method initializes the passed ShapeStrategy object into the private ShapeStrategy shape variable for shape drawing purposes.
   */
  public SelectionOutlineStrategy(ShapeStrategy shape) {
    this.shape = shape;
  }

  /**
   * @See model.interfaces.ShapeStrategy
   * This is a runner method. This method will draw a Shape on the passed Graphics2D object based on the Shape object creating in private ShapeStrategy Shape's drawStrategy(). The private ShapeStrategy shape variable
   * being the lower level decorator object, which uses the Graphics2d and ShapeProperties object passed to this method to call its drawStrategy() method, which draws the needed shape and then this method uses
   * shape's getShape() method in order to know the dimensions needed to draw the selection outline around the drawn shape.
   */
  @Override
  public void drawStrategy(Graphics2D graphics, ShapeProperties properties) {
    shape.drawStrategy(graphics, properties);
    Stroke stroke = new BasicStroke(8, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
    graphics.setStroke(stroke);
    graphics.setColor(Color.BLACK);
    graphics.draw(shape.getShape());
  }

  /**
   * This is a getter method. This method returns shape to whoever called it.
   */
  @Override
  public Shape getShape() {
    return shape.getShape();
  }
}
