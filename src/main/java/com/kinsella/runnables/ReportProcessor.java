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

    private BankAccount bankAccount;
    private BankAccountDao bankAccountDao;


    public ReportProcessor(BankAccount bankAccount, BankAccountDao bankAccountDao) {
        this.bankAccount = bankAccount;
        this.bankAccountDao = bankAccountDao;
    }

    @Override
    public Boolean call() throws Exception {
        List<BankAccountTransaction> transactions = bankAccountDao.getTransactionsForAccount(bankAccount);
        File file = new File("out/reports/" + bankAccount.getAccNumber() + "_tx_report.txt");
        transactions.forEach(transaction -> {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        writer.write("Account Number: " + transaction.getAccNumber());
                        writer.write("Transaction Type: " + transaction.getTxType());
                        writer.write("Transaction ID: " + transaction.getTxId());
                        writer.write("Transaction Date: " + transaction.getTxDate());
                        writer.write("Transaction Amount: " + transaction.getAmount());
                        writer.newLine();
                        writer.flush();
                    } catch (IOException e) {
                        Logger.getLogger(ReportProcessor.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
        );

        return true;
    }
}
