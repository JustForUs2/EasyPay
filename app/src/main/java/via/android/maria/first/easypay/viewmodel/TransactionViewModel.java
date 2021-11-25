package via.android.maria.first.easypay.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.List;

import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.repository.TransactionRepository;

// TODO check if there was another view model
public class TransactionViewModel extends ViewModel {
    private final TransactionRepository transactionRepository;

    public TransactionViewModel() {
        transactionRepository = TransactionRepository.getInstance();
    }

    public void addTransaction(Transaction transaction, String accountId) {
        transactionRepository.addTransactionToAccount(transaction, accountId);
    }

    public List<Transaction> getTransactions()
    {
        List<Transaction> transactions = transactionRepository.getTransactions();
        return transactions;
    }

}
