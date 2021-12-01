package via.android.maria.first.easypay.repository.transaction.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.networking.account.api.TransactionResponseApi;
import via.android.maria.first.easypay.repository.FirestoreCallback;
import via.android.maria.first.easypay.utils.Constants;

public class TransactionRepositoryImpl implements TransactionRepository {
    private static TransactionRepositoryImpl instance;
    private FirebaseFirestore database;
    private MutableLiveData<List<Transaction>> list;
    private FirebaseAuth firebaseAuth;
    private TransactionResponseApi transactionResponseApi;

    private TransactionRepositoryImpl() {
        list = new MutableLiveData<>();
        database = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        transactionResponseApi = new TransactionResponseApi();
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
    public MutableLiveData<List<Transaction>> getTransactions(FirestoreCallback<List<Transaction>> callback) {
        database.collection("users").document(Constants.USER_SENDER_DOC_ID)
                .collection("account").document(Constants.ACCOUNT_SENDER_DOC_ID)
                .collection("transactions")
                .get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot document = task.getResult();
                if (document != null) {
                    List<Transaction> list = document.toObjects(Transaction.class);
                    callback.onCallback(list);
                }
            } else {
                Log.w(TAG, "Error getting documents.", task.getException());
            }
        });

        return list;
    }

    @Override
    public void addTransactionList() {
        List<Transaction> apiData = transactionResponseApi.getApiData();
        for (Transaction transaction : apiData) {
            database.collection("users")
                    .document(Constants.USER_SENDER_DOC_ID)
                    .collection("account")
                    .document(Constants.ACCOUNT_SENDER_DOC_ID).collection("transactions")
                    .add(transaction).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w(TAG, "Error adding document", e);
                }
            });
        }
    }
}
