package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import via.android.maria.first.easypay.model.Account;
import via.android.maria.first.easypay.service.account.service.AccountService;
import via.android.maria.first.easypay.service.account.service.AccountServiceImpl;

public class AccountViewModel extends AndroidViewModel {
    private MutableLiveData<Account> account;
    private AccountService accountService;

    public AccountViewModel(Application application) {
        super(application);
        accountService = new AccountServiceImpl();
    }

    public void init() {
        account = accountService.getSenderAccount();
    }

    public LiveData<Account> getAccount() {
        return account;
    }

}
