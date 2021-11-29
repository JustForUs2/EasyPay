package via.android.maria.first.easypay.model;

public class User {
    private String uid;
    private String name;
    private String surname;
    private Account account;
    private String email;
    private String phone;

    public User(String uid) {
        this.uid = uid;
    }

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

    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
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
