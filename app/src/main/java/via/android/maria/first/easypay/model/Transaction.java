package via.android.maria.first.easypay.model;

import com.google.firebase.firestore.DocumentId;

import java.util.Date;

public class Transaction {
    @DocumentId
    private String transactionId;
    private double amount;
    private String receiverAccount;
    private Date transactionDate;

    public Transaction() {}

    public Transaction(String transactionId, double amount, String receiverAccount, Date transactionDate) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.receiverAccount = receiverAccount;
        this.transactionDate = transactionDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}

