package ru.darkalive.LightLMS.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="student_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Column(name="name")
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Column(name="admission_year")
    private int admissionYear;
    public int getAdmissionYear() { return admissionYear; }
    public void setAdmissionYear(int admissionYear) { this.admissionYear = admissionYear; }

    @ManyToOne
    @JoinColumn(name = "type_id")
    private EducationType educationType;
    public EducationType getEducationType() { return educationType; }
    public void setEducationType(EducationType educationType) { this.educationType = educationType; }

    @OneToMany
    @JoinColumn(name = "group_id")
    @OrderBy("fullname")
    private List<User> students;
    public List<User> getStudents() { return students; }
    public void setStudents(List<User> students) { this.students = students; }
}
