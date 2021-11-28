package via.android.maria.first.easypay.repository.transaction.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import via.android.maria.first.easypay.model.Transaction;

public interface TransactionRepository {
    void addTransactionToSenderAccount(Transaction transaction);
    void addTransactionToReceiverAccount(Transaction transaction);
    MutableLiveData<List<Transaction>> getTransactions();
}
