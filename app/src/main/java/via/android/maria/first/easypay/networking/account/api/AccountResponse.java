package via.android.maria.first.easypay.networking.account.api;

import java.util.List;

import via.android.maria.first.easypay.model.Transaction;

public class AccountResponse {
    private List<Transaction> transactions;

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
