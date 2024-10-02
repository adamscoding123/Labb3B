package model;

import model.exceptionClasses.TitleNotUniqueException;

import java.util.ArrayList;
import java.util.List;

public class ProjectsManager {

    private int nextProjectId;
    public List<Project> projects;

    public ProjectsManager() {
        this.projects = new ArrayList<>();
        this.nextProjectId = 1;
    }

    public void setProjects(List<Project> incomingProjects) {
        this.projects.clear();
        this.projects.addAll(incomingProjects);
        this.nextProjectId = getHighestId() + 1;
    }

    public boolean isTitleUnique(String title) {
        for (Project project : projects) {
            if (title.compareTo(project.getTitle()) == 0) {
                return false;
            }
        }
        return true;
    }

    public Project addProject(String title, String descr) {
        // kontrollera om titeln är unik
        if (!isTitleUnique(title)) {
            throw new TitleNotUniqueException("Project title " + title + "is not unique.");
        }
        Project newProject = new Project(title, descr, nextProjectId++);
        projects.add(newProject);
        return newProject;
    }

    // för att ta bort ett projekt
    public boolean removeProject(Project project) {
        return projects.remove(project);
    }

    public Project getProjectById(int id) {
        for (Project project : projects) {
            if (project.getID() == id) {
                return project;
            }
        }
        return null;
    }

    public List<Project> findProjects(String titleString) {
        List<Project> matchedProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.toString().toLowerCase().contains(titleString.toLowerCase())) {
                matchedProjects.add(project); // gör till lowercase, kolla om = en annan string med lowercase. if case,
                                              // inkrementera matched
            }
        }
        return matchedProjects;
    }

    private int getHighestId() {
        int highestId = 0;
        for (Project project : projects) {
            int id = project.getID();
            if (id > highestId) {
                highestId = id;
            }
        }
        return highestId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProjectsManager{");
        sb.append("nextProjectId=").append(nextProjectId);
        sb.append(", projects=").append(projects);
        sb.append('}');
        return sb.toString();
    }
}
