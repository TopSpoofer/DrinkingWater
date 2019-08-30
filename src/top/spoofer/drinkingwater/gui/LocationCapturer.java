package top.spoofer.drinkingwater.gui;

import java.awt.*;

public class LocationCapturer {
    /**
     * 获取提示框的显示点, 屏幕左上角
     * @param dimension 提示框
     * @return 显示点
     */
    public Point captureRemainderLocation(Dimension dimension) {
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        GraphicsDevice[] gds =  GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();

        for(GraphicsDevice gd : gds) {
            Point showLocation = remainderShowPoint(mousePoint, gd, dimension);
            if (showLocation != null) return showLocation;
        }

        return new Point(0, 0);
    }

    private Point remainderShowPoint(Point mousePoint, GraphicsDevice gd, Dimension dimension) {
        int monitorWidth = gd.getDisplayMode().getWidth();
        int monitorHeight = gd.getDisplayMode().getHeight();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();

        int x_Min = gc.getBounds().x;
        int x_Max = gc.getBounds().x + monitorWidth;

        int y_Min = gc.getBounds().y;
        int y_Max = gc.getBounds().y + monitorHeight;

        if (x_Min <= mousePoint.x && mousePoint.x <= x_Max && y_Min <= mousePoint.y && mousePoint.y <= y_Max) {
            return new Point(x_Max - (int) (dimension.width * 1.17), y_Min + (int)(dimension.height * 1.88));
        } else return null;
    }
}
