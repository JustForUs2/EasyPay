package via.android.maria.first.easypay.model;

public class Loan {
    private int id;
    private String type, bank, additionalInfo, description;

    public Loan(int id, String type, String bank, String additionalInfo, String description) {
        this.id = id;
        this.type = type;
        this.bank = bank;
        this.additionalInfo = additionalInfo;
        this.description = description;
    }

    public Loan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
