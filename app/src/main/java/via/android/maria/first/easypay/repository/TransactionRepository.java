package via.android.maria.first.easypay.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import via.android.maria.first.easypay.model.Transaction;

public class TransactionRepository {
    private static TransactionRepository instance;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();

    public TransactionRepository() {
    }

    public static TransactionRepository getInstance() {
        if (instance == null)
            instance = new TransactionRepository();
        return instance;
    }

    // TODO to be changed with make a transaction
    public void addTransactionToAccount(Transaction transaction, String accountId) {
        database.collection("account").document()
                .collection("transactions").add(transaction).
        addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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
        ;
    }
}
