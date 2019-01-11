package top.spoofer.drinkingwater.gui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;
import java.util.TimerTask;

public class ReminderDialog extends JDialog {
    private JPanel contentPane;
    private JLabel label;

    public ReminderDialog() {
        setContentPane(contentPane);
        setModal(true);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        this.setFocusable(false);
        this.setModalityType(ModalityType.MODELESS);
        this.pack();
        Point location = ReminderDialog.getRemainderLocation(this);
        this.setLocation(location);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }

    public void showWithTime() {
        int imageIndex = new Random(System.currentTimeMillis()).nextInt(2);
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                setReminder(imageIndex + "", ModalityType.DOCUMENT_MODAL);
            }
        }, 2);

        java.util.Timer timer4 = new java.util.Timer();
        timer4.schedule(new TimerTask() {
            public void run() {
                ReminderDialog.this.dispose();
            }
        }, 2400);
    }

    private void setReminder(String image, ModalityType type) {
        this.setModalityType(type);
        String name = "/resources/images/" + image + ".png";
        URL fileURL = this.getClass().getResource(name);
        ImageIcon icon= new ImageIcon(fileURL);
        label.setIcon(icon);
        this.setVisible(true);
    }

    /**
     * 根据一个点所在的位置，计算其所属于的象限
     * @param p 位置点
     * @return 象限
     */
    private static int monitorQuadrant(Point p) {
        if (p.x < 0) {
            if (p.y >= 0) return 3;
            else return 1;
        } else {
            if (p.y >= 0) return 4;
            else return 2;
        }
    }

    /**
     * 计算鼠标所在的屏幕中心点，也是对话框显示的位置
     * @param quadrant 象限
     * @param monitorWidth 屏幕长度
     * @param monitorHeight 屏幕高度
     * @param reminderWidth 显示对话框的长度
     * @param reminderHeight 显示对话框的高度
     * @return 对话框显示的位置
     */
    private static Point remainderShowPoint(int quadrant,
                                           int monitorWidth,
                                           int monitorHeight,
                                           int reminderWidth,
                                           int reminderHeight) {
        Point showPoint = new Point(0, 0);
        switch (quadrant) {
            case 1:
                int new_x_1 = (-monitorWidth / 2) - (reminderWidth / 2);
                int new_y_1 = (-monitorHeight / 2) - (reminderHeight / 2);
                showPoint = new Point(new_x_1, new_y_1);
                break;

            case 2:
                int new_x_2 = (monitorWidth / 2) - (reminderWidth / 2);
                int new_y_2 = (-monitorHeight / 2) - (reminderHeight / 2);
                showPoint = new Point(new_x_2, new_y_2);
                break;

            case 3:
                int new_x_3 = (-monitorWidth / 2) - (reminderWidth / 2);
                int new_y_3 = (monitorHeight / 2) - (reminderHeight / 2);
                showPoint = new Point(new_x_3, new_y_3);
                break;

            case 4:
                int new_x_4 = (monitorWidth / 2) - (reminderWidth / 2);
                int new_y_4 = (monitorHeight / 2) - (reminderHeight / 2);
                showPoint = new Point(new_x_4, new_y_4);
                break;
            default:
        }

        return showPoint;
    }

    /**
     * 获取提示框的显示点，屏幕的中央
     * @param dialog 提示框
     * @return 显示点
     */
    private static Point getRemainderLocation(ReminderDialog dialog) {
        int dialogWidth = dialog.getWidth();
        int dialogHeight = dialog.getHeight();
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        int mouseQuadrant = monitorQuadrant(mousePoint);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gds = ge.getScreenDevices();
        Point showLocation = new Point(0, 0);

        for(GraphicsDevice gd : gds) {
            int monitorWidth = gd.getDisplayMode().getWidth();
            int monitorHeight = gd.getDisplayMode().getHeight();
            GraphicsConfiguration gc = gd.getDefaultConfiguration();
            int displayQuadrant = monitorQuadrant(new Point(gc.getBounds().x, gc.getBounds().y));
            if (displayQuadrant == mouseQuadrant) {
                showLocation = remainderShowPoint(displayQuadrant, monitorWidth, monitorHeight, dialogWidth, dialogHeight);
            }
        }
        return showLocation;
    }

    public static void main(String[] args) {
        ReminderDialog dialog = new ReminderDialog();
        dialog.pack();
        dialog.setVisible(true);
        Point location = ReminderDialog.getRemainderLocation(dialog);
        dialog.setLocation(location);
//        dialog.showWithTime();
    }
}
