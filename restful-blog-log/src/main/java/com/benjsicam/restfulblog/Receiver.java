package com.benjsicam.restfulblog;

import com.benjsicam.restfulblog.dao.LogRepository;
import com.benjsicam.restfulblog.domain.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @Autowired
    private LogRepository repository;

    public void receiveMessage(String message) {
        System.out.println(message);
        repository.save(new Log(message));
    }
}
