package top.spoofer.drinkingwater;

import com.intellij.ide.util.PropertiesComponent;

class PluginConfig {
    static private String key = "drinking_water_time_interval";
    static private String defaultValue = "12"; //12分钟，细菌繁殖周期20分钟

    static int getTimeInterval() {
        int interval = 12;
        try {
            String value = PropertiesComponent.getInstance().getValue(PluginConfig.key);
            interval = Integer.parseInt(value == null ? PluginConfig.defaultValue : value);
        } catch (Throwable ex) {
            PluginConfig.setTimeInterval(PluginConfig.defaultValue);
        }
        return interval;
    }

    static long timeInterval2Mlliseconds() {
        return PluginConfig.getTimeInterval() * 60000;
    }

    static void setTimeInterval(String value) {
        PropertiesComponent.getInstance().setValue(PluginConfig.key, value);
    }
}
