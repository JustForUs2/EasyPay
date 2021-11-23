package via.android.maria.first.easypay.model;

import com.google.firebase.firestore.DocumentId;

import java.util.ArrayList;
import java.util.List;

public class Account {
    @DocumentId
    private String accountId;
    private double balance;
    private String accountNumber;
    private List<Transaction> transactions;

    public Account() {}

    public Account(String accountId, double balance, String accountNumber, List<Transaction> transactions) {
        this.accountId = accountId;
        this.balance = balance;
        this.accountNumber = accountNumber;
        transactions = new ArrayList<>();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
