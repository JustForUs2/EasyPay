package via.android.maria.first.easypay.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import via.android.maria.first.easypay.model.Recipient;
import via.android.maria.first.easypay.utils.Constants;

public class RecipientRepositoryImpl implements RecipientRepository {
    private static RecipientRepositoryImpl instance;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();

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
}
