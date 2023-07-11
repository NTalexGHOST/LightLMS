package ru.darkalive.LightLMS.entities;

import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "subject_exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Column(name = "name")
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    @OneToMany
    @JoinColumn(name = "exam_id")
    @OrderBy("position")
    private List<ManualResource> manualResources;
    public List<ManualResource> getManualResources() { return manualResources; }
    public void setManualResources(List<ManualResource> manualResources) { this.manualResources = manualResources; }

    @OneToMany
    @JoinColumn(name = "exam_id")
    @OrderBy("position")
    private List<ExternalResource> externalResources;
    public List<ExternalResource> getExternalResources() { return externalResources; }
    public void setExternalResources(List<ExternalResource> externalResources) { this.externalResources = externalResources; }

    @OneToMany
    @JoinColumn(name = "exam_id")
    private List<Practice> practices;
    public List<Practice> getPractices() { return practices; }
    public void setPractices(List<Practice> practices) { this.practices = practices; }

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

    @Column(name = "description")
    private String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isDescriptionExist() { return ((description != null) && (!description.equals(""))); }

    public Exam() { }
}
