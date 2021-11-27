package via.android.maria.first.easypay.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Objects;

import via.android.maria.first.easypay.model.Account;

public class AccountRepositoryImpl implements AccountRepository {
    private static AccountRepositoryImpl instance;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private MutableLiveData<List<Account>> accountList = new MutableLiveData<>();
    private MutableLiveData<Account> senderAccount = new MutableLiveData<>();

    public AccountRepositoryImpl() {
    }

    public static AccountRepositoryImpl getInstance() {
        if (instance == null)
            instance = new AccountRepositoryImpl();
        return instance;
    }

    @Override
    public MutableLiveData<Account> getBalance() {
        database.collection("users").whereEqualTo("uid", Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot document = task.getResult();
                    if (document != null) {
                        accountList.setValue(document.toObjects(Account.class));
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
        senderAccount.setValue(accountList.getValue().get(0));
        return senderAccount;
    }
}

/*
    @Override
    public MutableLiveData<Account> getBalance() {
        DocumentReference docRef = database.collection("users").document(Constants.USER_SENDER_DOC_ID).collection("accounts")
                .document(Constants.ACCOUNT_SENDER_DOC_ID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot = task.getResult();
                senderAccount.setValue(documentSnapshot.toObject(Account.class));
            }
        });

        return senderAccount;
    }
}
 */