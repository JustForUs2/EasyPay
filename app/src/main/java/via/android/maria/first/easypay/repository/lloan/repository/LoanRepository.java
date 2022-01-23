package via.android.maria.first.easypay.repository.lloan.repository;

import java.util.List;

import via.android.maria.first.easypay.model.Loan;
import via.android.maria.first.easypay.repository.FirestoreCallback;

public interface LoanRepository {
    void getLoans(FirestoreCallback<List<Loan>> callback);
    void addLoansToDB();
}
