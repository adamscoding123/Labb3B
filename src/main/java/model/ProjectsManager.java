package model;

import model.exceptionClasses.TitleNotUniqueException;

import java.util.ArrayList;
import java.util.List;

/*** Class that manages the projects ***/
public class ProjectsManager {

    private int nextProjectId;
    private List<Project> projects;

    /*** Constructs a new empty object of the ProjectManager class ***/
    public ProjectsManager() {
        this.projects = new ArrayList<>();
        this.nextProjectId = 1;
    }
    /**
     * Sets the ProjectManager projects to handle the given projects
     *
     * @param incomingProjects The projects that the manager till hold onto
     */
    public void setProjects(List<Project> incomingProjects) {
        this.projects.clear();
        this.projects.addAll(incomingProjects);
        this.nextProjectId = getHighestId() + 1;
    }

    /**
     * Checks if the given title already exists within the managers projects
     *
     * @param title the title to check against
     * @return Boolean if title is unique
     */
    public boolean isTitleUnique(String title) {
        for (Project project : projects) {
            if (title.compareTo(project.getTitle()) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds a project to the manager
     *
     * @return The added project
     */
    public Project addProject(String title, String descr) {
        // kontrollera om titeln är unik
        if (!isTitleUnique(title)) {
            throw new TitleNotUniqueException("Project title " + title + "is not unique.");
        }
        Project newProject = new Project(title, descr, nextProjectId++);
        projects.add(newProject);
        return newProject;
    }

    /**
     * Removes a project from the manager
     *
     * @return Boolean if project was removed
     */
    public boolean removeProject(Project project) {
        return projects.remove(project);
    }

    /**
     * Gets a project from id
     *
     * @param id The id to check against
     * @return The project that was found, and if not found then null
     */
    public Project getProjectById(int id) {
        for (Project project : projects) {
            if (project.getID() == id) {
                return project;
            }
        }
        return null;
    }

    /**
     * Finds all projects that matches the given title
     *
     * @param titleString title to match against
     * @return Collection of projects
     */
    public List<Project> findProjects(String titleString) {
        List<Project> matchedProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.toString().toLowerCase().contains(titleString.toLowerCase())) {
                matchedProjects.add(project);
            }
        }
        return matchedProjects;
    }



    /**
     * Gets all immutable projects within the manager
     *
     * @return Collection of Project
     */
    public final List<Project> getProjects() {
        return this.projects;
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

    /**
     * Gets formated class representation in String
     *
     * @return Pretty string from class representation
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProjectsManager{");
        sb.append("nextProjectId=").append(nextProjectId);
        sb.append(", projects=").append(projects);
        sb.append('}');
        return sb.toString();
    }
}
