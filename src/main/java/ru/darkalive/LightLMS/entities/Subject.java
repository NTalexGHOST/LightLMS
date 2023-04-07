package ru.darkalive.LightLMS.entities;

import jakarta.persistence.*;

@Entity(name="lms_subject")
public class Subject {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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

    @ManyToOne
    @JoinColumn(name = "type_id")
    private EducationType educationType;
    public EducationType getEducationType() { return educationType; }
    public void setEducationType(EducationType educationType) { this.educationType = educationType; }

    @Column(name="course")
    private int course;
    public int getCourse() { return course; }
    public void setCourse(int course) { this.course = course; }

    @Column(name="semester")
    private int semester;
    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }

    @ManyToOne
    @JoinColumn(name = "teacher")
    private User teacher;
    public User getTeacher() { return teacher; }
    public void setTeacher(User teacher) { this.teacher = teacher; }
}
