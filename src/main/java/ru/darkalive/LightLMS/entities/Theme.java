package ru.darkalive.LightLMS.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "subject_theme")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Column(name = "name")
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Column(name = "position")
    private int position;
    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    @OneToMany
    @JoinColumn(name = "theme_id")
    @OrderBy("position")
    private List<ManualResource> manualResources;
    public List<ManualResource> getManualResources() { return manualResources; }
    public void setManualResources(List<ManualResource> manualResources) { this.manualResources = manualResources; }

    @OneToMany
    @JoinColumn(name = "theme_id")
    @OrderBy("position")
    private List<ExternalResource> externalResources;
    public List<ExternalResource> getExternalResources() { return externalResources; }
    public void setExternalResources(List<ExternalResource> externalResources) { this.externalResources = externalResources; }

    @OneToMany
    @JoinColumn(name = "theme_id")
    private List<Task> tasks;
    public List<Task> getTasks() { return tasks; }
    public void setTasks(List<Task> tasks) { this.tasks = tasks; }

    @Column(name = "description")
    private String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isDescriptionExist() {
        return ((description != null) && (!description.equals("")));
    }

    public Theme() { }
}
