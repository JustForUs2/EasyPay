package via.android.maria.first.easypay.repository.account.repository;

import androidx.lifecycle.MutableLiveData;

import via.android.maria.first.easypay.model.Account;

public interface AccountRepository {
    MutableLiveData<Account> getSenderBalance();
    MutableLiveData<Account> getReceiverBalance();
    void updateBalanceAfterTransaction(String newSenderBalance);
    void updateReceiverAfterTransaction(String newBalance);
    void readDataAccountReceiver(AccountRepositoryImpl.FirestoreCallback firestoreCallback);
}
