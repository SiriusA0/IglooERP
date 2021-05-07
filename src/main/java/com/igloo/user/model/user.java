package com.igloo.user.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    // Favorites

    @Column
    private List<Integer> favoriteAgents;

    @Column
    private List<Integer> favoriteClients;
}
