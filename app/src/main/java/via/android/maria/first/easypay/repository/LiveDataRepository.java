package via.android.maria.first.easypay.repository;

import java.util.List;

import via.android.maria.first.easypay.model.Transaction;

public interface LiveDataRepository {
    void showAllTransactionList(List<Transaction> allTransactions);
}
