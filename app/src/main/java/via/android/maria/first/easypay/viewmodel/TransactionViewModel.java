package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.repository.LiveDataRepository;

public class TransactionViewModel extends AndroidViewModel implements LiveDataRepository {

    private final MutableLiveData<List<Transaction>> mutableDataTransaction;


    public TransactionViewModel(Application application) {
        super(application);
        mutableDataTransaction = new MutableLiveData<>();
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return mutableDataTransaction;
    }

    @Override
    public void showAllTransactionList(List<Transaction> allTransactions) {
        mutableDataTransaction.setValue((List<Transaction>) allTransactions);
    }
}
