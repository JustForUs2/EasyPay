package via.android.maria.first.easypay.service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import via.android.maria.first.easypay.model.Account;
import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.repository.account.repository.AccountRepository;
import via.android.maria.first.easypay.repository.account.repository.AccountRepositoryImpl;
import via.android.maria.first.easypay.repository.transaction.repository.TransactionRepository;
import via.android.maria.first.easypay.repository.transaction.repository.TransactionRepositoryImpl;

public class TransactionServiceImpl implements TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl() {
        accountRepository = AccountRepositoryImpl.getInstance();
        transactionRepository = TransactionRepositoryImpl.getInstance();
    }

    @Override
    public void completeTransaction(Transaction transaction) {
        updateBalanceAfterTransaction(transaction);
        //updateReceiverBalanceAfterTransaction(transaction);
        registerSenderTransaction(transaction);
        registerReceiverTransaction(transaction);
    }

    @Override
    public MutableLiveData<List<Transaction>> getTransactions() {
        MutableLiveData<List<Transaction>> transactions = transactionRepository.getTransactions();
        return transactions;
    }

    private void updateBalanceAfterTransaction(Transaction transaction) {

        LiveData<Account> account = accountRepository.getSenderBalance();

        double balance = Double.parseDouble(account.getValue().getBalance());
        double amount = Double.parseDouble(transaction.getAmount());

        double extractAmount = balance - amount;
        String newBalance = String.valueOf(extractAmount);
        accountRepository.updateBalanceAfterTransaction(newBalance);
    }

    private void updateReceiverBalanceAfterTransaction(Transaction transaction) {
        LiveData<Account> receiverAccount = accountRepository.getReceiverBalance();

        double balance = Double.parseDouble(receiverAccount.getValue().getBalance());
        double amount = Double.parseDouble(transaction.getAmount());

        double addAmountToBalance = balance + amount;
        String newBalance = String.valueOf(addAmountToBalance);

        accountRepository.updateReceiverAfterTransaction(newBalance);
    }

    private void registerReceiverTransaction(Transaction transaction) {
        transaction.setTransferName("Alex");
        // Mark receives from ALex receives on plus - so amount should be on plus
        String amount = transaction.getAmount();
        amount = "+" + amount;
        transaction.setAmount(amount);
        transactionRepository.addTransactionToReceiverAccount(transaction);
    }

    private void registerSenderTransaction(Transaction transaction) {
        // Alex should get a transaction registered with minus
        // Mark receives from ALex receives on plus - so amount should be on plus
        String amount = transaction.getAmount();
        amount = "-" + amount;
        transaction.setAmount(amount);
        transactionRepository.addTransactionToSenderAccount(transaction);
    }
}
