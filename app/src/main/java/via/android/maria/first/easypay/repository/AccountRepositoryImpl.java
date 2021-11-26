package via.android.maria.first.easypay.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import via.android.maria.first.easypay.model.Account;
import via.android.maria.first.easypay.utils.Constants;

public class AccountRepositoryImpl implements AccountRepository{
    private static AccountRepositoryImpl instance;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private MutableLiveData<Account> senderAccount;

    public AccountRepositoryImpl() {
    }

    public static AccountRepositoryImpl getInstance()
    {
        if (instance == null)
            instance = new AccountRepositoryImpl();
        return instance;
    }

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
