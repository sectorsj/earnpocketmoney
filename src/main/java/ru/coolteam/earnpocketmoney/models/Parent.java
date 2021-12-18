package ru.coolteam.earnpocketmoney.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parents")
@Data
@NoArgsConstructor
public class Parent extends UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column
    private String login;

    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @OneToMany(mappedBy = "parent")
    List<Task> tasks;

    @OneToMany(mappedBy = "parent")
    List<Bonus> bonuses;

}
