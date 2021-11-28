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
import via.android.maria.first.easypay.model.Recipient;
import via.android.maria.first.easypay.viewmodel.RecipientViewModel;


public class AddRecipientFragment extends Fragment {
    private EditText accountNumber, nameRecipient, sortCode;
    private Button sendCTA;
    private RecipientViewModel recipientViewModel;

    public AddRecipientFragment() {
    }

    public static AddRecipientFragment newInstance(String param1, String param2) {
        AddRecipientFragment fragment = new AddRecipientFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_recipient, container, false);
        recipientViewModel = new ViewModelProvider(this).get(RecipientViewModel.class);
        findViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sendCTA.setOnClickListener((v) -> {
            Recipient recipient = new Recipient();

            recipient.setAccountNumber(accountNumber.getText().toString());
            recipient.setOwnerName(nameRecipient.getText().toString());
            recipient.setSortCode(sortCode.getText().toString());

            recipientViewModel.addRecipient(recipient);
            navigateToRecipients();
        });

    }

    private void navigateToRecipients() {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.savedAccounts);
    }

    private void findViews(View view) {
        accountNumber = view.findViewById(R.id.recipient_account_number);
        nameRecipient = view.findViewById(R.id.recipient_name);
        sortCode = view.findViewById(R.id.recipient_sort_code);
        sendCTA = view.findViewById(R.id.send);
    }
}