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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.view.adapter.FilterAdapter;
import via.android.maria.first.easypay.viewmodel.FilterListViewModel;

public class FilterFragment extends Fragment {
    private Button food_button, clothes_button, utilities_button;
    private FloatingActionButton floatingActionButton;
    private FilterListViewModel filterListViewModel;
    private RecyclerView recyclerView;
    private FilterAdapter filterAdapter;
    private List<Transaction> newList;

    public FilterFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        findViews(view);
        filterListViewModel = new ViewModelProvider(this).get(FilterListViewModel.class);
        filterListViewModel.init();
        initRecyclerView();
        newList = new ArrayList<>();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        filterListViewModel.getTransactions().observe(getViewLifecycleOwner(), new FilterListObserver());
        filterByFood();
        filterByUtilities();
        filterByClothes();

        floatingActionButton.setOnClickListener(v -> {
            reload();
        });
    }

    private void reload() {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.dashboardFragment);
    }

    private List<Transaction> filterByClothes() {
        clothes_button.setOnClickListener(v-> {
            List<Transaction> transactions = filterListViewModel.getTransactions().getValue();
            for(int i = 0; i < transactions.size(); i ++){
                if(transactions.get(i).getDescription() != null) {
                    if(transactions.get(i).getDescription().contains("clothes"))
                        newList.add(transactions.get(i));
                }
            }
            transactions.clear();
            filterAdapter.setTransactionList(newList);
            filterAdapter.notifyDataSetChanged();
        });
        return newList;
    }

    private List<Transaction> filterByUtilities() {
        utilities_button.setOnClickListener(v-> {
            List<Transaction> transactions = filterListViewModel.getTransactions().getValue();
            for(int i = 0; i < transactions.size(); i ++){
                if(transactions.get(i).getDescription() != null) {
                    if(transactions.get(i).getDescription().contains("utilities"))
                        newList.add(transactions.get(i));
                }
            }
            transactions.clear();
            filterAdapter.setTransactionList(newList);
            filterAdapter.notifyDataSetChanged();
        });
        return newList;
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
            transactions.clear();
            filterAdapter.setTransactionList(newList);
            filterAdapter.notifyDataSetChanged();
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
        floatingActionButton = view.findViewById(R.id.fab_filtering);
        recyclerView = view.findViewById(R.id.filter_transactions);
    }

    private class FilterListObserver implements Observer<List<Transaction>> {
        @Override
        public void onChanged(List<Transaction> transactions) {
            filterAdapter.setTransactionList(transactions);
            filterAdapter.notifyDataSetChanged();
        }
    }
}