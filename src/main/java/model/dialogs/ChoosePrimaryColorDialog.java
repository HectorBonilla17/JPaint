package model.dialogs;

import model.ShapeColor;
import model.interfaces.UserChoices;
import view.interfaces.DialogChoice;

public class ChoosePrimaryColorDialog implements DialogChoice<ShapeColor> {

    private final UserChoices applicationState;

    public ChoosePrimaryColorDialog(UserChoices applicationState) {
        this.applicationState = applicationState;
    }

    @Override
    public String getDialogTitle() {
        return "Primary Color";
    }

    @Override
    public String getDialogText() {
        return "Select a primary color from the menu below:";
    }

    @Override
    public ShapeColor[] getDialogOptions() {
        return ShapeColor.values();
    }

    @Override
    public ShapeColor getCurrentSelection() {
        return applicationState.getActivePrimaryColor();
    }
}
