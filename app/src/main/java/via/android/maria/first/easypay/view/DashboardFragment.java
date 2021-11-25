package via.android.maria.first.easypay.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.view.adapter.TransactionAdapter;

public class DashboardFragment extends Fragment {
    private FloatingActionButton floatingCTA;
    private TransactionAdapter transactionAdapter;
    private RecyclerView recyclerView;

    public DashboardFragment() {
        // Required empty public constructor
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
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        transactionAdapter = new TransactionAdapter();
        recyclerView.setAdapter(transactionAdapter);


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

}