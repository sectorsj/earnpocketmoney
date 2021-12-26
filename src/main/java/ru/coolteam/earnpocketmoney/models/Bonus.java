package ru.coolteam.earnpocketmoney.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bonuses")
@Data
@NoArgsConstructor
public class Bonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "bonus_text")
    private String bonusText;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "id_user_creating_bonus")
    private User userCreatingBonus;

    @ManyToOne
    @JoinColumn(name = "id_user_getting_bonus")
    private User userGettingBonus;

    @Column(name = "price")
    private Long price;

    @Column(name = "getting_status")
    private Boolean gettingStatus;


}
