package via.android.maria.first.easypay.viewmodel;

import androidx.lifecycle.ViewModel;

import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.repository.TransactionRepository;

// TODO check if there was another view model
public class TransactionViewModel extends ViewModel {
    private TransactionRepository transactionRepository;

    public TransactionViewModel() {
        transactionRepository = TransactionRepository.getInstance();
    }

    public void addTransaction(Transaction transaction, int accountIn)
    {
        transactionRepository.addTransactionToAccount(transaction, accountIn);
    }


}
