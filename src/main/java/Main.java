import java.io.File;

import io.ProjectsFileIO;
import model.ProjectsManager;
import ui.MainUI;

public class Main {
    private static final String FILE_NAME = "save.dump";

    public static void run() {
        ProjectsManager manager = new ProjectsManager();
        File file = new File(FILE_NAME);

        try {
            if (file.exists()) {
                manager.setProjects(ProjectsFileIO.deSerializeFromFile(file));
            }
        } catch (Exception exception) {
            System.out.println("Failed to load.");
        }

        // OUR UI
        var ui = new MainUI(manager);
        ui.mainLoop();

        try {

            ProjectsFileIO.serializeToFile(file, manager.getProjects());
        } catch (Exception exception) {

            System.out.println("Failed to save.");

        }
    }

    public static void main(String[] args) {
        run();
    }
}
