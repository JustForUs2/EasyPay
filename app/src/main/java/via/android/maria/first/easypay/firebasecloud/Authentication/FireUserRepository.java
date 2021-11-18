package via.android.maria.first.easypay.firebasecloud.Authentication;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseUser;

public class FireUserRepository {
    private final FireUserLiveData currentUser;
    private final Application application;
    private static FireUserRepository instance;

    public FireUserRepository(Application application) {
        this.application = application;
        currentUser = new FireUserLiveData();
    }

    public static synchronized FireUserRepository getInstance(Application application){
        if(instance == null)
            instance = new FireUserRepository(application);
        return instance;
    }
    public LiveData<FirebaseUser> getCurrentUser(){
        return currentUser;
    }
    public void signOut(){
        AuthUI.getInstance().signOut(application.getApplicationContext());
    }
}
