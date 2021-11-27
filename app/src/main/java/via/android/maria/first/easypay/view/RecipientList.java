package via.android.maria.first.easypay.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.model.Recipient;
import via.android.maria.first.easypay.view.adapter.RecipientAdapter;
import via.android.maria.first.easypay.viewmodel.RecipientViewModel;

public class RecipientList extends Fragment {
    private FloatingActionButton fab;
    private RecipientViewModel recipientViewModel;
    private RecipientAdapter recipientAdapter;
    private RecyclerView recyclerView;

    public RecipientList() {

    }

    public static RecipientList newInstance(String param1, String param2) {
        RecipientList fragment = new RecipientList();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_accounts, container, false);
        findViews(view);
        recipientViewModel = new ViewModelProvider(this).get(RecipientViewModel.class);
        recipientViewModel.init();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recipientAdapter = new RecipientAdapter();
        recyclerView.setAdapter(recipientAdapter);
        if (recipientViewModel.getRecipients() != null)
        {
            recipientViewModel.getRecipients().observe(getViewLifecycleOwner(), new RecipientObserverImpl());
        }


        fab.setOnClickListener((v) -> {
            navigateToAddRecipient();
        });
    }

    private void navigateToAddRecipient() {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.addRecipient);
    }

    private void findViews(View view) {
        fab = view.findViewById(R.id.fab_add_recipient);
        recyclerView = view.findViewById(R.id.recipients_list);
    }

    private class RecipientObserverImpl implements Observer<List<Recipient>> {
        @Override
        public void onChanged(List<Recipient> recipients) {
            recipientAdapter.setRecipientList(recipients);
            recipientAdapter.notifyDataSetChanged();
        }
    }
}