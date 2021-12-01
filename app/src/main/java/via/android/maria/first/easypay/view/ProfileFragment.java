package via.android.maria.first.easypay.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.model.User;
import via.android.maria.first.easypay.viewmodel.ProfileViewModel;

public class ProfileFragment extends Fragment {
    private ImageView imageView;
    private TextView firstName, lastName, email, contactNo;
    private ProfileViewModel profileViewModel;

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        findViews(view);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        profileViewModel.init();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (profileViewModel.getUser() != null)
        {
            profileViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<User>() {
                @Override
                public void onChanged(User user) {
                    firstName.setText(user.getName());
                    lastName.setText(user.getSurname());
                    email.setText(user.getEmail());
                    contactNo.setText(user.getPhone());
                }
            });
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void findViews(View view)
    {
        imageView = view.findViewById(R.id.profile_image);
        firstName = view.findViewById(R.id.profile_first_name);
        lastName = view.findViewById(R.id.profile_last_name);
        email = view.findViewById(R.id.profile_email);
        contactNo = view.findViewById(R.id.profile_contact_no);
    }
}