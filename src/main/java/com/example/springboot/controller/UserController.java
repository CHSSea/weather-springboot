package com.example.springboot.controller;

import com.example.springboot.bean.User;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
public class UserController {

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public String login(User user){
        return "SUCCESS";
    }

}
