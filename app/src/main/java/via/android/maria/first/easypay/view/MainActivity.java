package via.android.maria.first.easypay.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.model.User;
import via.android.maria.first.easypay.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;
    private TextView welcomeBackMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("User");
        newUser.setId(1);
        newUser.setUsername("username");
        newUser.setPassword("password");
        
        myRef.push().setValue(newUser);

        // ####### Navigation
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
    }
    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                String message = "Welcome " + user.getDisplayName();
                welcomeBackMessage.setText(message);
            } else
                startLoginActivity();
        });
    }

    private void startLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void signOut(View view){
        viewModel.signOut();
    }

}