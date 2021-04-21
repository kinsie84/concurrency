package com.kinsella.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("greet")
public class GreetResource {

    @GET
    public String greetUser(){
        return "Java EE concurrent started";

    }
}
