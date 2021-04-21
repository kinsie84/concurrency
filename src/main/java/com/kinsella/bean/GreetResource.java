package com.kinsella.bean;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("greetUser")
public class GreetResource {

    @GET
    public String greetUser(){
        return "Java EE concurrent started";

    }
}
