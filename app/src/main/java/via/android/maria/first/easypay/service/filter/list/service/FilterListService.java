package via.android.maria.first.easypay.service.filter.list.service;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import via.android.maria.first.easypay.model.Transaction;

public interface FilterListService {
    MutableLiveData<List<Transaction>> getTransactions();
}
