package com.kinsella.rest;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.Path;

@Path("reports")
public class ReportsResource {
        @Resource(lookup = "java.comp/DefaultManagedExecutorService")
        private ManagedExecutorService service;



}


