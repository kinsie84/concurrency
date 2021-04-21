package com.kinsella.dao;

import com.kinsella.bean.BankAccount;
import com.kinsella.bean.BankAccountTransaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankAccountDao {

    private final DataSource dataSource;

    public BankAccountDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<BankAccount> getAllBankAccounts() {
        Connection connection = null;
        List<BankAccount> accounts = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            BankAccount account = null;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from bank_account");
            while (resultSet.next()) {
                account = new BankAccount();
                account.setAccNumber(resultSet.getInt("acc_number"));
                account.setName(resultSet.getString("acc_holder_name"));
                account.setEmail(resultSet.getString("acc_email"));
                account.setAccType(resultSet.getString("acc_type"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            Logger.getLogger(BankAccountDao.class.getName()).log(Level.SEVERE,null,e);
        }
        return accounts;
    }

    public List<BankAccountTransaction> getTransactionsForAccount(BankAccount account){
        Connection connection = null;
        List<BankAccountTransaction> transactions = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            BankAccountTransaction transaction = null;
            PreparedStatement statement = connection.prepareStatement("select * from bank_account_transaction where acc_number=?");
            statement.setInt(1,account.getAccNumber());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                transaction = new BankAccountTransaction();
                transaction.setAccNumber(resultSet.getInt("acc_number"));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setTxType(resultSet.getString("transaction_type"));
                transaction.setTxId(resultSet.getInt("tx_id"));
                transaction.setTxDate(new Date(resultSet.getDate("transaction_date").getTime()));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            Logger.getLogger(BankAccountDao.class.getName()).log(Level.SEVERE,null,e);
        }
        return transactions;
    }
}
