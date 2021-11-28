package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import via.android.maria.first.easypay.model.Account;
import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.repository.account.repository.AccountRepository;
import via.android.maria.first.easypay.repository.account.repository.AccountRepositoryImpl;

public class TransferViewModel extends AndroidViewModel {
    private final AccountRepository accountRepository;


    public TransferViewModel(Application application) {
        super(application);
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

    public void updateReceiverBalanceAfterTransaction(Transaction transaction) {
        LiveData<Account> receiverAccount = accountRepository.getReceiverBalance();

        double balance = Double.parseDouble(receiverAccount.getValue().getBalance());
        double amount = Double.parseDouble(transaction.getAmount());

        double addAmountToBalance = balance + amount;
        String newBalance = String.valueOf(addAmountToBalance);

        accountRepository.updateReceiverAfterTransaction(newBalance);
    }
}

