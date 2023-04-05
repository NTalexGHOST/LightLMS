package ru.darkalive.LightLMS.entities;

import jakarta.persistence.*;

@Entity(name="group_type")
public class EducationType {
    @Id
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Column(name="name")
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Column(name="years")
    private int years;
    public int getYears() { return years; }
    public void setYears(int years) { this.years = years; }
}
