package model.dialogs;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.MouseMode;
import model.interfaces.UserChoices;
import model.interfaces.DialogProvider;
import view.interfaces.DialogChoice;

public class DialogProviderImpl implements DialogProvider {
    private final DialogChoice<ShapeType> chooseShapeDialog;
    private final DialogChoice<ShapeColor> choosePrimaryColorDialog;
    private final DialogChoice<ShapeColor> chooseSecondaryColorDialog;
    private final DialogChoice<ShapeShadingType> chooseShadingTypeDialog;
    private final DialogChoice<MouseMode> chooseStartAndEndPointModeDialog;
    private final UserChoices applicationState;

    public DialogProviderImpl(UserChoices applicationState) {
        this.applicationState = applicationState;
        chooseShapeDialog = new ChooseShapeDialog(this.applicationState);
        choosePrimaryColorDialog = new ChoosePrimaryColorDialog(this.applicationState);
        chooseSecondaryColorDialog = new ChooseSecondaryColorDialog(this.applicationState);
        chooseShadingTypeDialog = new ChooseShadingTypeDialog(this.applicationState);
        chooseStartAndEndPointModeDialog = new ChooseStartAndEndPointModeDialog(this.applicationState);
    }

    @Override
    public DialogChoice<ShapeType> getChooseShapeDialog() {
        return chooseShapeDialog;
    }

    @Override
    public DialogChoice<ShapeColor> getChoosePrimaryColorDialog() {
        return choosePrimaryColorDialog;
    }

    @Override
    public DialogChoice<ShapeColor> getChooseSecondaryColorDialog() {
        return chooseSecondaryColorDialog;
    }

    @Override
    public DialogChoice<ShapeShadingType> getChooseShadingTypeDialog() {
        return chooseShadingTypeDialog;
    }

    @Override
    public DialogChoice<MouseMode> getChooseStartAndEndPointModeDialog() {
        return chooseStartAndEndPointModeDialog;
    }
}
