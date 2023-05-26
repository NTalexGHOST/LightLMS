package ru.darkalive.LightLMS.entities;

import jakarta.persistence.*;

@Entity(name="resource_external")
public class ExternalResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Column(name="display_name")
    private String displayName;
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    @Column(name="url")
    private String url;
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    @Column(name = "position")
    private int position;
    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ResourceType type;
    public ResourceType getType() { return type; }
    public void setType(ResourceType type) { this.type = type; }
    public boolean isLink() { return type.getName().equals("link"); }
    public boolean isYoutube() { return type.getName().equals("youtube"); }

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;
    public Theme getTheme() { return theme; }
    public void setTheme(Theme theme) { this.theme = theme; }

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
    public Task getTask() { return task; }
    public void setTask(Task task) { this.task = task; }

    public ExternalResource() { }
}
