package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

import via.android.maria.first.easypay.firebasecloud.Authentication.FireUserRepository;

public class MainActivityViewModel extends AndroidViewModel {
    private final FireUserRepository fireUserRepository;

    public MainActivityViewModel(Application application) {
        super(application);
        fireUserRepository = FireUserRepository.getInstance(application);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return fireUserRepository.getCurrentUser();
    }

    public void signOut() {
        fireUserRepository.signOut();
    }

    public void init() {
        //fireUserRepository.getCurrentUser().getValue().getUid();
    }
}
