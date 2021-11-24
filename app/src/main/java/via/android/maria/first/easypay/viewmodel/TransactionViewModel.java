package via.android.maria.first.easypay.viewmodel;

import androidx.lifecycle.ViewModel;

import via.android.maria.first.easypay.model.Transaction_02;
import via.android.maria.first.easypay.repository.TransactionRepository;

// TODO check if there was another view model
public class TransactionViewModel extends ViewModel {
    private final TransactionRepository transactionRepository;

    public TransactionViewModel() {
        transactionRepository = TransactionRepository.getInstance();
    }

    public void addTransaction(Transaction_02 transaction, String accountId)
    {
        transactionRepository.addTransactionToAccount(transaction, accountId);
    }

}
