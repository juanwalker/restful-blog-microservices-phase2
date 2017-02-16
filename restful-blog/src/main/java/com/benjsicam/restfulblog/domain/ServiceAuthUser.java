package com.benjsicam.restfulblog.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

public class ServiceAuthUser {

    public String user;
    public String password;
    public String encodedPassword;

    public ServiceAuthUser(String user, String password,String encodedPassword){
        this.user = user;
        this.password = password;
        this.encodedPassword= encodedPassword;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCredentials(){
        return this.user.concat(":").concat(this.password);
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }
}