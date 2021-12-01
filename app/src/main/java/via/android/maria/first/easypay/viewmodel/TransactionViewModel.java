package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.service.transaction.service.TransactionService;
import via.android.maria.first.easypay.service.transaction.service.TransactionServiceImpl;

public class TransactionViewModel extends AndroidViewModel {
    private final TransactionService transactionService;
    private MutableLiveData<List<Transaction>> transactions;


    public TransactionViewModel(Application application) {
        super(application);
        transactionService = new TransactionServiceImpl();
    }

    public void addTransaction(Transaction transaction) {
        transactionService.completeTransaction(transaction);
    }

    public void init()
    {
        if (transactions != null)
        {
            return;
        }
       transactions = transactionService.getTransactions();
        //transactionService.addTransactionList();
    }

    public LiveData<List<Transaction>> getTransactions()
    {
        return transactions;
    }

}
