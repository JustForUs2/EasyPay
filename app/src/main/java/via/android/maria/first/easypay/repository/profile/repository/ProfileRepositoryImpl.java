package via.android.maria.first.easypay.repository.profile.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import via.android.maria.first.easypay.model.User;
import via.android.maria.first.easypay.repository.FirestoreCallback;
import via.android.maria.first.easypay.utils.Constants;

public class ProfileRepositoryImpl implements ProfileRepository {
    private static ProfileRepositoryImpl instance;
    private FirebaseFirestore database;
    private MutableLiveData<User> user;

    private ProfileRepositoryImpl() {
        user = new MutableLiveData<>();
        database = FirebaseFirestore.getInstance();
    }

    public static ProfileRepositoryImpl getInstance() {
        if (instance == null)
            instance = new ProfileRepositoryImpl();
        return instance;
    }

    @Override
    public void getUser(FirestoreCallback<User> callback) {
        database.collection("users")
                .document(Constants.USER_SENDER_DOC_ID)
                .get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot result = task.getResult();
                if (result.exists()) {
                    Log.d(TAG, "DocumentSnapshot data: " + result.getData());
                    Map<String, Object> accountData = result.getData();
                    String name = (String) accountData.get("name");
                    String email = (String) accountData.get("email");
                    String phone = (String) accountData.get("phone");
                    String surname = (String) accountData.get("surname");

                    User user = new User(name, surname,email, phone);

                    callback.onCallback(user);
                    Log.d(TAG, "result" + accountData);
                } else {
                    Log.d(TAG, "No such document");
                }
            }
        });
    }
}
