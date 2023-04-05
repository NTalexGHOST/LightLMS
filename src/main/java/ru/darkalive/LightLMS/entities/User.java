package ru.darkalive.LightLMS.entities;

import jakarta.persistence.*;

@Entity(name="lms_user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Column(name = "username")
    private String userName;
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    @Column(name = "password")
    private String password;
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Column(name = "active")
    private boolean active = false;
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Column(name = "fullname")
    private String fullName;
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    @Column(name="email")
    private String email;
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    public Group getGroup() { return group; }
    public void setGroup(Group group) { this.group = group; }
}
