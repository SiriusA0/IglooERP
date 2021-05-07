package com.igloo.agent.model;

import javax.persistence.*;

@Entity
@Table(name = "agent")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int agent_id;

    @Column
    private String first_name;

    @Column
    private String last_name;
    
    public Agent() {
    	
    }


    public Agent(String first_name, String last_name) {
        super();
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


    public int getAgent_id() {
        return agent_id;
    }

}
