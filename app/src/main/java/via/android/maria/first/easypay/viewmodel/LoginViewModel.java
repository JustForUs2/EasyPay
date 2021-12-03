package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends AndroidViewModel {

    private FirebaseAuth firebaseAuth;
    private MutableLiveData<Boolean> showError;
    private MutableLiveData<Boolean> isSigned;


    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        firebaseAuth = FirebaseAuth.getInstance();
        showError = new MutableLiveData<>(false);
        isSigned = new MutableLiveData<>(false);
    }

    public void authenticateUser(String email, String password) {
        if (email != null && password != null && !email.isEmpty() && !password.isEmpty())
        {
            showError.postValue(false);
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task ->
            {
                if (task.isSuccessful()){
                    isSigned.postValue(true);
                }
                else {
                    showError.postValue(true);
                }
            });
        }
        else {
            showError.postValue(true);
        }
    }

    public MutableLiveData<Boolean> getShowError() {
        return showError;
    }

    public MutableLiveData<Boolean> getIsSigned() {
        return isSigned;
    }
}
