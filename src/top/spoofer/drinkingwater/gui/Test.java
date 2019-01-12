package top.spoofer.drinkingwater.gui;

import java.awt.*;

public class Test {
    public static void main(String[] args) {
        new Test();
    }

    private Test() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ReminderDialogNew d = new ReminderDialogNew();
                d.disposeWithTime(50000);
            }
        });
    }
}
