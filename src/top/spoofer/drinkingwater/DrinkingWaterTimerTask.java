package top.spoofer.drinkingwater;

import top.spoofer.drinkingwater.gui.ReminderDialogNew;

import javax.swing.*;
import java.util.TimerTask;

public class DrinkingWaterTimerTask extends TimerTask {
    @Override
    public void run() {
        JFrame.setDefaultLookAndFeelDecorated(false);
        ReminderDialogNew d = new ReminderDialogNew();
        d.disposeWithTime(5000);
    }
}
