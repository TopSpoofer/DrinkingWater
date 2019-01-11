package top.spoofer.drinkingwater;

import top.spoofer.drinkingwater.gui.ReminderDialog;
import java.util.TimerTask;

public class DrinkingWaterTimerTask extends TimerTask {
    @Override
    public void run() {
        ReminderDialog dialog = new ReminderDialog();
        dialog.showWithTime();
    }
}
