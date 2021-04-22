package com.kinsella.rest;

import com.kinsella.bean.BankAccount;
import com.kinsella.dao.BankAccountDao;
import com.kinsella.runnables.ReportProcessor;
import com.mchange.lang.StringUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import jdk.jfr.internal.LogLevel;
import sun.misc.RequestProcessor;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("reports")
public class ReportsResource {

    private BankAccountDao dao;
    @Resource(lookup = "java:jboss/ee/concurrency/executor/default")
    private ManagedExecutorService service;


    public ReportsResource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        // externalise these properties

        try {
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/concurrency");
            dataSource.setDriverClass("com.mysql.cj.jdbc.driver");
            dataSource.setUser("root");
            dataSource.setPassword("root");
            dao = new BankAccountDao(dataSource);
        } catch (PropertyVetoException e) {
            Logger.getLogger(ReportsResource.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @GET
    public String generateReports() {
        List<BankAccount> accountList = dao.getAllBankAccounts();

        accountList.forEach(account -> {
            Future<Boolean> future = service.submit(new ReportProcessor(account, dao));
            try {
                System.out.println("report generated? " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                Logger.getLogger(ReportsResource.class.getName()).log(Level.SEVERE, null, e);
            }
        });
        return "Report generation task submitted";
    }


}


