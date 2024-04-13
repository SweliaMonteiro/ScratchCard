package com.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;

    private String firstName;

    private String lastName;

    private boolean isActive;

    private String password;

    @OneToMany
    private List<ScratchCard> scratchCards;

}
