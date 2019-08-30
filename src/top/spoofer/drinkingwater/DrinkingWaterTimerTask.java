package top.spoofer.drinkingwater;

import top.spoofer.drinkingwater.gui.ReminderDialog;

import javax.swing.*;
import java.util.TimerTask;

public class DrinkingWaterTimerTask extends TimerTask {
    @Override
    public void run() {
        JFrame.setDefaultLookAndFeelDecorated(false);
        ReminderDialog d = new ReminderDialog();
        d.disposeWithTime(5000);
    }
}
