package via.android.maria.first.easypay.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.view.adapter.FilterAdapter;
import via.android.maria.first.easypay.view.adapter.TransactionAdapter;
import via.android.maria.first.easypay.viewmodel.FilterListViewModel;
import via.android.maria.first.easypay.viewmodel.TransactionViewModel;

public class FilterFragment extends Fragment {
    private Button food_button, clothes_button, utilities_button;
    private FilterListViewModel filterListViewModel;
    private RecyclerView recyclerView;
    private FilterAdapter filterAdapter;

    List<Transaction> newList = new ArrayList<>();

    public FilterFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        findViews(view);
        filterListViewModel = new ViewModelProvider(this).get(FilterListViewModel.class);
        filterListViewModel.init();
        initRecyclerView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        filterListViewModel.getTransactions().observe(getViewLifecycleOwner(), new FilterListObserver());
        filterByFood();
    }


    private List<Transaction> filterByFood() {
        food_button.setOnClickListener(v-> {
            List<Transaction> transactions = filterListViewModel.getTransactions().getValue();
            for(int i = 0; i < transactions.size(); i ++){
                if(transactions.get(i).getDescription() != null) {
                    if(transactions.get(i).getDescription().contains("food"))
                        newList.add(transactions.get(i));
                }
            }
        });
        return newList;
    }
    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        filterAdapter = new FilterAdapter();
        recyclerView.setAdapter(filterAdapter);
    }

    private void findViews(View view) {
        food_button = view.findViewById(R.id.food_button);
        clothes_button = view.findViewById(R.id.clothes_button);
        utilities_button = view.findViewById(R.id.utilities_button);
        recyclerView = view.findViewById(R.id.filter_transactions);
    }

    private class FilterListObserver implements androidx.lifecycle.Observer<List<Transaction>> {
        @Override
        public void onChanged(List<Transaction> transactions) {
            filterAdapter.setTransactionList(transactions);
            filterAdapter.notifyDataSetChanged();
        }
    }
}