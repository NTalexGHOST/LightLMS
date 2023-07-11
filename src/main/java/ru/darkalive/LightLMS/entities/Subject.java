package ru.darkalive.LightLMS.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity(name="lms_subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Column(name="name")
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Column(name="description")
    private String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Column(name="course")
    private int course;
    public int getCourse() { return course; }
    public void setCourse(int course) { this.course = course; }

    @Column(name="semester")
    private int semester;
    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }

    @OneToMany
    @JoinColumn(name = "subject_id")
    private List<LinkUserSubject> links;
    public List<LinkUserSubject> getLinks() { return links; }
    public void setLinks(List<LinkUserSubject> links) { this.links = links; }
    public List<LinkUserSubject> getTeacherLinks() {
        return links.stream().filter(link -> link.getRole().getId() == 2).collect(Collectors.toList());
    }

    @JoinColumn(name = "logo_file_name")
    private String logoFileName;
    public String getLogoFileName() { return logoFileName; }
    public void setLogoFileName(String logoFileName) { this.logoFileName = logoFileName; }
}
