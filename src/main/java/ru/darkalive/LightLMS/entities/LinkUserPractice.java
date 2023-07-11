package ru.darkalive.LightLMS.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity(name="link_user_practice")
public class LinkUserPractice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Column(name="mark")
    private float mark = 0.0f;
    public float getMark() { return mark; }
    public void setMark(float mark) { this.mark = mark; }
    public boolean isRated() { return (mark != 0.0f); }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @ManyToOne
    @JoinColumn(name = "practice_id")
    private Practice practice;
    public Practice getPractice() { return practice; }
    public void setPractice(Practice practice) { this.practice = practice; }

    @JoinColumn(name = "change_date")
    private Timestamp changeDate;
    public Timestamp getChangeDate() { return changeDate; }
    public void setChangeDate(Timestamp changeDate) { this.changeDate = changeDate; }

    @Column(name = "answer")
    private String answer;
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
    public boolean isAnswerExist() { return ((answer != null) && (!answer.equals(""))); }

    @Column(name = "feedback")
    private String feedback;
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
    public boolean isFeedbackExist() { return ((feedback != null) && (!feedback.equals(""))); }
}
