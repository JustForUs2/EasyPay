package via.android.maria.first.easypay.repository.transaction;

import via.android.maria.first.easypay.model.Transaction;

public interface TransactionRepository {
    void getAllTransactions();

    void beginNewTransaction(Transaction transaction);

    void cancelTransaction(Transaction transaction);

    void deleteTransaction(String transactionId);
}
