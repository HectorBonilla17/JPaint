package model.dialogs;

import model.ShapeShadingType;
import model.interfaces.UserChoices;
import view.interfaces.DialogChoice;

public class ChooseShadingTypeDialog implements DialogChoice<ShapeShadingType> {
    private final UserChoices applicationState;

    public ChooseShadingTypeDialog(UserChoices applicationState) {

        this.applicationState = applicationState;
    }

    @Override
    public String getDialogTitle() {
        return "Shading Type";
    }

    @Override
    public String getDialogText() {
        return "Select a shading type from the menu below:";
    }

    @Override
    public ShapeShadingType[] getDialogOptions() {
        return ShapeShadingType.values();
    }

    @Override
    public ShapeShadingType getCurrentSelection() {
        return applicationState.getActiveShapeShadingType();
    }
}
