package via.android.maria.first.easypay.repository.lloan.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import via.android.maria.first.easypay.model.Loan;
import via.android.maria.first.easypay.networking.loan.api.LoanResponseApi;
import via.android.maria.first.easypay.repository.FirestoreCallback;

public class LoanRepositoryImpl implements LoanRepository {
    private static LoanRepositoryImpl instance;
    private LoanResponseApi loanResponseApi;
    private FirebaseFirestore database;

    private LoanRepositoryImpl() {
        loanResponseApi = new LoanResponseApi();
        database = FirebaseFirestore.getInstance();
    }

    public static LoanRepositoryImpl getInstance() {
        if (instance == null)
            instance = new LoanRepositoryImpl();
        return instance;
    }

    //TODO retrieve all loans from DB
    @Override
    public void getLoans(FirestoreCallback<List<Loan>> callback) {
        database.collection("loans").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                QuerySnapshot result = task.getResult();
                if (result!= null){
                    List<Loan> loans = result.toObjects(Loan.class);
                    callback.onCallback(loans);
                }
            }
            else {
                Log.w(TAG, "Error fetching loans from DB", task.getException());
            }
        });
    }

    @Override
    public void addLoansToDB() {
        List<Loan> loanListApiData = loanResponseApi.getLoanListApiData();
        for (Loan loan : loanListApiData){
            database.collection("loans").add(loan).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d(TAG, "Loans successfully typed in DB");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w(TAG, "Error when adding loans to DB");
                }
            });
        }
    }


}
