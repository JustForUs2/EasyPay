package via.android.maria.first.easypay.model;

public class Transaction_02 {
    private String amount, sortCode, accountNumber, transferName;

    public Transaction_02(String amount, String sortCode, String accountNumber, String transferName) {
        this.amount = amount;
        this.sortCode = sortCode;
        this.accountNumber = accountNumber;
        this.transferName = transferName;
    }

    public Transaction_02() {
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
}




