package contoller.command;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.command.CopyShapeCommand;
import controller.command.CreateShapeCommand;
import controller.command.SelectShapeCommand;
import model.persistence.UserChoicesImpl;
import model.shapeInformation.CanvasLists;
import model.shapeInformation.CoordinatePair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.gui.PaintCanvas;

public class CopyShapeCommandTest {

  @Test
  @DisplayName("Running CopyShapeCommand should get the selected shape(s) and add them to the clipBoardList")
  void verifyCopiedShapeWasAddedToClipboard() {
    CanvasLists canvasList = new CanvasLists();
    PaintCanvas canvas = new PaintCanvas(canvasList);
    UserChoicesImpl u = new UserChoicesImpl(null);
    SelectShapeCommand selection = new SelectShapeCommand(new CoordinatePair(0,0), new CoordinatePair(5,1), canvasList, canvas);
    CopyShapeCommand copy = new CopyShapeCommand(canvasList);

    CreateShapeCommand shape1 = new CreateShapeCommand(new CoordinatePair(0,0), new CoordinatePair(1,1), u, canvasList);
    CreateShapeCommand shape2 = new CreateShapeCommand(new CoordinatePair(0,4), new CoordinatePair(1,5), u, canvasList);
    shape1.run();
    shape2.run();
    selection.run();
    copy.run();

    assertAll(
        //Shape1 should be copied while shape2 shouldn't
        () -> assertEquals(1, canvasList.getClipBoardList().size())
        );
  }
}
