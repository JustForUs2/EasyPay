package via.android.maria.first.easypay.model;

public class User {
    private String uid;
    private String name;
    private Account account;

    public User(String uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public User(String name, Account account) {
        this.name = name;
        this.account = account;
    }

    public User() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
