package via.android.maria.first.easypay.networking.loan.api;

import java.util.ArrayList;
import java.util.List;

import via.android.maria.first.easypay.model.Loan;

public class LoanResponse {
    private List<Loan> loans;

    public LoanResponse() {
        loans = new ArrayList<>();
    }

    public List<Loan> getLoans() {
        return loans;
    }
}
