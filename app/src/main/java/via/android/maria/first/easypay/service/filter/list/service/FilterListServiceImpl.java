package via.android.maria.first.easypay.service.filter.list.service;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.repository.filter.list.repository.FilterListRepository;
import via.android.maria.first.easypay.repository.filter.list.repository.FilterListRepositoryImpl;

public class FilterListServiceImpl implements FilterListService{
    private final FilterListRepository filterListRepository;

    public FilterListServiceImpl() {
        filterListRepository = FilterListRepositoryImpl.getInstance();
    }

    @Override
    public MutableLiveData<List<Transaction>> getTransactions() {
        MutableLiveData<List<Transaction>> transactions = new MutableLiveData<>();
        filterListRepository.getTransactions(transactions :: setValue);
        return transactions;
    }
}
