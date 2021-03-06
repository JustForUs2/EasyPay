package via.android.maria.first.easypay.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import via.android.maria.first.easypay.model.Account;
import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.utils.Constants;
import via.android.maria.first.easypay.view.adapter.TransactionAdapter;
import via.android.maria.first.easypay.viewmodel.AccountViewModel;
import via.android.maria.first.easypay.viewmodel.TransactionViewModel;

public class DashboardFragment extends Fragment {
    private TextView currentBalance, availableBalance;
    private FloatingActionButton floatingCTA;
    private TransactionAdapter transactionAdapter;
    private TransactionViewModel transactionViewModel;
    private AccountViewModel accountModel;
    private RecyclerView recyclerView;

    public DashboardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        findViews(view);
        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionViewModel.init();

        accountModel = new ViewModelProvider(this).get(AccountViewModel.class);
        accountModel.init();
        initRecyclerView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        accountModel.getAccount().observe(getViewLifecycleOwner(), new AccountBalanceImpl());
        transactionViewModel.getTransactions().observe(getViewLifecycleOwner(), new TransactionObserverImpl());

        floatingCTA.setOnClickListener((v) -> {
            navigateToTransaction();
        });
    }

    private void initRecyclerView()
    {
        transactionAdapter = new TransactionAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(transactionAdapter);
    }

    private void navigateToTransaction() {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.makePayment);
    }

    private void findViews(View view) {
        floatingCTA = view.findViewById(R.id.fab);
        recyclerView = view.findViewById(R.id.completed_transactions);
        currentBalance = view.findViewById(R.id.current_balance);
        availableBalance = view.findViewById(R.id.available_balance);
    }

    private class TransactionObserverImpl implements Observer<List<Transaction>> {
        @Override
        public void onChanged(List<Transaction> transactions) {
            transactionAdapter.setTransactionList(transactions);
            transactionAdapter.notifyDataSetChanged();
        }
    }

    private class AccountBalanceImpl implements Observer<Account> {
        @Override
        public void onChanged(Account account) {
            currentBalance.setText(account.getBalance());
            String balance = account.getBalance();
            double newAvailableBalance = Double.parseDouble(balance) + Constants.ACCOUNT_EXTRA_CREDIT;
            availableBalance.setText(String.valueOf(newAvailableBalance));
        }
    }
}