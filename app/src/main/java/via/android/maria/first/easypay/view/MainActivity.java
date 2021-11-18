package via.android.maria.first.easypay.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import via.android.maria.first.easypay.R;
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

        viewModel.getCurrentUser().observe(this, user -> {
            if(user != null)
                Toast.makeText(this,
                        "Welcome back "+user.getDisplayName(),Toast.LENGTH_SHORT).show();
        });
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