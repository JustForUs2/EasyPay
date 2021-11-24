package via.android.maria.first.easypay.model;

public class Transaction {
    private String transferId;
    private String description;
    private String account;
    private double amount;
    private double balance;

    public Transaction(String transferId, String description, String account, double amount, double balance) {
        this.transferId = transferId;
        this.description = description;
        this.account = account;
        this.amount = amount;
        this.balance = balance;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
