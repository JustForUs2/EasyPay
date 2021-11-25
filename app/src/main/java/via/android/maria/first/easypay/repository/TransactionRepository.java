package via.android.maria.first.easypay.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

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
    }

    public List<Transaction> getTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        CollectionReference collection = database.collection("account").document("transaction-id-here").collection("transactions");

        collection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });

        return transactions;
    }
}
