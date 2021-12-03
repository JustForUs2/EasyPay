package via.android.maria.first.easypay.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.model.Recipient;
import via.android.maria.first.easypay.view.adapter.RecipientAdapter;
import via.android.maria.first.easypay.viewmodel.RecipientViewModel;

public class SelectRecipientFragment extends Fragment implements RecipientAdapter.OnListItemClickListener {
    private Button addRecipientCTA;
    private TextInputEditText recipientName, recipientAccountNum, recipientSortCode;
    private SwitchMaterial switchMaterial;
    private RecipientViewModel recipientViewModel;
    private RecipientAdapter recipientAdapter;
    private RecyclerView recyclerView;
    private List<Recipient> recipientListLoaded;

    public SelectRecipientFragment() {
    }

    public static SelectRecipientFragment newInstance(String param1, String param2) {
        SelectRecipientFragment fragment = new SelectRecipientFragment();
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
        View view = inflater.inflate(R.layout.fragment_select_recipient, container, false);
        recipientViewModel = new ViewModelProvider(this).get(RecipientViewModel.class);
        recipientViewModel.init();
        findViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recipientAdapter = new RecipientAdapter(this);
        recyclerView.setAdapter(recipientAdapter);
        recipientListLoaded = new ArrayList<>();

        if (recipientViewModel.getRecipients() != null) {
            recipientViewModel.getRecipients().observe(getViewLifecycleOwner(), new RecipientObserverImpl());
        }


        addRecipientCTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle result = new Bundle();

                Recipient recipient = new Recipient();
                recipient.setSortCode(recipientSortCode.getText().toString());
                recipient.setAccountNumber(recipientAccountNum.getText().toString());
                recipient.setOwnerName(recipientName.getText().toString());

                result.putSerializable("bundleKey", recipient);
                getParentFragmentManager().setFragmentResult("requestKey", result);

                if (switchMaterial.isChecked()) {
                    recipientViewModel.addRecipient(recipient);
                }

                navigateToSendTransaction();
            }
        });
    }

    private void navigateToSendTransaction() {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.makePayment);
    }

    private void findViews(View view) {
        addRecipientCTA = view.findViewById(R.id.add_recipient_for_transfer);
        recipientName = view.findViewById(R.id.name);
        recipientAccountNum = view.findViewById(R.id.account_num);
        recipientSortCode = view.findViewById(R.id.sort_code);
        switchMaterial = view.findViewById(R.id.switch1);
        recyclerView = view.findViewById(R.id.recipients_list);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        Recipient recipient = recipientListLoaded.get(clickedItemIndex);
        recipientName.setText(recipient.getOwnerName());
        recipientAccountNum.setText(recipient.getAccountNumber());
        recipientSortCode.setText(recipient.getSortCode());
    }

    private class RecipientObserverImpl implements Observer<List<Recipient>> {
        @Override
        public void onChanged(List<Recipient> recipients) {
            recipientAdapter.setRecipientList(recipients);
            recipientListLoaded = recipients;
            recipientAdapter.notifyDataSetChanged();
        }
    }
}