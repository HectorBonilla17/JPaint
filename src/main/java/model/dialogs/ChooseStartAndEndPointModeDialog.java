package model.dialogs;

import model.MouseMode;
import model.interfaces.UserChoices;
import view.interfaces.DialogChoice;

public class ChooseStartAndEndPointModeDialog implements DialogChoice<MouseMode> {
    private final UserChoices applicationState;

    public ChooseStartAndEndPointModeDialog(UserChoices applicationState) {

        this.applicationState = applicationState;
    }

    @Override
    public String getDialogTitle() {
        return "Start and End Point Mode";
    }

    @Override
    public String getDialogText() {
        return "Select a shading type from the menu below:";
    }

    @Override
    public MouseMode[] getDialogOptions() {
        return MouseMode.values();
    }

    @Override
    public MouseMode getCurrentSelection() {
        return applicationState.getActiveMouseMode();
    }
}
