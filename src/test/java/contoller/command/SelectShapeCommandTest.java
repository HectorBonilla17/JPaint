package contoller.command;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import controller.command.CreateShapeCommand;
import controller.command.SelectShapeCommand;
import model.persistence.UserChoicesImpl;
import model.shapeInformation.CanvasLists;
import model.shapeInformation.CoordinatePair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.gui.PaintCanvas;

//Class: SE 350 Fall 2021
//Name: Hector Bonilla
//Topic: JPaint Assignment 2

public class SelectShapeCommandTest {

  @Test
  @DisplayName("Running SelectShapeCommand should change a shape(s)'s isSelected attribute to true if selected, whether it be fully or partial overlap")
  void verifyShapeIsSelectedAttributeChanged() {
    CanvasLists canvasList = new CanvasLists();
    PaintCanvas canvas = new PaintCanvas(canvasList);
    UserChoicesImpl u = new UserChoicesImpl(null);
    SelectShapeCommand selection = new SelectShapeCommand(new CoordinatePair(0,0), new CoordinatePair(5,1), canvasList, canvas);

    CreateShapeCommand shape1 = new CreateShapeCommand(new CoordinatePair(0,0), new CoordinatePair(1,1), u, canvasList);
    CreateShapeCommand shape2 = new CreateShapeCommand(new CoordinatePair(4,0), new CoordinatePair(6,2), u, canvasList);
    CreateShapeCommand shape3 = new CreateShapeCommand(new CoordinatePair(0,4), new CoordinatePair(1,5), u, canvasList);
    shape1.run();
    shape2.run();
    shape3.run();
    selection.run();

    assertAll(
        //Fully encompassed by selection box
        () -> assertTrue(shape1.getShape().isSelected),
        //Partially/Intersected by selection box
        () -> assertTrue(shape2.getShape().isSelected),
        //Doesn't overlap with selection box
        () -> assertFalse(shape3.getShape().isSelected)
    );
  }
}
