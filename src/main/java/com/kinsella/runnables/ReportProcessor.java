package com.kinsella.runnables;

import com.kinsella.bean.BankAccount;
import com.kinsella.bean.BankAccountTransaction;
import com.kinsella.dao.BankAccountDao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportProcessor implements Callable<Boolean> {

    private final BankAccount bankAccount;
    private final BankAccountDao bankAccountDao;


    public ReportProcessor(BankAccount bankAccount, BankAccountDao bankAccountDao) {
        this.bankAccount = bankAccount;
        this.bankAccountDao = bankAccountDao;
    }

    @Override
    public Boolean call() throws Exception {
        boolean reportGenerated = false;
        List<BankAccountTransaction> transactions = bankAccountDao.getTransactionsForAccount(bankAccount);
        File file = new File("/Users/pete/Projects/concurrency/out/reports/" + bankAccount.getAccNumber() + "_tx_report.txt");
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (BankAccountTransaction transaction : transactions) {

                writer.write("Account Number: " + transaction.getAccNumber());
                writer.write("Transaction Type: " + transaction.getTxType());
                writer.write("Transaction ID: " + transaction.getTxId());
                writer.write("Transaction Date: " + transaction.getTxDate());
                writer.write("Transaction Amount: " + transaction.getAmount());
                writer.newLine();
            }
            writer.flush();
        }
        reportGenerated = true;
        return reportGenerated;
    }
}
