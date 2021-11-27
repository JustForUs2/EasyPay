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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import via.android.maria.first.easypay.R;

public class SavedAccounts extends Fragment {

    private FloatingActionButton fab;

    public SavedAccounts() {

    }

    public static SavedAccounts newInstance(String param1, String param2) {
        SavedAccounts fragment = new SavedAccounts();
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

        View view = inflater.inflate(R.layout.fragment_saved_accounts, container, false);
        findViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab.setOnClickListener((v) -> {
            nagivateToAddRecipient();
        });
    }

    private void nagivateToAddRecipient() {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.addRecipient);
    }

    private void findViews(View view) {
        fab = view.findViewById(R.id.fab_add_recipient);
    }
}