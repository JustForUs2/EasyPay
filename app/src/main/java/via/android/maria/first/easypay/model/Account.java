package via.android.maria.first.easypay.model;


import java.util.List;

public class Account {
    private String balance;
    private String accountNumber;
    private List<Transaction> transactions;
    private List<Recipient> recipients;

    public Account(String balance, String accountNumber, List<Transaction> transactions, List<Recipient> recipientsList) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.transactions = transactions;
        this.recipients = recipientsList;
    }

    public Account() {
    }

    public Account(String balance, String accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
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

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
    }


}
