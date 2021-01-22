package top.spoofer.drinkingwater.gui;

import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;
import java.util.TimerTask;

public class ReminderDialog {
    private Dimension dimension = new Dimension(240, 61);
    private LocationCapturer capturor = new LocationCapturer();
    private JWindow frame = new JWindow();
    private String[] tips = {
            "drinking water ~~~  (●ﾟωﾟ●)",
            "防猝死提醒！! ~~~  (づ｡◕‿‿◕｡)づ",
            "再不休息就等着挂吧 ~~~  (●´∀｀●)ﾉ",
            "996.ICU.AVI 提醒 ~~~  ψ(｀∇´)ψ"
    };

    public ReminderDialog() {
        JFrame.setDefaultLookAndFeelDecorated(false);

        Random df = new Random();
        int tipIndex = df.nextInt(tips.length);
        String tip = tips[tipIndex];

        JLabel jlabel = new JLabel(tip);
        JBLabel jl = new JBLabel("");
        jlabel.setForeground(new JBColor(new Color(248, 248, 255), new Color(248, 248, 255)));

        DialogPane p = new DialogPane(dimension);
        p.setBackground(new JBColor(new Color(78, 80, 82), new Color(78, 80, 82)));

        p.add(jlabel);
        frame.setContentPane(p);
        frame.pack();
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        Point location = capturor.captureRemainderLocation(dimension);
        frame.setLocation(location);
    }

    public void disposeWithTime(int delay) {
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                frame.dispose();
            }
        }, delay);
    }

    public class DialogPane extends JPanel {
        private Dimension dimension;
        DialogPane(Dimension d) {
            dimension = d;
            setLayout(new GridBagLayout());
        }

        @Override
        public Dimension getPreferredSize() {
            return dimension;
        }
    }
}
