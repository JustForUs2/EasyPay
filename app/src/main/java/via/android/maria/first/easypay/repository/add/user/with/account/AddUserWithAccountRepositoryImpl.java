package via.android.maria.first.easypay.repository.add.user.with.account;

import com.google.firebase.firestore.FirebaseFirestore;

import via.android.maria.first.easypay.model.User;

public class AddUserWithAccountRepositoryImpl implements AddUserWithAccountRepository {
    private static AddUserWithAccountRepositoryImpl instance;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();

    public AddUserWithAccountRepositoryImpl() {
    }

    public static AddUserWithAccountRepositoryImpl getInstance() {
        if (instance == null)
            instance = new AddUserWithAccountRepositoryImpl();
        return instance;
    }

    @Override
    public void addUserWithAccount(User user) {


/* -------> 1
        database.collection("users").add(new User("bCjXtV31R0TXvraV6fvsaBlgARk2", "Mark")).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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

 */

        /* -------> 2

        database.collection("users").document("E5ZNdWn1xZW9ELmhVKPx").collection("account")
                .add(new Account("92.355", "092711694")).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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
*/


/* ---> 3
        database.collection("users").document("E5ZNdWn1xZW9ELmhVKPx").collection("account")
                .document("bVMiJ8CWPXZICQrLgNi2").collection("transactions").add(new Transaction()).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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

 */


/* --->4

        database.collection("users").document("E5ZNdWn1xZW9ELmhVKPx").collection("account")
                .document("bVMiJ8CWPXZICQrLgNi2").collection("recipients").add(new Recipient()).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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

*/

    }
}
