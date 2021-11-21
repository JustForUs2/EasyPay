package via.android.maria.first.easypay.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.model.User;
import via.android.maria.first.easypay.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;
    private TextView welcomeBackMessage;
    private Button dashboard;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ####### Navigation Component ###############
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        dashboard = findViewById(R.id.dashboard);
        // !! nav_host_fragment - empty container where destinations are swapped in and out
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        /*
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.init();
        checkIfSignedIn();
        setContentView(R.layout.activity_main);
        welcomeBackMessage = findViewById(R.id.welcome_message);
        User newUser = null;

        viewModel.getCurrentUser().observe(this, user -> {
            if(user != null)
                Toast.makeText(this,
                        "Welcome back "+user.getDisplayName(),Toast.LENGTH_SHORT).show();
        });
         */
    }

    public void signIn(View view) {
        view.setOnClickListener(view1 -> {
            navController.navigate(R.id.dashboard);
        });
    }
    /*
    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                String message = "Welcome " + user.getDisplayName();
                welcomeBackMessage.setText(message);
            } else
                startLoginActivity();
        });
    }
     */
/*
    private void startLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void signOut(View view){
        viewModel.signOut();
    }
    */

}