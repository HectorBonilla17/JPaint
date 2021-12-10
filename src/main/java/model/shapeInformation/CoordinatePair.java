package model.shapeInformation;

//Class: SE 350 Fall 2021
//Name: Hector Bonilla
//Topic: JPaint Assignment 2

/**
 * This is a class that functions as a Point class. It creates an object that stores to integer that
 * will function as the x & y value of a coordinate on the canvas.
 */
public class CoordinatePair {

  private int x;
  private int y;

  /**
   * This is a constructor method. This method initializes the CoordinatePair object with the
   * two integer parameters of a & b into the private variables x & y.
   */
  public CoordinatePair(int a, int b){
    this.x = a;
    this.y = b;
  }

  /**
   * This is a getter method. This method returns the current x value of the coordinate to whoever calls it.
   */
  public int getX(){
    return x;
  }

  /**
   * This is a getter method. This method returns the current y value of the coordinates to whoever calls it.
   */
  public int getY(){
    return y;
  }

  /**
   * This is a setter method. This method sets the current x value of the coordinates to whatever is passed to it.
   */
  public void setX(int i) { x = i; }

  /**
   * This is a setter method. This method sets the current y value of the coordinates to whatever is passed to it.
   */
  public void setY(int i) { y = i; }
}
