package via.android.maria.first.easypay.model;

public class Recipient {
    private String accountNumber;
    private String sortCode;
    private String ownerName;

    public Recipient(String accountNumber, String sortCode, String ownerName) {
        this.accountNumber = accountNumber;
        this.sortCode = sortCode;
        this.ownerName = ownerName;
    }

    public Recipient() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
