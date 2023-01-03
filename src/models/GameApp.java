package models;

import utils.Utilities;

public class GameApp extends App{

    private boolean isMultiplayer = false;

    public GameApp(Developer developer, String appName, double appSize, double appVersion, double appCost, boolean isMultiplayer) {
        super();
        setAppVersion(appVersion);
        setAppCost(appCost);
        setAppName(appName);
        setDeveloper(developer);
        setAppSize(appSize);
        setMultiplayer(isMultiplayer);
    }
    public boolean isMultiplayer() {
        return isMultiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        isMultiplayer = multiplayer;
    }

    public boolean isRecommendedApp() {
        if (isMultiplayer && Utilities.greaterThanOrEqualTo(calculateRating(), 4.0)) {
            return true;
        } else {
            return false;
        }
    }

    public String appSummary() {
        return getAppName() + "(V" + getAppVersion() + ")" + "by" + getDeveloper().toString() + "��" + getAppCost() + ".Rating:" + calculateRating();
    }

    public String toString() {
        return super.toString() + Utilities.booleanToYN(isMultiplayer);
    }
}