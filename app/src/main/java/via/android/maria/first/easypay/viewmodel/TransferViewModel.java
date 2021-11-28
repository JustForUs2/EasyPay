package via.android.maria.first.easypay.viewmodel;

import static java.lang.Double.parseDouble;

import android.app.Application;


import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import via.android.maria.first.easypay.model.Account;
import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.repository.account.repository.AccountRepository;
import via.android.maria.first.easypay.repository.account.repository.AccountRepositoryImpl;
import via.android.maria.first.easypay.repository.transaction.repository.TransactionRepository;
import via.android.maria.first.easypay.repository.transaction.repository.TransactionRepositoryImpl;

public class TransferViewModel extends AndroidViewModel {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;


    public TransferViewModel(Application application) {
        super(application);
        transactionRepository = TransactionRepositoryImpl.getInstance();
        accountRepository = AccountRepositoryImpl.getInstance();
    }

    public void updateBalanceAfterTransaction(Transaction transaction) {

        LiveData<Account> account = accountRepository.getSenderBalance();

        double balance = Double.parseDouble(account.getValue().getBalance());
        double amount = Double.parseDouble(transaction.getAmount());

        double extractAmount = balance - amount;
        String  newBalance = String.valueOf(extractAmount);
        accountRepository.updateBalanceAfterTransaction(newBalance);
    }
}

