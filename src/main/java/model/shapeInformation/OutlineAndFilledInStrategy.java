package model.shapeInformation;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Shape;
import model.interfaces.ShapeStrategy;

//Class: SE 350 Fall 2021
//Name: Hector Bonilla
//Topic: JPaint Assignment 3

/**
 * This is a shape drawer class that implements ShapeStrategy to draw a shape object based on the passed arguments in its drawStrategy() method
 * and from the passed ShapeStrategy object's shape object gotten via its getShape() method.
 */
public class OutlineAndFilledInStrategy implements ShapeStrategy {

  private final ShapeStrategy shape;

  /**
   * This is a constructor method. This method initializes the passed ShapeStrategy object into the private ShapeStrategy shape variable for shape drawing purposes.
   */
  public OutlineAndFilledInStrategy(ShapeStrategy shapeStrategy) {
    this.shape = shapeStrategy;
  }

  /**
   * @See model.interfaces.ShapeStrategy
   * This is a runner method. This method will draw a Shape on the passed Graphics2D object based on the Shape object creating in private ShapeStrategy Shape's drawStrategy(). The private ShapeStrategy shape variable
   * being the lower level decorator object, which uses the Graphics2d and ShapeProperties object passed to this method to call its drawStrategy() method and create its Shape object for use of this class.
   */
  @Override
  public void drawStrategy(Graphics2D graphics, ShapeProperties properties) {
    shape.drawStrategy(graphics, properties);
    graphics.setColor(properties.primaryColor.value);
    graphics.fill(shape.getShape());

    graphics.setStroke(new BasicStroke(5));
    graphics.setColor(properties.secondaryColor.value);
    graphics.draw(shape.getShape());
  }

  /**
   * This is a getter method. This method returns shape to whoever called it. This will be used
   * by its decorator class in order to create the right outline/fill in for the shape.
   */
  @Override
  public Shape getShape() {
    return shape.getShape();
  }
}
