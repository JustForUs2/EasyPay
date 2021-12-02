package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.service.filter.list.service.FilterListService;
import via.android.maria.first.easypay.service.filter.list.service.FilterListServiceImpl;

public class FilterListViewModel extends AndroidViewModel {
    private final FilterListService filterListService;
    private MutableLiveData<List<Transaction>> transactions;

    public FilterListViewModel(@NonNull Application application) {
        super(application);
        filterListService = new FilterListServiceImpl();
    }
    public void init(){
        transactions = filterListService.getTransactions();
    }
    public LiveData<List<Transaction>> getTransactions(){
        return transactions;
    }
}
