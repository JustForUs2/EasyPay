package via.android.maria.first.easypay.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.view.adapter.TransactionAdapter;
import via.android.maria.first.easypay.viewmodel.TransactionViewModel;

public class FilterFragment extends Fragment {
    private Button food_button, clothes_button, utilities_button;
    private TransactionViewModel transactionViewModel;
    private TransactionAdapter transactionAdapter;
    private RecyclerView recyclerView;

    public FilterFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        findViews(view);
        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionViewModel.init();
        initRecyclerView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        transactionViewModel.getTransactions();
        //filterByFood();
    }

    private void filterByFood() {
        food_button.setOnClickListener(v-> {
            List<Transaction> transactions = (List<Transaction>) transactionViewModel.getTransactions();
            List<Transaction> newListWithFilter = new ArrayList<>();
            for(Transaction t : transactions){
                if(t.getDescription().contains("food"));
                newListWithFilter.add(t);
            }
        });
    }
    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        transactionAdapter = new TransactionAdapter();
        recyclerView.setAdapter(transactionAdapter);
    }

    private void findViews(View view) {
        food_button = view.findViewById(R.id.food_button);
        clothes_button = view.findViewById(R.id.clothes_button);
        utilities_button = view.findViewById(R.id.utilities_button);
        recyclerView = view.findViewById(R.id.completed_transactions);
    }
}