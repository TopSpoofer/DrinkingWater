package top.spoofer.drinkingwater.gui;

import java.awt.*;

public class LocationCapturer {
    /**
     * 根据一个点所在的位置，计算其所属于的象限
     * @param p 位置点
     * @return 象限
     */
    private int monitorQuadrant(Point p) {
        if (p.x < 0) {
            if (p.y >= 0) return 3;
            else return 1;
        } else {
            if (p.y >= 0) return 4;
            else return 2;
        }
    }

    /**
     * 计算鼠标所在的屏幕右上角，也是对话框显示的位置
     * @param quadrant 象限
     * @param monitorWidth 屏幕长度
     * @param monitorHeight 屏幕高度
     * @param reminderWidth 显示对话框的长度
     * @param reminderHeight 显示对话框的高度
     * @return 对话框显示的位置
     */
    private Point remainderShowPoint(int quadrant,
                                            int monitorWidth,
                                            int monitorHeight,
                                            int reminderWidth,
                                            int reminderHeight) {
        Point showPoint = new Point(0, 0);
        switch (quadrant) {
            case 1:
                int new_x_1 = -(reminderWidth + reminderWidth/2); // 1.5 * reminderWidth
                int new_y_1 = -(monitorHeight - 3 * reminderHeight);
                showPoint = new Point(new_x_1, new_y_1);
                break;

            case 2:
                int new_x_2 = (monitorWidth - (4*reminderWidth)/3);
                int new_y_2 = -(monitorHeight - 3 * reminderHeight);
                showPoint = new Point(new_x_2, new_y_2);
                break;

            case 3:
                int new_x_3 = -(reminderWidth + reminderWidth/2); // 1.5 * reminderWidth
                int new_y_3 = reminderHeight * 2;
                showPoint = new Point(new_x_3, new_y_3);
                break;

            case 4:
                int new_x_4 = (monitorWidth - (4*reminderWidth)/3);
                int new_y_4 = 2 * reminderHeight;
                showPoint = new Point(new_x_4, new_y_4);
                break;
            default:
        }

        return showPoint;
    }

    /**
     * 获取提示框的显示点, 屏幕左上角
     * @param dimension 提示框
     * @return 显示点
     */
    public Point captureRemainderLocation(Dimension dimension) {
        int dialogWidth = (int) dimension.getWidth();
        int dialogHeight = (int) dimension.getHeight();
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
}
