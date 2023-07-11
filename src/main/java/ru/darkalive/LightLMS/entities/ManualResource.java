package ru.darkalive.LightLMS.entities;

import com.google.common.collect.Streams;
import jakarta.persistence.*;

import java.util.Arrays;

@Entity(name="resource_manual")
public class ManualResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Column(name="display_name")
    private String displayName;
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    @Column(name="file_name")
    private String fileName;
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getFileExtension() { return Streams.findLast(Arrays.stream(fileName.split("\\."))).get(); }

    @Column(name = "position")
    private int position;
    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ResourceType type;
    public ResourceType getType() { return type; }
    public void setType(ResourceType type) { this.type = type; }
    public boolean isDoc() { return type.getName().equals("doc"); }
    public boolean isOther() { return type.getName().equals("other"); }

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;
    public Theme getTheme() { return theme; }
    public void setTheme(Theme theme) { this.theme = theme; }

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Practice task;
    public Practice getTask() { return task; }
    public void setTask(Practice task) { this.task = task; }

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;
    public Exam getExam() { return exam; }
    public void setExam(Exam exam) { this.exam = exam; }

    public ManualResource() { }
}
