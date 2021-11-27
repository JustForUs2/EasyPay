package via.android.maria.first.easypay.repository.recipient.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import via.android.maria.first.easypay.model.Recipient;
import via.android.maria.first.easypay.utils.Constants;

public class RecipientRepositoryImpl implements RecipientRepository {
    private static RecipientRepositoryImpl instance;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private MutableLiveData<List<Recipient>> list = new MutableLiveData<>();

    public RecipientRepositoryImpl() {
    }

    public static RecipientRepositoryImpl getInstance() {
        if (instance == null)
            instance = new RecipientRepositoryImpl();
        return instance;
    }

    @Override
    public void addRecipient(Recipient recipient) {
        database.collection("users").document(Constants.USER_SENDER_DOC_ID)
                .collection("account").document(Constants.ACCOUNT_SENDER_DOC_ID)
                .collection("recipients").add(recipient)
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
    public MutableLiveData<List<Recipient>> getRecipients() {
        database.collection("users").document("Me0fwbU1rtGyKN2Xequw")
                .collection("account").document("2HGHb6mOJ2HbCFBseI7i")
                .collection("recipients")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot document = task.getResult();
                    if (document != null) {
                        list.setValue(document.toObjects(Recipient.class));
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });

        return list;
    }
}
