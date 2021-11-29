package via.android.maria.first.easypay.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {

    private TextInputEditText email;
    private TextInputEditText password;
    private Button loginButton;
    private TextView error;
    private LoginViewModel loginViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.init();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        loginViewModel.getShowError().observe(getViewLifecycleOwner(), b -> {
            int visibility = b ? View.VISIBLE : View.INVISIBLE;
            error.setVisibility(visibility);
        });

        loginViewModel.getIsSigned().observe(getViewLifecycleOwner(), a -> {
            if (a) {
                navigateToDashboard();
            }
        });
        loginButton.setOnClickListener((v) -> loginViewModel.authenticateUser(email.getText().toString(), password.getText().toString()));
    }

    private void navigateToDashboard() {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.dashboardFragment);
    }


    private void findViews(@NotNull View view) {
        loginButton = view.findViewById(R.id.login);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        error = view.findViewById(R.id.signin_error_message);
    }
}