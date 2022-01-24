package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import via.android.maria.first.easypay.model.Loan;
import via.android.maria.first.easypay.service.loan.service.LoanService;
import via.android.maria.first.easypay.service.loan.service.LoanServiceImpl;

public class LoanViewModel extends AndroidViewModel {
    private MutableLiveData<List<Loan>> loans;
    private LoanService loanService;

    public LoanViewModel(@NonNull Application application) {
        super(application);
        loanService = new LoanServiceImpl();
    }

    public void init() {
        loans = loanService.getLoans();
    }

    public LiveData<List<Loan>> getLoans() {
        return loans;
    }
}
