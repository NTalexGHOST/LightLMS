package ru.darkalive.LightLMS.entities;

import jakarta.persistence.*;

@Entity(name="link_user_subject")
public class LinkUserSubject {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Column(name="mark")
    private float mark;
    public float getMark() { return mark; }
    public void setMark(float mark) { this.mark = mark; }

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
}
