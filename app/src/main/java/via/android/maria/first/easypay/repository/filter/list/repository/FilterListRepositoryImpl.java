package via.android.maria.first.easypay.repository.filter.list.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.repository.FirestoreCallback;
import via.android.maria.first.easypay.utils.Constants;

public class FilterListRepositoryImpl implements FilterListRepository{
    private static FilterListRepositoryImpl instance;
    private FirebaseFirestore database;
    private MutableLiveData<List<Transaction>> list;

    private FilterListRepositoryImpl() {
        list = new MutableLiveData<>();
        database = FirebaseFirestore.getInstance();
    }

    public static FilterListRepositoryImpl getInstance() {
        if (instance == null)
            instance = new FilterListRepositoryImpl();
        return instance;
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
                    Log.d(TAG, "filter list");
                }
            } else {
                Log.w(TAG, "Error getting documents.", task.getException());
            }
        });

        return list;
    }
}
