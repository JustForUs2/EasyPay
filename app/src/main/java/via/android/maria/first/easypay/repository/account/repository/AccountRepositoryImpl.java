package via.android.maria.first.easypay.repository.account.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import via.android.maria.first.easypay.model.Account;
import via.android.maria.first.easypay.utils.Constants;

public class AccountRepositoryImpl implements AccountRepository {
    private static AccountRepositoryImpl instance;
    private FirebaseFirestore database;
    private MutableLiveData<Account> senderAccount;
    private MutableLiveData<Account> receiverAccount;
    private Map<String, Object> senderBalanceData;
    private Map<String, Object> receiverBalanceData;

    private AccountRepositoryImpl() {
        senderBalanceData = new HashMap<>();
        receiverBalanceData = new HashMap<>();
        senderAccount = new MutableLiveData<>();
        receiverAccount = new MutableLiveData<>();
        database = FirebaseFirestore.getInstance();
    }

    public static AccountRepositoryImpl getInstance() {
        if (instance == null)
            instance = new AccountRepositoryImpl();
        return instance;
    }

    @Override
    public MutableLiveData<Account> getSenderBalance() {
        database.collection("users")
                .document(Constants.USER_SENDER_DOC_ID)
                .collection("account")
                .document(Constants.ACCOUNT_SENDER_DOC_ID)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot result = task.getResult();
                    if (result.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + result.getData());
                        senderBalanceData = result.getData();
                        // TODO should happen inside the call back
                        String balance = (String) senderBalanceData.get("balance");
                        Account account = new Account();
                        account.setBalance(balance);
                        senderAccount.setValue(account);
                        Log.d(TAG, "result" + senderBalanceData);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                }
            }
        });
        return senderAccount;
    }


    @Override
    public MutableLiveData<Account> getReceiverBalance() {
        database.collection("users").document(Constants.USER_RECEIVER_DOC_ID)
                .collection("account").document(Constants.ACCOUNT_RECEIVER_DOC_ID)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot result = task.getResult();
                    if (result.exists()) {
                        Log.d(TAG, "DocumentSnapshot data receiver: " + result.getData());
                        receiverBalanceData = result.getData();
                        Log.d(TAG, "result receiver" + receiverBalanceData);
                    } else {
                        Log.d(TAG, "No such document receiver");
                    }
                }
            }
        });
        String balance = (String) receiverBalanceData.get("balance");
        Account account = new Account();
        account.setBalance(balance);
        receiverAccount.setValue(account);
        return receiverAccount;
    }

    @Override
    public void updateBalanceAfterTransaction(String newSenderBalance) {
        Map<String, Object> newBalance = new HashMap<>();
        newBalance.put("balance", newSenderBalance);

        database.collection("users")
                .document(Constants.USER_SENDER_DOC_ID)
                .collection("account")
                .document(Constants.ACCOUNT_SENDER_DOC_ID)
                .update(newBalance)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "Updated!");
                    }
                });
    }

    @Override
    public void updateReceiverAfterTransaction(String newBalance) {
        Map<String, Object> balance = new HashMap<>();
        balance.put("balance", newBalance);
        database.collection("users")
                .document(Constants.USER_RECEIVER_DOC_ID)
                .collection("account")
                .document(Constants.ACCOUNT_RECEIVER_DOC_ID)
                .update(balance)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "Updated!");
                    }
                });
    }

    public void readDataAccountReceiver(FirestoreCallback firestoreCallback)
    {
        database.collection("users").document(Constants.USER_RECEIVER_DOC_ID)
                .collection("account").document(Constants.ACCOUNT_RECEIVER_DOC_ID)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot result = task.getResult();
                        if (result.exists()) {
                            Log.d(TAG, "DocumentSnapshot data receiver: " + result.getData());
                            receiverBalanceData = result.getData();
                            String balance = (String) receiverBalanceData.get("balance");
                            Account account = new Account();
                            account.setBalance(balance);
                            receiverAccount.setValue(account);
                            firestoreCallback.onCallback(account);
                            Log.d(TAG, "result receiver" + receiverBalanceData);
                        } else {
                            Log.d(TAG, "No such document receiver");
                        }
                    }
                });
    }

    public interface FirestoreCallback {
        void onCallback(Account account);
    }
}


