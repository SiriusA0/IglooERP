package com.igloo.client.model;

import javax.persistence.*;

@Entity
@Table
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    @Column
    private String idNumber;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private char type;

    @Column
    private String email;

    @Column
    private String web;

    @Column
    private String streetLine1;

    @Column
    private String streetLine2;

    @Column
    private Integer countryId;

    @Column
    private Integer zipCode;


//    @Column
//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "client")
//    private Region region;
}
