package controllers;


        import com.thoughtworks.xstream.XStream;
        import com.thoughtworks.xstream.io.xml.DomDriver;
        import models.*;
        import java.io.FileReader;
        import java.io.FileWriter;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;
        import java.util.ArrayList;
        import java.util.Random;

        import static utils.RatingUtility.generateRandomRating;

public class AppStoreAPI {

    private ArrayList<App> apps = new ArrayList<>();

    public boolean addApp(App app) {
        return apps.add(app);
    }

    public String listAllApps() {

        if (apps.isEmpty()) {
            return "No apps";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < apps.size(); i++) {
            sb.append(i).append(": ").append(apps.get(i)).append('\n');
        }
        return sb.toString();
    }

    public String listSummaryOfAllApps() {

        if (apps.isEmpty()) {
            return "No apps";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < apps.size(); i++) {
            sb.append(i).append(": ").append(apps.get(i).appSummary()).append('\n');
        }
        return sb.toString();
    }

    public String listAllGameApps() {

        if (apps.isEmpty()) {
            return "No Game apps";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i) instanceof GameApp) {
                sb.append(i).append(": ").append(apps.get(i)).append('\n');
            }
        }

        if (sb.isEmpty()) {
            sb.append("No Game apps");
        }
        return sb.toString();
    }

    public int numberOfGameApps() {

        int number = 0;

        for (App app : apps) {
            if (app instanceof GameApp) {
                number++;
            }
        }
        return number;
    }

    public String listAllEducationApps() {

        if (apps.isEmpty()) {
            return "No Education apps";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i) instanceof EducationApp) {
                sb.append(i).append(": ").append(apps.get(i)).append("\n");
            }
        }

        if (sb.isEmpty()) {
            sb.append("No education apps");
        }
        return sb.toString();
    }

    public int numberOfEducationApps() {

        int number = 0;

        for (App app : apps) {
            if (app instanceof EducationApp) {
                number++;
            }
        }
        return number;
    }

    public String listAllProductivityApps() {

        if (apps.isEmpty()) {
            return "No Productivity apps";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i) instanceof ProductivityApp) {
                sb.append(i).append(": ").append(apps.get(i)).append('\n');
            }
        }

        if (sb.isEmpty()) {
            sb.append("No Productivity apps");
        }
        return sb.toString();
    }

    public String listAllAppsByName(String name) {

        if (apps.isEmpty() || !isValidAppName(name)) {
            return "No apps for name " + name + " exists";
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).getAppName().equalsIgnoreCase(name)) {
                builder.append(i).append(": ").append(apps.get(i)).append('\n');
            }
        }
        if (builder.isEmpty()) {
            builder.append("No apps for name ").append(name).append(" exists");
        }
        return builder.toString();
    }

    public String listAllAppsAboveOrEqualAGivenStarRating(int rating) {

        if (apps.isEmpty() || rating < 1 || rating > 5) {
            return "No apps have a rating of " + rating + " or above";
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).calculateRating() >= rating) {
                builder.append(i).append(": ").append(apps.get(i)).append('\n');
            }
        }

        if (builder.isEmpty()) {
            return "No apps have a rating of " + rating + " or above";
        }
        return builder.toString();
    }

    public String listAllRecommendedApps() {

        if (apps.isEmpty()) {
            return "No recommended apps";
        }

        String str = "";

        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).isRecommendedApp()) {
                str += apps.get(i).getAppName();
            } else str = "No recommended apps";
        }
        return str;
    }

    public String listAllAppsByChosenDeveloper(Developer developer) {

        if (apps.isEmpty()) {
            return "No apps for developer: " + developer;
        }

        StringBuilder a = new StringBuilder();

        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).getDeveloper().equals(developer)) {
                a.append(i).append(": ").append(apps.get(i)).append('\n');
            }
        }

        if (a.isEmpty()) {
            a.append("No apps for developer: ").append(developer);
        }
        return a.toString();
    }

    public int numberOfProductivityApp() {

        int number = 0;

        for (App app : apps) {
            if (app instanceof ProductivityApp) {
                number++;
            }
        }
        return number;
    }

    public boolean updateEducationApp(int indexToUpdate, Developer developer, String appName, double appSize, double appVersion, double appCost, int level) {

        App foundApp = findApp(indexToUpdate);

        if ((foundApp != null) && (foundApp instanceof EducationApp)) {
            ((EducationApp) foundApp).setDeveloper(developer);
            ((EducationApp) foundApp).setAppName(appName);
            ((EducationApp) foundApp).setAppCost(appCost);
            ((EducationApp) foundApp).setAppVersion(appVersion);
            ((EducationApp) foundApp).setAppSize(appSize);
            ((EducationApp) foundApp).setLevel(level);
            return true;
        }
        return false;
    }

    public boolean updateGameApp(int indexToUpdate, Developer developer, String appName, double appSize, double appVersion, double appCost, boolean isMultiplayer) {

        App foundApp = findApp(indexToUpdate);

        if ((foundApp != null) && (foundApp instanceof GameApp)) {
            ((GameApp) foundApp).setDeveloper(developer);
            ((GameApp) foundApp).setAppName(appName);
            ((GameApp) foundApp).setAppSize(appSize);
            ((GameApp) foundApp).setAppCost(appCost);
            ((GameApp) foundApp).setAppVersion(appVersion);
            ((GameApp) foundApp).setMultiplayer(isMultiplayer);
            return true;
        }
        return false;
    }

    public boolean updateProductivityApp(int indexToUpdate, Developer developer, String appName, double appSize, double appVersion, double appCost) {

        App foundApp = findApp(indexToUpdate);

        if ((foundApp != null) && (foundApp instanceof ProductivityApp)) {
            ((ProductivityApp) foundApp).setAppName(appName);
            ((ProductivityApp) foundApp).setAppSize(appSize);
            ((ProductivityApp) foundApp).setAppVersion(appVersion);
            ((ProductivityApp) foundApp).setAppCost(appCost);
            ((ProductivityApp) foundApp).setDeveloper(developer);
            return true;
        }
        return false;
    }

    public int numberOfAppsByChosenDeveloper(Developer developer) {

        if (apps.isEmpty()) {
            return 0;
        }

        int number = 0;

        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).getDeveloper().equals(developer)) {
                number++;
            } else {
                number += 0;
            }
        }
        return number;
    }

    public App deleteAppByIndex(int indexToDelete) {

        if (isValidIndex(indexToDelete)) {
            return apps.remove(indexToDelete);
        }
        return null;
    }

    public App randomApp() {
        if (apps.isEmpty()) {
            return null;
        } else {
            return apps.get(new Random().nextInt(apps.size()));
        }
    }

    public void simulateRatings() {
        for (App app : apps) {
            app.addRating(generateRandomRating());
        }
    }

    public boolean isValidAppName(String appName) {

        for (App app : apps) {
            if (app.getAppName().equals(appName)) {
                return true;
            }
        }
        return false;
    }

    public App getAppByName(String name) {

        if (name == null) {
            return null;
        }

        for (App app : apps) {
            if (app.getAppName().equalsIgnoreCase(name)) {
                return app;
            }
        }
        return null;
    }

    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < apps.size());
    }

    public int numberOfApps() {
        return apps.size();
    }

    public void sortAppsByNameAscending() {

        for (int i = apps.size() - 1; i >= 0; i--) {

            int highestIndex = 0;

            for (int j = 0; j <= i; j++) {
                if (apps.get(j).getAppName().compareTo(apps.get(highestIndex).getAppName()) > 0) {
                    highestIndex = j;
                }
            }

            swapApps(apps, i, highestIndex);
        }
    }

    private void swapApps(ArrayList<App> apps, int i, int j) {

        App smaller = apps.get(i);
        App bigger = apps.get(j);
        apps.set(i, bigger);
        apps.set(j, smaller);
    }

    public App findApp(int index) {
        if (isValidIndex(index)) {
            return apps.get(index);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public void load() throws Exception {

        Class<?>[] classes = new Class[]{App.class, EducationApp.class, GameApp.class, ProductivityApp.class, Rating.class};

        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(fileName()));
        apps = (ArrayList<App>) in.readObject();
        in.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(fileName()));
        out.writeObject(apps);
        out.close();
    }
    public String fileName(){
        return "apps.xml";
    }
}{
}
