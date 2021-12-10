package model.interfaces;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.MouseMode;

/**
 * Defines the operations related to preserving the options the
 * user selects from the toolbar.
 */
public interface UserChoices {
    void setActiveShape();
    void setActiveShape(ShapeType type);

    void setActivePrimaryColor();

    void setActiveSecondaryColor();

    void setActiveShadingType();
    void setActiveShadingType(ShapeShadingType type);

    void setActiveStartAndEndPointMode();
    void setActiveStartAndEndPointMode(MouseMode mode);

    ShapeType getActiveShapeType();

    ShapeColor getActivePrimaryColor();

    ShapeColor getActiveSecondaryColor();

    ShapeShadingType getActiveShapeShadingType();

    MouseMode getActiveMouseMode();
}
