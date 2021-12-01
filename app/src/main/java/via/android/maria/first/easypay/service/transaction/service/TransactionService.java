package via.android.maria.first.easypay.service.transaction.service;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import via.android.maria.first.easypay.model.Transaction;

public interface TransactionService {
    void completeTransaction(Transaction transaction);
    MutableLiveData<List<Transaction>> getTransactions();
    void addTransactionList();
}
