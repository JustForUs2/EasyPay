package via.android.maria.first.easypay.repository.account.repository;

import via.android.maria.first.easypay.model.Account;
import via.android.maria.first.easypay.repository.FirestoreCallback;

public interface AccountRepository {
    void updateBalanceAfterTransaction(String newSenderBalance);
    void updateReceiverAfterTransaction(String newBalance);
    void readDataAccountReceiver(FirestoreCallback<Account> firestoreCallback);
    void readDataAccountSender(FirestoreCallback<Account> firestoreCallback);
}
