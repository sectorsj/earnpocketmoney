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

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Transient
    private String passwordConfirm;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @OneToOne
    @JoinColumn(name = "id_wallet")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "id_people_groups")
    private PeopleGroups peopleGroups;
}
