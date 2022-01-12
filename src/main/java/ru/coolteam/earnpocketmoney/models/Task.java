package ru.coolteam.earnpocketmoney.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tasks")
@NoArgsConstructor
public class Task{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name="task_text")
    private String taskText;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "id_user_creating_task")
    private User userCreatingTask;

    @ManyToOne
    @JoinColumn(name = "id_user_executing_task")
    private User userExecutingTask;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private Status status;

    //обозвал зарплату
    @Column(name = "wages")
    private Long wages;

    public void incrementWages() {
        this.wages++;
        System.out.println("wages = " + getWages());
    }

    public void decrementWages() {
        this.wages--;
        System.out.println("wages = " + getWages());
    }

}