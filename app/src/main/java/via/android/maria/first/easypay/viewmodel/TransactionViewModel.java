package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.service.TransactionService;
import via.android.maria.first.easypay.service.TransactionServiceImpl;

public class TransactionViewModel extends AndroidViewModel {
    private final TransactionService transactionService;
    private MutableLiveData<List<Transaction>> transactions;


    public TransactionViewModel(Application application) {
        super(application);
        transactionService = new TransactionServiceImpl();
    }

    // TODO come back and place service
    public void addTransaction(Transaction transaction) {
        transactionService.completeTransaction(transaction);
    }

    public void init()
    {
        this.transactions = transactionService.getTransactions();
    }

    public LiveData<List<Transaction>> getTransactions()
    {
        return transactions;
    }

}
