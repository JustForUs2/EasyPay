package via.android.maria.first.easypay.service.loan.service;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import via.android.maria.first.easypay.model.Loan;

public interface LoanService {
    void addLoans();
    MutableLiveData<List<Loan>> getLoans();
}
