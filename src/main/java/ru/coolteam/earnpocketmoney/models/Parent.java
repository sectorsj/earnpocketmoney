package ru.coolteam.earnpocketmoney.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "parents")
@Data
@NoArgsConstructor
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String login;

    @Column
    private String password;
}
