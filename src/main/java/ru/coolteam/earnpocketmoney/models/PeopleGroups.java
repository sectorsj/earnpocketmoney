package ru.coolteam.earnpocketmoney.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "people_groups")
@Data
@NoArgsConstructor
public class PeopleGroups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
<<<<<<< HEAD:src/main/java/ru/coolteam/earnpocketmoney/models/Child.java
    private String login;

    @Column
    private String password;
=======
    private String name;

    public PeopleGroups(String name) {
        this.name = name;
    }
>>>>>>> dev_front:src/main/java/ru/coolteam/earnpocketmoney/models/PeopleGroups.java
}
