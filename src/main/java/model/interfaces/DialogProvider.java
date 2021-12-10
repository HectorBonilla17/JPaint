package model.interfaces;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.MouseMode;
import view.interfaces.DialogChoice;

public interface DialogProvider {

    DialogChoice<ShapeType> getChooseShapeDialog();

    DialogChoice<ShapeColor> getChoosePrimaryColorDialog();

    DialogChoice<ShapeColor> getChooseSecondaryColorDialog();

    DialogChoice<ShapeShadingType> getChooseShadingTypeDialog();

    DialogChoice<MouseMode> getChooseStartAndEndPointModeDialog();
}
