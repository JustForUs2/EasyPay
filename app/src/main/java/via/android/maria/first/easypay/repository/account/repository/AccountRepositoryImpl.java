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
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private MutableLiveData<Account> senderAccount = new MutableLiveData<>();
    private MutableLiveData<Account> receiverAccount = new MutableLiveData<>();


    Map<String, Object> senderBalanceData = new HashMap<>();
    Map<String, Object> receiverBalanceData = new HashMap<>();
    Map<String, Object> senderAccountData = new HashMap<>();


    public AccountRepositoryImpl() {
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
                        //updateBalanceAfterTransaction((String) senderBalanceData.get("balance"));
                        Log.d(TAG, "result" + senderBalanceData);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                }
            }
        });
        String balance = (String) senderBalanceData.get("balance");
        Account account = new Account();
        account.setBalance(balance);
        senderAccount.setValue(account);
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
                        Log.d(TAG, "DocumentSnapshot data: " + result.getData());
                        receiverBalanceData = result.getData();
                        Log.d(TAG, "result" + receiverBalanceData);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                }
            }
        });
        String balance = (String) receiverBalanceData.get("balance");
        String accountNumber = (String) receiverBalanceData.get("accountNumber");
        Account account = new Account();
        account.setBalance(balance);
        account.setAccountNumber(accountNumber);
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
    public String getSenderAccount() {
        database.collection("users").document("Me0fwbU1rtGyKN2Xequw")
                .collection("account").document("2HGHb6mOJ2HbCFBseI7i")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot result = task.getResult();
                    if (result.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + result.getData());
                        senderAccountData = result.getData();
                        Log.d(TAG, "result" + receiverBalanceData);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                }
            }
        });
        String balance = (String) senderAccountData.get("balance");
        Account account = new Account();
        account.setBalance(balance);
        return account.getBalance();
    }
}

