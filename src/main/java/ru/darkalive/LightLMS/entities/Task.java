package ru.darkalive.LightLMS.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity(name="theme_task")
public class Task {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Column(name="display_name")
    private String displayName;
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    @Column(name="opening_date")
    private Timestamp openingDate;
    public Timestamp getOpeningDate() { return openingDate; }
    public void setOpeningDate(Timestamp openingDate) { this.openingDate = openingDate; }

    @Column(name="closing_date")
    private Timestamp closingDate;
    public Timestamp getClosingDate() { return closingDate; }
    public void setClosingDate(Timestamp closingDate) { this.closingDate = closingDate; }

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;
    public Theme getTheme() { return theme; }
    public void setTheme(Theme theme) { this.theme = theme; }

    @OneToMany
    @JoinColumn(name = "task_id")
    @OrderBy("position")
    private List<ManualResource> manualResources;
    public List<ManualResource> getManualResources() { return manualResources; }
    public void setManualResources(List<ManualResource> manualResources) { this.manualResources = manualResources; }

    @OneToMany
    @JoinColumn(name = "task_id")
    @OrderBy("position")
    private List<ExternalResource> externalResources;
    public List<ExternalResource> getExternalResources() { return externalResources; }
    public void setExternalResources(List<ExternalResource> externalResources) { this.externalResources = externalResources; }

    public Task() { }
}
