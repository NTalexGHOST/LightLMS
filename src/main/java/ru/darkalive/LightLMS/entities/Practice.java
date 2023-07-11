package ru.darkalive.LightLMS.entities;

import com.google.common.collect.Streams;
import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Entity(name="resource_practice")
public class Practice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Column(name="display_name")
    private String displayName;
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    @Column(name = "opening_date")
    private Timestamp openingDate;
    public Timestamp getOpeningDate() { return openingDate; }
    public void setOpeningDate(Timestamp openingDate) { this.openingDate = openingDate; }

    @Column(name = "closing_date")
    private Timestamp closingDate;
    public Timestamp getClosingDate() { return closingDate; }
    public void setClosingDate(Timestamp closingDate) { this.closingDate = closingDate; }

    @Column(name = "duration")
    private Time duration;
    public Time getDuration() { return duration; }
    public void setDuration(Time duration) { this.duration = duration; }

    @Column(name = "position")
    private int position;
    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ResourceType type;
    public ResourceType getType() { return type; }
    public void setType(ResourceType type) { this.type = type; }
    public boolean isTask() { return type.getName().equals("task"); }
    public boolean isTest() { return type.getName().equals("test"); }

    @Column(name = "description")
    private String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isDescriptionExist() { return ((description != null) && (!description.equals(""))); }

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;
    public Theme getTheme() { return theme; }
    public void setTheme(Theme theme) { this.theme = theme; }
    public boolean isInTheme() { return theme != null; }

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;
    public Exam getExam() { return exam; }
    public void setExam(Exam exam) { this.exam = exam; }

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

    public Practice() { }
}
