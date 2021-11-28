package via.android.maria.first.easypay.repository.transaction.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import via.android.maria.first.easypay.model.Account;
import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.utils.Constants;

public class TransactionRepositoryImpl implements TransactionRepository {
    private static TransactionRepositoryImpl instance;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private MutableLiveData<List<Transaction>> list = new MutableLiveData<>();
    private FirebaseAuth firebaseAuth;

    public TransactionRepositoryImpl() {
    }

    public static TransactionRepositoryImpl getInstance() {
        if (instance == null)
            instance = new TransactionRepositoryImpl();
        return instance;
    }

    @Override
    public void addTransactionToSenderAccount(Transaction transaction) {

        database.collection("users").document(Constants.USER_SENDER_DOC_ID)
                .collection("account").document(Constants.ACCOUNT_SENDER_DOC_ID)
                .collection("transactions").add(transaction)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    @Override
    public void addTransactionToReceiverAccount(Transaction transaction) {

        database.collection("users").document(Constants.USER_RECEIVER_DOC_ID)
                .collection("account").document(Constants.ACCOUNT_RECEIVER_DOC_ID)
                .collection("transactions").add(transaction)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    @Override
    public MutableLiveData<List<Transaction>> getTransactions() {
        database.collection("users").document(Constants.USER_SENDER_DOC_ID)
                .collection("account").document(Constants.ACCOUNT_SENDER_DOC_ID)
                .collection("transactions")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot document = task.getResult();
                    if (document != null) {
                        list.setValue(document.toObjects(Transaction.class));
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });

        return list;
    }

    private FirebaseUser getCurrentUser() {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        return currentUser;
    }
}
