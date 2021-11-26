package via.android.maria.first.easypay.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.view.adapter.TransactionAdapter;
import via.android.maria.first.easypay.viewmodel.TransactionViewModel;

public class DashboardFragment extends Fragment {
    private FloatingActionButton floatingCTA;
    private TransactionAdapter transactionAdapter;
    private TransactionViewModel transactionViewModel;
    private RecyclerView recyclerView;

    public DashboardFragment() {
    }

    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        findViews(view);
        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionViewModel.init();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        LiveData<List<Transaction>> transactions = transactionViewModel.getTransactions();
        transactionAdapter = new TransactionAdapter();
        recyclerView.setAdapter(transactionAdapter);
        transactionViewModel.getTransactions().observe(getViewLifecycleOwner(), new TransactionObserverImpl());

        floatingCTA.setOnClickListener((v) -> {
            navigateToTransaction();
        });
    }

    private void navigateToTransaction() {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.transaction);
    }

    private void findViews(View view) {
        floatingCTA = view.findViewById(R.id.fab);
        recyclerView = view.findViewById(R.id.completed_transactions);
    }

    private class TransactionObserverImpl implements Observer<List<Transaction>> {
        @Override
        public void onChanged(List<Transaction> transactions) {
            transactionAdapter.setTransactionList(transactions);
            transactionAdapter.notifyDataSetChanged();
        }
    }
}