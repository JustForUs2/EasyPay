package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import via.android.maria.first.easypay.model.Account;
import via.android.maria.first.easypay.repository.account.repository.AccountRepository;
import via.android.maria.first.easypay.repository.account.repository.AccountRepositoryImpl;

public class AccountViewModel extends AndroidViewModel {
    private MutableLiveData<Account> account;
    private final AccountRepository repository;

    public AccountViewModel(Application application) {
        super(application);
        repository = AccountRepositoryImpl.getInstance();
    }

    public void init() {
        account = repository.getSenderBalance();
    }

    public LiveData<Account> getBalance() {
        return account;
    }

}
