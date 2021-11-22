package via.android.maria.first.easypay.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {
    private EditText email;
    private EditText password;
    private Button loginButton;
    private TextView error;
    private LoginViewModel loginViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            if (a){
                navigateToDashboard();
            }
        });

    }

    private void navigateToDashboard() {
        // TODO implementation of dashboard
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.init();
        return view;
    }

    private void findViews(@NotNull View view)
    {
        loginButton = view.findViewById(R.id.login);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        error = view.findViewById(R.id.signin_error_message);
    }
}