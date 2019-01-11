package top.spoofer.drinkingwater;

import java.util.Timer;

class DrinkingWaterTimer {
    private static DrinkingWaterTimer timerManager = null;
    private DrinkingWaterTimer() {}

    static DrinkingWaterTimer getInstance() {
        if (timerManager == null) {
            timerManager = new DrinkingWaterTimer();
        }
        return timerManager;
    }

    void runTimerTask(long delay, long period) {
        resetTimerTask();
        resetTimer();
        timer.schedule(timerTask, delay, period);
    }

    void stopTimerTask() {
        if (timerTask != null) {
            if (timer != null) {
                timer.cancel();
            }
            timerTask.cancel();
            timerTask = null;
        }
    }

    private void resetTimerTask() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        timerTask = new DrinkingWaterTimerTask();
    }

    private void resetTimer() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer("DrinkingWaterTimer");
    }

    private Timer timer = null;
    private DrinkingWaterTimerTask timerTask = null;
}
