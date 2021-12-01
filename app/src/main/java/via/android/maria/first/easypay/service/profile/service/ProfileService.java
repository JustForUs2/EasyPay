package via.android.maria.first.easypay.service.profile.service;

import androidx.lifecycle.MutableLiveData;

import via.android.maria.first.easypay.model.User;

public interface ProfileService {
    MutableLiveData<User> getUser();
}
