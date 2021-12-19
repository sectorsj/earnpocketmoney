package ru.coolteam.earnpocketmoney.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "children")
@Data
@NoArgsConstructor
public class Child implements UserEntity {


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

    @Column(name = "wallet")
    private Integer wallet;

    @OneToMany(mappedBy = "child")
    List<Task> tasks;

}
