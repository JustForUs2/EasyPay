package via.android.maria.first.easypay.repository;

import androidx.lifecycle.MutableLiveData;

import via.android.maria.first.easypay.model.Account;

public interface AccountRepository {
    MutableLiveData<Account> getBalance();
}
