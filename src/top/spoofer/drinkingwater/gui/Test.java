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
                ReminderDialog d = new ReminderDialog();
                d.disposeWithTime(50000);
            }
        });
    }
}
