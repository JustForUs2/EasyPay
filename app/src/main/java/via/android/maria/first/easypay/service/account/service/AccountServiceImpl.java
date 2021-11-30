package via.android.maria.first.easypay.service.account.service;

import androidx.lifecycle.MutableLiveData;

import via.android.maria.first.easypay.model.Account;
import via.android.maria.first.easypay.repository.account.repository.AccountRepository;
import via.android.maria.first.easypay.repository.account.repository.AccountRepositoryImpl;

public class AccountServiceImpl implements AccountService{
    private final AccountRepository repository;
    private MutableLiveData<Account> accountMutableLiveData;

    public AccountServiceImpl() {
        this.repository = AccountRepositoryImpl.getInstance();
        accountMutableLiveData = new MutableLiveData<>();
    }

    @Override
    public MutableLiveData<Account> getSenderAccount() {
        repository.readDataAccountSender((account) -> {
            accountMutableLiveData.setValue(account);
        });
        return accountMutableLiveData;
    }
}
