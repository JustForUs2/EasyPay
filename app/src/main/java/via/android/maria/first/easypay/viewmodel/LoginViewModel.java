package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

import via.android.maria.first.easypay.firebasecloud.Authentication.FireUserRepository;

public class LoginViewModel extends AndroidViewModel {
    private final FireUserRepository fireUserRepository;

    public LoginViewModel(Application application) {
        super(application);
        fireUserRepository = FireUserRepository.getInstance(application);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return fireUserRepository.getCurrentUser();
    }
}
