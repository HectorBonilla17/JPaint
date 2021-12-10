package contoller.command;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.command.CreateShapeCommand;
import controller.command.MoveShapeCommand;
import controller.command.SelectShapeCommand;
import java.util.ArrayList;
import java.util.List;
import model.persistence.UserChoicesImpl;
import model.shapeInformation.CanvasLists;
import model.shapeInformation.CoordinatePair;
import model.shapeInformation.ShapeProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.gui.PaintCanvas;

//Class: SE 350 Fall 2021
//Name: Hector Bonilla
//Topic: JPaint Assignment 2

public class MoveShapeCommandTest {

  /**
   * This is a toString method. This method returns the x & y coordinates of a CoordinatePair object as a string.
   */
  public String coordinateToString(CoordinatePair coordinate){
    return coordinate.getX() + ", " + coordinate.getY();
  }

  @Test
  @DisplayName("Running MoveShapeCommand should change the selected shape(s)'s coordinates based on the x & y delta of the move drag")
  void verifySelectedShapesCoordinatesWereMoved() {
    CanvasLists canvasList = new CanvasLists();
    PaintCanvas canvas = new PaintCanvas(canvasList);
    UserChoicesImpl u = new UserChoicesImpl(null);
    SelectShapeCommand selection = new SelectShapeCommand(new CoordinatePair(0,0), new CoordinatePair(5,1), canvasList, canvas);
    MoveShapeCommand moveCommand = new MoveShapeCommand(new CoordinatePair(40,40), new CoordinatePair(60,70), canvasList);

    CreateShapeCommand shape1 = new CreateShapeCommand(new CoordinatePair(0,0), new CoordinatePair(1,1), u, canvasList);
    CreateShapeCommand shape2 = new CreateShapeCommand(new CoordinatePair(4,0), new CoordinatePair(6,2), u, canvasList);
    CreateShapeCommand shape3 = new CreateShapeCommand(new CoordinatePair(0,4), new CoordinatePair(1,5), u, canvasList);
    shape1.run();
    shape2.run();
    shape3.run();
    selection.run();
    moveCommand.run();

    assertAll(
        //Shape1 should move
        () -> assertEquals(coordinateToString(new CoordinatePair(20,30)), coordinateToString((shape1.getShape().startingPoints))),
        () -> assertEquals(coordinateToString(new CoordinatePair(21,31)), coordinateToString((shape1.getShape().endingPoints))),
        //Shape2 should move
        () -> assertEquals(coordinateToString(new CoordinatePair(24,30)), coordinateToString((shape2.getShape().startingPoints))),
        () -> assertEquals(coordinateToString(new CoordinatePair(26,32)), coordinateToString((shape2.getShape().endingPoints))),
        //Shape3 shouldn't move
        () -> assertEquals(coordinateToString(new CoordinatePair(0,4)), coordinateToString((shape3.getShape().startingPoints))),
        () -> assertEquals(coordinateToString(new CoordinatePair(1,5)), coordinateToString((shape3.getShape().endingPoints)))
    );
  }

  @Test
  @DisplayName("Running MoveShapeCommand should save the shape(s) that were selected for future undos or redos")
  void verifySelectedShapesWereSaved() {
    CanvasLists canvasList = new CanvasLists();
    PaintCanvas canvas = new PaintCanvas(canvasList);
    UserChoicesImpl u = new UserChoicesImpl(null);
    List<ShapeProperties> testSelectedShapeList = new ArrayList<>();
    SelectShapeCommand selection = new SelectShapeCommand(new CoordinatePair(0,0), new CoordinatePair(5,1), canvasList, canvas);
    MoveShapeCommand moveCommand = new MoveShapeCommand(new CoordinatePair(40,40), new CoordinatePair(60,70), canvasList);

    CreateShapeCommand shape1 = new CreateShapeCommand(new CoordinatePair(0,0), new CoordinatePair(1,1), u, canvasList);
    CreateShapeCommand shape2 = new CreateShapeCommand(new CoordinatePair(4,0), new CoordinatePair(6,2), u, canvasList);
    CreateShapeCommand shape3 = new CreateShapeCommand(new CoordinatePair(0,4), new CoordinatePair(1,5), u, canvasList);
    shape1.run();
    shape2.run();
    shape3.run();
    testSelectedShapeList.add(shape1.getShape());
    testSelectedShapeList.add(shape2.getShape());
    selection.run();
    moveCommand.run();

    assertAll(
        () -> assertEquals(testSelectedShapeList, moveCommand.getSelectedShapeList())
    );
  }

  @Test
  @DisplayName("Running undo should undo shape previous move")
  void verifyUndoUnMovesShape() {
    CanvasLists canvasList = new CanvasLists();
    PaintCanvas canvas = new PaintCanvas(canvasList);
    UserChoicesImpl u = new UserChoicesImpl(null);
    SelectShapeCommand selection = new SelectShapeCommand(new CoordinatePair(0,0), new CoordinatePair(5,1), canvasList, canvas);
    MoveShapeCommand moveCommand = new MoveShapeCommand(new CoordinatePair(40,40), new CoordinatePair(60,70), canvasList);

    CreateShapeCommand shape = new CreateShapeCommand(new CoordinatePair(0,0), new CoordinatePair(1,1), u, canvasList);
    shape.run();
    selection.run();
    moveCommand.run();
    moveCommand.undo();

    assertAll(
        () -> assertEquals(coordinateToString(new CoordinatePair(0,0)), coordinateToString(shape.getShape().startingPoints)),
        () -> assertEquals(coordinateToString(new CoordinatePair(1,1)), coordinateToString(shape.getShape().endingPoints))
    );
  }

  @Test
  @DisplayName("Running redo should redo shape previous undo")
  void verifyRedoReMovesShape() {
    CanvasLists canvasList = new CanvasLists();
    PaintCanvas canvas = new PaintCanvas(canvasList);
    UserChoicesImpl u = new UserChoicesImpl(null);
    SelectShapeCommand selection = new SelectShapeCommand(new CoordinatePair(0,0), new CoordinatePair(5,1), canvasList, canvas);
    MoveShapeCommand moveCommand = new MoveShapeCommand(new CoordinatePair(40,40), new CoordinatePair(60,70), canvasList);

    CreateShapeCommand shape = new CreateShapeCommand(new CoordinatePair(0,0), new CoordinatePair(1,1), u, canvasList);
    shape.run();
    selection.run();
    moveCommand.run();
    moveCommand.undo();
    moveCommand.redo();

    assertAll(
        () -> assertEquals(coordinateToString(new CoordinatePair(20,30)), coordinateToString(shape.getShape().startingPoints)),
        () -> assertEquals(coordinateToString(new CoordinatePair(21,31)), coordinateToString(shape.getShape().endingPoints))
    );
  }

}
