package via.android.maria.first.easypay.repository.transaction;

import static via.android.maria.first.easypay.utils.Constants.TRANSACTION_COLLECTION;
import static via.android.maria.first.easypay.utils.Constants.TRANSACTION_DOC;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.repository.LiveDataRepository;

public class TransactionRepositoryImpl implements TransactionRepository {

    private static final String TAG = "Error Message";
    private final FirebaseFirestore firestore;
    private LiveDataRepository liveDataRepository;

    public TransactionRepositoryImpl(LiveDataRepository liveDataRepository) {
        this.liveDataRepository = liveDataRepository;
        firestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void getAllTransactions() {
        List<Transaction> getAll = new ArrayList<>();

        firestore.collection(TRANSACTION_COLLECTION).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d : list) {
                    Transaction transaction = d.toObject(Transaction.class);
                    getAll.add(transaction);
                    liveDataRepository.showAllTransactionList(getAll);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Access Firestore Failed!");
            }
        });
    }

    @Override
    public void beginNewTransaction(Transaction transaction) {
        Map<String, Object> saveTransaction = new HashMap<>();

        saveTransaction.put("amount", transaction.getAmount());
        saveTransaction.put("receiverAccount", transaction.getReceiverAccount());
        saveTransaction.put("transactionDate", transaction.getTransactionDate());

        firestore.collection(TRANSACTION_COLLECTION).document(TRANSACTION_DOC).set(saveTransaction)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TRANSACTION_COLLECTION, "Successfully added!");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TRANSACTION_COLLECTION, "Access Firestore Failed!");
            }
        });
    }

    @Override
    public void cancelTransaction(Transaction transaction) {

    }

    @Override
    public void deleteTransaction(String transactionId) {

    }
}
