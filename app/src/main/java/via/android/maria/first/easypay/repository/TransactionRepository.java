package via.android.maria.first.easypay.repository;

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

// TODO create an interface
public class TransactionRepository {
    private static TransactionRepository instance;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private MutableLiveData<List<Transaction>> list = new MutableLiveData<>();
    private FirebaseAuth firebaseAuth;
    private Account senderAccount;


    public TransactionRepository() {
    }

    public static TransactionRepository getInstance() {
        if (instance == null)
            instance = new TransactionRepository();
        return instance;
    }

    // ################### create transaction
    // 1) Alex balance
    public void getSenderBalance()
    {
        DocumentReference docRef = database.collection("accounts")
                .document(Constants.ACCOUNT_SENDER_DOC_ID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot = task.getResult();
                 senderAccount = documentSnapshot.toObject(Account.class);
            }
        });
        Log.d(TAG, senderAccount.getBalance());
    }
    // 2) Substract amount to send from Alex balance
    // 3) Overwrite new balance of Alex to Db
    // 4) Write new transaction to Alex's list with description, name sent to and - amount


    // 1) Mark balance
    // 2) add amount to Mark balance
    // 3) Overwrite Mark's balance
    // 4) Write a new transaction to lists of trans of Mark - name of who send, amount received, description


    public void addTransactionToAccount(Transaction transaction) {
        FirebaseUser firebaseUser = getCurrentUser();

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

    // TODO change harcoded ID of user when Auth and Firestore synced
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

                /*
        database.collection("account").document("transaction-id-here").collection("transactions")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
         */
    }

    private FirebaseUser getCurrentUser() {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        return currentUser;
    }
}
