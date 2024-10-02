package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Project;

/**
 * Hints on how to implement serialization and deserialization
 * of lists of projects and users.
 */
public class ProjectsFileIO {

  /**
   * Call this method before the application exits, to store the users and
   * projects,
   * in serialized form.
   */
  public static void serializeToFile(File file, List<Project> data) throws IOException {
    FileOutputStream fileOut = new FileOutputStream(file.toString());
    ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
    System.out.println("Saving total " + data.size() + " projects.");
    objectOut.writeInt(data.size());
    for (Project project : data) {
      objectOut.writeObject(project);
    }
    objectOut.flush();
    objectOut.close();
  }

  /**
   * Call this method at startup of the application, to deserialize the users and
   * from file the specified file.
   */
  @SuppressWarnings("unchecked")
  public static List<Project> deSerializeFromFile(File file) throws IOException, ClassNotFoundException {
    List<Project> projects = new ArrayList<Project>();
    FileInputStream fileOut = new FileInputStream(file.toString());
    ObjectInputStream objectOut = new ObjectInputStream(fileOut);
    int total = objectOut.readInt();
    for (int i = 0; i < total; i++) {
      Project project = (Project) objectOut.readObject();
      projects.add(project);
    }
    objectOut.close();
    fileOut.close();
    return projects;
  }

  private ProjectsFileIO() {
  }
}
