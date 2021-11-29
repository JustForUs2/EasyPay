package via.android.maria.first.easypay.model;


import com.google.firebase.firestore.PropertyName;

public class Transaction {
    private String accountNumber;
    private String amount;
    private String sortCode;
    private String transferName;
    private String description;
    private String type; // withdrawal or deposit

    public Transaction(String amount, String sortCode, String accountNumber, String transferName, String description, String type) {
        this.amount = amount;
        this.sortCode = sortCode;
        this.accountNumber = accountNumber;
        this.transferName = transferName;
        this.description = description;
        this.type = type;
    }

    public Transaction() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @PropertyName("amount")
    public String getAmount() {
        return amount;
    }

    @PropertyName("amount")
    public void setAmount(String amount) {
        this.amount = amount;
    }

    @PropertyName("sortCode")
    public String getSortCode() {
        return sortCode;
    }

    @PropertyName("sortCode")
    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    @PropertyName("accountNumber")
    public String getAccountNumber() {
        return accountNumber;
    }

    @PropertyName("accountNumber")
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @PropertyName("transferName")
    public String getTransferName() {
        return transferName;
    }

    @PropertyName("transferName")
    public void setTransferName(String transferName) {
        this.transferName = transferName;
    }

    @PropertyName("description")
    public String getDescription() {
        return description;
    }

    @PropertyName("description")
    public void setDescription(String description) {
        this.description = description;
    }
}




