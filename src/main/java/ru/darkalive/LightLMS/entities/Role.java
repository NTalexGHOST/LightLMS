package ru.darkalive.LightLMS.entities;

import jakarta.persistence.*;

@Entity(name="user_role")
public class Role {
    @Id
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Column(name="name")
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
