package top.spoofer.drinkingwater;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public class DrinkingWaterComponent implements ApplicationComponent {
    public DrinkingWaterComponent() {
    }

    @Override
    public void initComponent() {
        // TODO: insert component initialization logic here
        long interval = PluginConfig.timeInterval2Mlliseconds();
        DrinkingWaterTimer.getInstance().runTimerTask(interval, interval);
    }

    @Override
    public void disposeComponent() {
        // TODO: insert component disposal logic here
        DrinkingWaterTimer.getInstance().stopTimerTask();
    }

    @Override
    @NotNull
    public String getComponentName() {
        return "DrinkingWaterComponent";
    }
}
