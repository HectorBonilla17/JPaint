package main;

import controller.EventConnector;
import controller.EventConnectorImpl;
import controller.KeyboardInterface;
import controller.MouseHandler;
import controller.command.CommandController;
import model.persistence.UserChoicesImpl;
import model.shapeInformation.CanvasLists;
import view.gui.Gui;
import view.gui.GuiWindowImpl;
import view.gui.PaintCanvas;
import view.interfaces.GuiWindow;
import view.interfaces.UiModule;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        CanvasLists objectLists = new CanvasLists();
        PaintCanvas paintCanvas = new PaintCanvas(objectLists);
        GuiWindow guiWindow = new GuiWindowImpl(paintCanvas);
        UiModule uiModule = new Gui(guiWindow);
        UserChoicesImpl appState = new UserChoicesImpl(uiModule);
        CommandController commandController = new CommandController(paintCanvas, appState, objectLists);
        EventConnector controller = new EventConnectorImpl(uiModule, appState, commandController);

        KeyboardInterface keys = new KeyboardInterface(paintCanvas, appState);
        keys.setup();

        CommandController c = new CommandController(paintCanvas, appState, objectLists);
        MouseHandler mouse = new MouseHandler(c);
        paintCanvas.addMouseListener(mouse);
        controller.setup();
    }
}
