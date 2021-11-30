package via.android.maria.first.easypay.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.model.Recipient;
import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.utils.Constants;
import via.android.maria.first.easypay.viewmodel.TransactionViewModel;


// FRAGMENT A
//TODO make common viewModel - extra view model for handling the data exchange - amount and description should be saved
public class MakePaymentFragment extends Fragment {
    private EditText amount, transferDescription;
    private TextView selectedRecipient;
    private Button sendCTA, selectCTA, cancelCTA;
    private TransactionViewModel transactionViewModel;
    private Recipient bundleSerializableRecipient;

    public MakePaymentFragment() {
    }


    public static MakePaymentFragment newInstance(String param1, String param2) {
        MakePaymentFragment fragment = new MakePaymentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                bundleSerializableRecipient = (Recipient) bundle.getSerializable("bundleKey");
                selectedRecipient.setText(bundleSerializableRecipient.getOwnerName());
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Amount", amount.getText().toString());
        outState.putString("Description", transferDescription.getText().toString());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null){
            String amount = savedInstanceState.getString("Amount");
            this.amount.setText(amount);
        }

        if (savedInstanceState != null)
        {
            String description = savedInstanceState.getString("Description");
            transferDescription.setText(description);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_payment, container, false);
        findViews(view);
        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionViewModel.init();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selectCTA.setOnClickListener((v) -> {
            navigateToSelectRecipient();
        });

        // TODO error handling - extract method if possible
        sendCTA.setOnClickListener((v) -> {

            Transaction transaction = new Transaction();

            transaction.setAmount(amount.getText().toString());
            transaction.setAccountNumber(bundleSerializableRecipient.getAccountNumber());
            transaction.setSortCode(bundleSerializableRecipient.getSortCode());
            transaction.setTransferName(bundleSerializableRecipient.getOwnerName());
            transaction.setDescription(transferDescription.getText().toString());
            transaction.setType(Constants.WITHDRAWAL_ACCOUNT_TYPE);

            transactionViewModel.addTransaction(transaction);
            navigateToDashboard();
        });

        cancelCTA.setOnClickListener((v) -> {
            navigateToDashboard();
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
        cancelCTA = view.findViewById(R.id.cancel);
    }
}