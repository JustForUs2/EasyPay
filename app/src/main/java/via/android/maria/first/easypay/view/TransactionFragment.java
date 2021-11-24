package via.android.maria.first.easypay.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import org.jetbrains.annotations.NotNull;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.model.Transaction_02;
import via.android.maria.first.easypay.viewmodel.TransactionViewModel;

public class TransactionFragment extends Fragment {
    private EditText amount, sortCode, accountNumber, transferName;
    private Button sendButton;
    TransactionViewModel transactionViewModel;

    public TransactionFragment() {
        // Required empty public constructor
    }


    public static TransactionFragment newInstance(String param1, String param2) {
        TransactionFragment fragment = new TransactionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionViewModel.init();
        sendTransaction();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        // TODO on click button

    }

    public void sendTransaction() {
        sendButton.setOnClickListener((v) -> {

            Transaction_02 transaction = new Transaction_02();
            transaction.setAmount(amount.getText().toString());
            transaction.setAccountNumber(accountNumber.getText().toString());
            transaction.setSortCode(sortCode.getText().toString());
            transaction.setTransferName(transferName.getText().toString());

            // TODO here ID is hardcoded - needs to be taken from logged in user's account
            transactionViewModel.addTransaction(transaction, "WoFRKQTWczVOaFzzUF96");
        });
    }

    private void navigateToDashboard() {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.dashboardFragment);
    }

    private void findViews(@NotNull View view) {
        amount = view.findViewById(R.id.transfer_amount);
        sortCode = view.findViewById(R.id.transfer_code);
        accountNumber = view.findViewById(R.id.transfer_account_number);
        transferName = view.findViewById(R.id.transfer_name);
        sendButton = view.findViewById(R.id.send);
    }
}