package via.android.maria.first.easypay.service.loan.service;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import via.android.maria.first.easypay.model.Loan;
import via.android.maria.first.easypay.repository.lloan.repository.LoanRepository;
import via.android.maria.first.easypay.repository.lloan.repository.LoanRepositoryImpl;

public class LoanServiceImpl implements LoanService{
    private final LoanRepository loanRepository;

    public LoanServiceImpl() {
        loanRepository = LoanRepositoryImpl.getInstance();
    }

    @Override
    public void addLoans() {
        loanRepository.addLoansToDB();
    }

    @Override
    public MutableLiveData<List<Loan>> getLoans() {
        MutableLiveData<List<Loan>> loans = new MutableLiveData<>();
        loanRepository.getLoans(value -> loans.setValue(value));
        return loans;
    }
}
