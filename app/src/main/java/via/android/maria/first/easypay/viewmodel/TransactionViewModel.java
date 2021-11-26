package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.repository.TransactionRepository;

public class TransactionViewModel extends AndroidViewModel {
    private final TransactionRepository transactionRepository;
    private LiveData<List<Transaction>> transactions;
    private FirebaseAuth firebaseAuth;

    public TransactionViewModel(Application application) {
        super(application);
        transactionRepository = TransactionRepository.getInstance();
    }

    public void addTransaction(Transaction transaction) {
        transactionRepository.addTransactionToAccount(transaction, getCurrentUser());
    }

    public void init()
    {
        this.transactions = transactionRepository.getTransactions();
    }

    public LiveData<List<Transaction>> getTransactions()
    {
        return transactions;
    }

    private String getCurrentUser()
    {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        return currentUser.getUid();
    }

}
