package controller.interfaces;

//Class: SE 350 Fall 2021
//Name: Hector Bonilla
//Topic: JPaint Assignment 2

/**
 * Interface class used to interact with CommandHistory's undo & redo methods.
 */
public interface Undoable {

  /**
   * This method will be used to undo a shape creation and addition to the shape list or shape move, then it repaints the canvas
   * with the updated shape list.
   */
  void undo();

  /**
   * This method will be used to redo a shape creation, which was previously undid
   * and re-adds the undid shape back to the shape list, or shape move then it repaints the canvas with the updated shape list.
   */
  void redo();
}
