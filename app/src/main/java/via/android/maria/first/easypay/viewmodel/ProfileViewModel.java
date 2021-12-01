package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import via.android.maria.first.easypay.model.User;
import via.android.maria.first.easypay.service.profile.service.ProfileService;

public class ProfileViewModel extends AndroidViewModel {
    private MutableLiveData<User> user;
    private ProfileService profileService;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
    }

    public void init()
    {
       user = profileService.getUser();
    }

    public LiveData<User> getUser()
    {
        return user;
    }
}
