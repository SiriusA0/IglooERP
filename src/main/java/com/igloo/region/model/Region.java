package com.igloo.region.model;

import com.igloo.client.model.Client;

import javax.persistence.*;

@Entity
@Table(name = "region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int regionId;

    @Column
    private String name;

    //TODO
    @OneToOne
    @JoinColumn(name = "idClient")
    private Client client;


}
