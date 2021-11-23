package via.android.maria.first.easypay.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import via.android.maria.first.easypay.R;

public class MainActivity extends AppCompatActivity {
    //private TextView welcomeBackMessage;
    //private Button dashboard;
    //NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  // ####### Navigation Component ###############
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();

        dashboard = findViewById(R.id.dashboard);
        // !! nav_host_fragment - empty container where destinations are swapped in and out
    }

    public void signIn(View view) {
        view.setOnClickListener(view1 -> {
            navController.navigate(R.id.dashboard);
        });
    }

       */
    }
}