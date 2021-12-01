package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import via.android.maria.first.easypay.model.User;
import via.android.maria.first.easypay.service.profile.service.ProfileService;
import via.android.maria.first.easypay.service.profile.service.ProfileServiceImpl;

public class ProfileViewModel extends AndroidViewModel {
    private MutableLiveData<User> user;
    private ProfileService profileService;

    public ProfileViewModel(Application application) {
        super(application);
        profileService = new ProfileServiceImpl();
    }

    public void init()
    {
        if (user != null)
        {
            return;
        }
       user = profileService.getUser();
    }

    public LiveData<User> getUser()
    {
        return user;
    }
}
