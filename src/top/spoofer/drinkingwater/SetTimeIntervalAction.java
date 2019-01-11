package top.spoofer.drinkingwater;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class SetTimeIntervalAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        // TODO: insert action logic here
        Project currentProject = anActionEvent.getProject();

        String newValue = Messages.showInputDialog(
                currentProject,
                "Set the time interval for drinking water, in minutes",
                "Setting Drinking Water Time",
                Messages.getQuestionIcon(),
                Integer.toString(PluginConfig.getTimeInterval()),
                null);
        if (newValue != null) {
            PluginConfig.setTimeInterval(newValue);
            DrinkingWaterTimer.getInstance().runTimerTask(PluginConfig.timeInterval2Mlliseconds(), PluginConfig.timeInterval2Mlliseconds());
        }
    }

    @Override
    public void update(AnActionEvent anActionEvent) {
        // Set the availability based on whether a project is open
        Project project = anActionEvent.getProject();
        anActionEvent.getPresentation().setEnabledAndVisible(project != null);
    }
}
