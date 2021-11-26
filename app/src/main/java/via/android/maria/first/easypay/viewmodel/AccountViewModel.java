package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import via.android.maria.first.easypay.model.Account;
import via.android.maria.first.easypay.repository.AccountRepository;
import via.android.maria.first.easypay.repository.AccountRepositoryImpl;

public class AccountViewModel extends AndroidViewModel {
    private MutableLiveData<Account> account;
    private final AccountRepository repository;

    public AccountViewModel(Application application) {
        super(application);
        repository = AccountRepositoryImpl.getInstance();
    }

    public void init()
    {
        this.account = repository.getBalance();
    }

    public LiveData<Account> getBalance()
    {
        return account;
    }
}
