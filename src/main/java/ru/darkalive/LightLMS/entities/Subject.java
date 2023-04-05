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

    @ManyToOne
    @JoinColumn(name = "type_id")
    private EducationType educationType;
    public EducationType getEducationType() { return educationType; }
    public void setEducationType(EducationType educationType) { this.educationType = educationType; }

    @Column(name="semester")
    private int semester;
    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }
}
