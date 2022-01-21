package via.android.maria.first.easypay.model;

public class Loan {
    private int id;
    private String type, bank;

    public Loan(int id, String type, String bank) {
        this.id = id;
        this.type = type;
        this.bank = bank;
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
}
