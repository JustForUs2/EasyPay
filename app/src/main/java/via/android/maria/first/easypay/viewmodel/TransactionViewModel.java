package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.repository.TransactionRepository;

// TODO check if there was another view model
public class TransactionViewModel extends AndroidViewModel {
    private final TransactionRepository transactionRepository;
    private LiveData<List<Transaction>> transactions;

    public TransactionViewModel(Application application) {
        super(application);
        transactionRepository = TransactionRepository.getInstance();
    }

    public void addTransaction(Transaction transaction, String accountId) {
        transactionRepository.addTransactionToAccount(transaction, accountId);
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
