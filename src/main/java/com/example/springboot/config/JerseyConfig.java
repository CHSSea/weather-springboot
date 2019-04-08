package com.example.springboot.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Configurable;

import javax.ws.rs.ApplicationPath;

@Configurable
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(){
        packages("com.example.springboot.controller");
    }
}
