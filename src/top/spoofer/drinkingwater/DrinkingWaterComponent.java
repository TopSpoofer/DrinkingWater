package top.spoofer.drinkingwater;

import com.intellij.ide.AppLifecycleListener;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;

public class DrinkingWaterComponent implements AppLifecycleListener {
    public DrinkingWaterComponent() {
    }

    public void appStarted() {
        Application app = ApplicationManager.getApplication();
        if (!app.isCommandLine() && !app.isHeadlessEnvironment() && !app.isUnitTestMode()) {
            long interval = PluginConfig.timeInterval2Mlliseconds();
            DrinkingWaterTimer.getInstance().runTimerTask(interval, interval);
        }
    }
}
