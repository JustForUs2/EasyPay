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

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.viewmodel.TransactionViewModel;


public class MakePaymentFragment extends Fragment {
    private EditText amount, transferDescription, selectedRecipient;
    private Button sendCTA, selectCTA;
    private TransactionViewModel transactionViewModel;

    public MakePaymentFragment() {
    }


    public static MakePaymentFragment newInstance(String param1, String param2) {
        MakePaymentFragment fragment = new MakePaymentFragment();
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
        View view = inflater.inflate(R.layout.fragment_make_payment, container, false);
        findViews(view);
        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // TODO go select recipient and bring data back
        selectCTA.setOnClickListener((v) -> {
            navigateToSelectRecipient();
        });

        // TODO cover errors when fields empty - extract method if possible
        sendCTA.setOnClickListener((v) -> {

            Transaction transaction = new Transaction();

            transaction.setAmount(amount.getText().toString());
            //transaction.setAccountNumber(accountNumber.getText().toString());
            //transaction.setSortCode(sortCode.getText().toString());
            //transaction.setTransferName(transferName.getText().toString());
            transaction.setDescription(transferDescription.getText().toString());

            transactionViewModel.addTransaction(transaction);
        });
    }

    private void navigateToSelectRecipient() {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.selectRecipient);
    }

    private void navigateToDashboard() {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.dashboardFragment);
    }

    private void findViews(View view) {
        amount = view.findViewById(R.id.amount_to_transfer);
        transferDescription = view.findViewById(R.id.description_of_transfer);
        selectedRecipient = view.findViewById(R.id.selected_recipient);
        sendCTA = view.findViewById(R.id.send_transfer);
        selectCTA = view.findViewById(R.id.select_recipient_for_transfer);
    }
}