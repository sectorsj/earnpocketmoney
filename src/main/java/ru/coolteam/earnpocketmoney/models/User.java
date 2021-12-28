package ru.coolteam.earnpocketmoney.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Transient
    private String passwordConfirm;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

//    @ManyToMany
//    @JoinColumn(name = "id_role")
//    private Set<Role> roles;

    @OneToOne
    @JoinColumn(name = "id_wallet")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "id_people_groups")
    private PeopleGroups peopleGroups;
}
