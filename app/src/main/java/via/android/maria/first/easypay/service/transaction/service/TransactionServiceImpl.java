package via.android.maria.first.easypay.service.transaction.service;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.repository.account.repository.AccountRepository;
import via.android.maria.first.easypay.repository.account.repository.AccountRepositoryImpl;
import via.android.maria.first.easypay.repository.transaction.repository.TransactionRepository;
import via.android.maria.first.easypay.repository.transaction.repository.TransactionRepositoryImpl;
import via.android.maria.first.easypay.utils.Constants;

public class TransactionServiceImpl implements TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl() {
        accountRepository = AccountRepositoryImpl.getInstance();
        transactionRepository = TransactionRepositoryImpl.getInstance();
    }

    @Override
    public void completeTransaction(Transaction transaction) {
        updateReceiverBalanceAfterTransaction(transaction);
        updateSenderBalanceAfterTransaction(transaction);
        registerReceiverTransaction(transaction);
        registerSenderTransaction(transaction);
    }

    @Override
    public MutableLiveData<List<Transaction>> getTransactions() {
        MutableLiveData<List<Transaction>> transactions = new MutableLiveData<>();
        transactionRepository.getTransactions(transactions::setValue);
        return transactions;
    }

    @Override
    public void addTransactionList() {
        transactionRepository.addTransactionList();
    }

    private void updateSenderBalanceAfterTransaction(Transaction transaction) {
        accountRepository.readDataAccountSender((account -> {
            double balance = Double.parseDouble(account.getBalance());
            double amount = Double.parseDouble(transaction.getAmount());

            double extractAmount = balance - amount;
            String newBalance = String.valueOf(extractAmount);
            accountRepository.updateBalanceAfterTransaction(newBalance);
        }));
    }

    private void updateReceiverBalanceAfterTransaction(Transaction transaction) {
        accountRepository.readDataAccountReceiver(account -> {
            double balance = Double.parseDouble(account.getBalance());
            double amount = Double.parseDouble(transaction.getAmount());
            double addAmountToBalance = balance + amount;
            String newBalance = String.valueOf(addAmountToBalance);
            accountRepository.updateReceiverAfterTransaction(newBalance);
        });
    }

    private void registerReceiverTransaction(Transaction transaction) {
        Transaction receiverTransaction = transaction.copy();
        receiverTransaction.setTransferName("Alex"); // Alex is the sender
        if (receiverTransaction.getType().equals(Constants.WITHDRAWAL_ACCOUNT_TYPE)) {
            String amount = receiverTransaction.getAmount();
            amount = "+" + amount;
            receiverTransaction.setAmount(amount);
            receiverTransaction.setType(Constants.DEPOSIT_ACCOUNT_TYPE);
        }

        transactionRepository.addTransactionToReceiverAccount(receiverTransaction);
    }

    private void registerSenderTransaction(Transaction transaction) {
        Transaction senderTransaction = transaction.copy();
        if (senderTransaction.getType().equals(Constants.WITHDRAWAL_ACCOUNT_TYPE)) {
            String amount = senderTransaction.getAmount();
            amount = "-" + amount;
            senderTransaction.setAmount(amount);
        }
        transactionRepository.addTransactionToSenderAccount(senderTransaction);
    }
}
