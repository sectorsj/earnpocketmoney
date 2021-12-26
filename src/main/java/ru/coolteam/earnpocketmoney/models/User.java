package ru.coolteam.earnpocketmoney.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String name;

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
