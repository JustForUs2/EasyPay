package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import via.android.maria.first.easypay.model.Account;
import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.repository.transaction.repository.TransactionRepository;
import via.android.maria.first.easypay.repository.transaction.repository.TransactionRepositoryImpl;

public class TransactionViewModel extends AndroidViewModel {
    private final TransactionRepository transactionRepository;
    private MutableLiveData<List<Transaction>> transactions;


    public TransactionViewModel(Application application) {
        super(application);
        transactionRepository = TransactionRepositoryImpl.getInstance();
    }

    public void addTransaction(Transaction transaction) {
        transactionRepository.addTransactionToAccount(transaction);
    }

    public void init()
    {
        this.transactions = transactionRepository.getTransactions();
    }

    public LiveData<List<Transaction>> getTransactions()
    {
        return transactions;
    }

}
