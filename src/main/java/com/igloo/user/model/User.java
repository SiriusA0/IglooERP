package com.igloo.user.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    // Favorites

    @Column
    private String favoriteAgents;

    @Column
    private String favoriteClients;
}
