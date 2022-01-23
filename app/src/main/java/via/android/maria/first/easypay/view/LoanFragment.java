package via.android.maria.first.easypay.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.view.adapter.LoanAdapter;

public class LoanFragment extends Fragment {

    RecyclerView recyclerViewLoans;
    LoanAdapter loanAdapter;

    public LoanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.loan_card, container, false);
    }
}