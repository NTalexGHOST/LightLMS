package ru.darkalive.LightLMS.entities;

import jakarta.persistence.*;

@Entity(name="link_user_subject")
public class LinkUserSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Column(name="mark")
    private float mark = 0;
    public float getMark() { return mark; }
    public void setMark(float mark) { this.mark = mark; }
    public float forecastMark() {

        return 0;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
