package models;

import java.util.ArrayList;

public class App {

    private Developer developer;
    private String appName = "No app name";
    private double appSize = 0;
    private double appVersion = 1.0;
    private double appCost = 0.0;
    private ArrayList<Rating> ratings = new ArrayList<Rating>();

    public App() {
        setDeveloper(developer);
        setAppName(appName);
        setAppSize(appSize);
        setAppVersion(appVersion);
        setAppCost(appCost);
    }

    public boolean isRecommendedApp() {
        return true;
    }
    public String appSummary() {
        return appName + "(V" + appVersion + ")by" + developer + ",��" + appCost + ".Rating:" + calculateRating();
    }

    public boolean addRating(Rating rating) {
        return ratings.add(rating);
    }

    public String listRatings() {

        String str = "";

        if (ratings.isEmpty()) {
            return "No ratings added yet";
        } else {
            for (int i = 0; i <= ratings.size(); i++) {
                str = ratings.get(i).getRatingComment();
            }
        }
        return str;
    }

    public double calculateRating() {
        double sum = 0;
        double avg = 0;
        for (int i = 0; i < ratings.size(); i++) {
            sum += ratings.get(i).getNumberOfStars();
        }
        avg = sum / ratings.size();
        return avg;
    }

    public Developer getDeveloper() {
        return developer;
    }
    public String getAppName() {
        return appName;
    }
    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
    public double getAppSize() {
        return appSize;
    }

    public void setAppSize(double appSize) {
        if (appSize >= 1 && appSize <= 1000) {
            this.appSize = appSize;
        }
    }

    public double getAppVersion() {
        return appVersion;
    }
    public void setRatings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
    }
    public double getAppCost() {
        return appCost;
    }
    public void setAppCost(double appCost) {
        if (appCost >= 0) {
            this.appCost = appCost;
        }
    }
    public void setAppVersion(double appVersion) {
        if (appVersion >= 1.0) {
            this.appVersion = appVersion;
        }
    }
    public ArrayList<Rating> getRatings() {
        return ratings;
    }
    public String toString() {
        return appName + "\n" + "(Version" + appVersion + ")"+"\n"+"by" + developer + "\n"+"Size" + appSize + "MB"+"\n"+"Cost:" + appCost + "\n"+"Rating(" + calculateRating() + "):" + ratings;
    }
}