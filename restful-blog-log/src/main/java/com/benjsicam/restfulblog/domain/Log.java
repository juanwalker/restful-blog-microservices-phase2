package com.benjsicam.restfulblog.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Log {

    public Log(String message){
        this.message= message;
    }
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

	private String message;

}
