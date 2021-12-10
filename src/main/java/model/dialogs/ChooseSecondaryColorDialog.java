package model.dialogs;

import model.ShapeColor;
import model.interfaces.UserChoices;
import view.interfaces.DialogChoice;

public class ChooseSecondaryColorDialog implements DialogChoice<ShapeColor> {

    private final UserChoices applicationState;

    public ChooseSecondaryColorDialog(UserChoices applicationState) {
        this.applicationState = applicationState;
    }

    @Override
    public String getDialogTitle() {
        return "Secondary Color";
    }

    @Override
    public String getDialogText() {
        return "Select a secondary color from the menu below:";
    }

    @Override
    public ShapeColor[] getDialogOptions() {
        return ShapeColor.values();
    }

    @Override
    public ShapeColor getCurrentSelection() {
        return applicationState.getActiveSecondaryColor();
    }
}
