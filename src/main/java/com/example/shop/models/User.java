package com.example.shop.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "customers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "number_phone")
    private String numberPhone;

    @JoinColumn(name = "role_id")
    @ManyToOne
    private Role role;


    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<Order>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
