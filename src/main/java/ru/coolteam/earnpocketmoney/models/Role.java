package ru.coolteam.earnpocketmoney.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
//    private Integer id;
    private Long id;

//     TODO нет в БД, если добавлять. то нужно добавить и в БД
//    private String name;

    @Column (name = "role")
    private String role;

//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;
}
