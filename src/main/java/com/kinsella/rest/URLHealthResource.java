package com.kinsella.rest;

import com.kinsella.runnables.URLHealthProcessor;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.concurrent.TimeUnit;

@Path("urlcheck")
public class URLHealthResource {

    @Resource(lookup = "java:comp/DefaultManagedScheduledExecutorService")
    private ManagedScheduledExecutorService service;

    @GET
    public String checkURLStatus(){
        String message = "";
        service.schedule(new URLHealthProcessor(),3, TimeUnit.SECONDS);

        message = "Health check initiated";
        return message;
    }
}
