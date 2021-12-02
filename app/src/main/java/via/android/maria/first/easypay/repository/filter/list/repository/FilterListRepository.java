package via.android.maria.first.easypay.repository.filter.list.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.repository.FirestoreCallback;

public interface FilterListRepository {
    MutableLiveData<List<Transaction>> getTransactions(FirestoreCallback<List<Transaction>> callback);
}
