package controller;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import model.MouseMode;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.UserChoices;

/**
 * Provides a keyboard interface for:
 * - shape selection
 *    ctrl-R = Rectangle
 *    ctrl-T = Triangle
 *    ctrl-E = Ellipse
 * - Mouse mode
 *    ctrl-D = Draw
 *    ctrl-S = Select
 *    ctrl-M = Move
 * - Shading type
 *    ctrl-F = Filled in
 *    ctrl-O = outlined
 *    ctrl-B = Both filled ina and outlined
 *
 *    To use, add the following code somewhere early in the program
 *    (I have it in main())
 *
 *    KeyboardInterface keys = new KeyboardInterface(paintCanvas, appState);
 *    keys.setup();
 */
public class KeyboardInterface {
  private JComponent target;
  private UserChoices userChoices;

  public KeyboardInterface(JComponent target, UserChoices userChoices) {
    this.target = target;
    this.userChoices = userChoices;
  }

  public void setup() {

    // Moouse modes
    setupAction("draw", "control D", MouseMode.DRAW, (mode) -> userChoices.setActiveStartAndEndPointMode((MouseMode)mode));
    setupAction("select", "control S", MouseMode.SELECT, (mode) -> userChoices.setActiveStartAndEndPointMode((MouseMode)mode));
    setupAction("move", "control M", MouseMode.MOVE, (mode) -> userChoices.setActiveStartAndEndPointMode((MouseMode)mode));

    // Shape choices
    setupAction("rectangle", "control R", ShapeType.RECTANGLE, (type) -> userChoices.setActiveShape((ShapeType) type));
    setupAction("triangle", "control T", ShapeType.TRIANGLE, (type) -> userChoices.setActiveShape((ShapeType) type));
    setupAction("ellipse", "control E", ShapeType.ELLIPSE, (type) -> userChoices.setActiveShape((ShapeType) type));

    // Shading types
    setupAction("filled", "control F", ShapeShadingType.FILLED_IN, (type) -> userChoices.setActiveShadingType((ShapeShadingType) type));
    setupAction("outlined", "control O", ShapeShadingType.OUTLINE, (type) -> userChoices.setActiveShadingType((ShapeShadingType) type));
    setupAction("both", "control B", ShapeShadingType.OUTLINE_AND_FILLED_IN, (type) -> userChoices.setActiveShadingType((ShapeShadingType) type));

  }

  private void setupAction(String name, String strokeName, Object mode, Consumer c) {
    target.getActionMap().put(name, new AbstractAction() {

      @Override
      public void actionPerformed(ActionEvent e) {
        c.accept(mode);
      }
    });
    InputMap inputMap = target.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    inputMap.put(KeyStroke.getKeyStroke(strokeName), name);
  }
}
