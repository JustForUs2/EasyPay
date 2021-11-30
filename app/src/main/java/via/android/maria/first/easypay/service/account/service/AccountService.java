package via.android.maria.first.easypay.service.account.service;

import androidx.lifecycle.MutableLiveData;

import via.android.maria.first.easypay.model.Account;

public interface AccountService {
    MutableLiveData<Account> getSenderAccount();
}
