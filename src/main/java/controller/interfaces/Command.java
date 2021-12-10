package controller.interfaces;

//Class: SE 350 Fall 2021
//Name: Hector Bonilla
//Topic: JPaint Assignment 2

/**
 * Interface class used to interact with MouseHandler & CommandController, used to run the
 * received object's run method.
 */
public interface Command {

  /**
   * This method will either be used to create a new shape, select shapes(s), or move shape(s) and then repaint the canvas with the updated shape list.
   */
  void run();
}
