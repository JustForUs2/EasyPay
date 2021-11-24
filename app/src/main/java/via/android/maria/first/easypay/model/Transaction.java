package via.android.maria.first.easypay.model;

public class Transaction {
    private String amount, sortCode, accountNumber, transferName;
    private String description;

    public Transaction(String amount, String sortCode, String accountNumber, String transferName, String description) {
        this.amount = amount;
        this.sortCode = sortCode;
        this.accountNumber = accountNumber;
        this.transferName = transferName;
        this.description = description;
    }

    public Transaction() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransferName() {
        return transferName;
    }

    public void setTransferName(String transferName) {
        this.transferName = transferName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}




