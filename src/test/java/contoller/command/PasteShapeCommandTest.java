package contoller.command;

import static org.mockito.Mockito.*;

import controller.command.CopyShapeCommand;
import controller.command.CreateShapeCommand;
import controller.command.PasteShapeCommand;
import controller.command.SelectShapeCommand;
import model.persistence.UserChoicesImpl;
import model.shapeInformation.CanvasLists;
import model.shapeInformation.CoordinatePair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.gui.PaintCanvas;

public class PasteShapeCommandTest {

  @Test
  @DisplayName("Running PasteShapeCommand should get the paste the clipboarded shape(s) and add them to the pastedShapeList")
  void verifyCopiedShapeWasAddedToClipboard() {
    CanvasLists canvasList = new CanvasLists();
    PaintCanvas canvas = new PaintCanvas(canvasList);
    UserChoicesImpl u = new UserChoicesImpl(null);
    SelectShapeCommand selection = new SelectShapeCommand(new CoordinatePair(0,0), new CoordinatePair(5,1), canvasList, canvas);
    CopyShapeCommand copy = new CopyShapeCommand(canvasList);
    PasteShapeCommand spyPaste = Mockito.spy(new PasteShapeCommand(canvasList));

    CreateShapeCommand shape1 = new CreateShapeCommand(new CoordinatePair(0,0), new CoordinatePair(1,1), u, canvasList);
    CreateShapeCommand shape2 = new CreateShapeCommand(new CoordinatePair(0,4), new CoordinatePair(1,5), u, canvasList);
    shape1.run();
    shape2.run();
    selection.run();
    copy.run();
    spyPaste.run();
    spyPaste.run();
    spyPaste.run();

    //PasteCommand should run properly 3 times
    verify(spyPaste, times(3)).run();
  }
}
