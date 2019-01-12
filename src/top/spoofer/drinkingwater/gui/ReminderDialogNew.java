package top.spoofer.drinkingwater.gui;

import com.intellij.ui.JBColor;
import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.TimerTask;

public class ReminderDialogNew {
    private Dimension dimension = new Dimension(240, 61);
    private LocationCapturer capturor = new LocationCapturer();
    private JWindow frame = new JWindow();

    public ReminderDialogNew() {
        AWTUtilities.setWindowOpaque(frame, false);
        DialogPane p = new DialogPane(dimension);
        p.setBackground(JBColor.GRAY);
        JLabel jlabel = new JLabel("drinking water ~~~  (●ﾟωﾟ●)");
        jlabel.setForeground(JBColor.BLACK);
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
            setOpaque(false);
            setLayout(new GridBagLayout());
        }

        @Override
        public Dimension getPreferredSize() {
            return dimension;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
            Graphics2D g2d = (Graphics2D) g.create();
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHints(hints);
            Color c = getBackground();
            g2d.setColor(c);
            g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
            g2d.dispose();
        }
    }
}
