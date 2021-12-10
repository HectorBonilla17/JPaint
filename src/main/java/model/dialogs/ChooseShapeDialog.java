package model.dialogs;

import model.ShapeType;
import model.interfaces.UserChoices;
import view.interfaces.DialogChoice;

public class ChooseShapeDialog implements DialogChoice<ShapeType> {
    private final UserChoices applicationState;

    public ChooseShapeDialog(UserChoices applicationState) {

        this.applicationState = applicationState;
    }

    @Override
    public String getDialogTitle() {
        return "Shape";
    }

    @Override
    public String getDialogText() {
        return "Select a shape from the menu below:";
    }

    @Override
    public ShapeType[] getDialogOptions() {
        return ShapeType.values();
    }

    @Override
    public ShapeType getCurrentSelection() {
        return applicationState.getActiveShapeType();
    }
}
