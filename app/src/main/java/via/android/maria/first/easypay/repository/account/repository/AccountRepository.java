package via.android.maria.first.easypay.repository.account.repository;

import androidx.lifecycle.MutableLiveData;

import via.android.maria.first.easypay.model.Account;
import via.android.maria.first.easypay.repository.FirestoreCallback;

public interface AccountRepository {
    MutableLiveData<Account> getSenderBalance();
    MutableLiveData<Account> getReceiverBalance();
    void updateBalanceAfterTransaction(String newSenderBalance);
    void updateReceiverAfterTransaction(String newBalance);
    void readDataAccountReceiver(FirestoreCallback<Account> firestoreCallback);
}
