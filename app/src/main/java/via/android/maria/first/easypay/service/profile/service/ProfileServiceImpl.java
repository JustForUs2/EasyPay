package via.android.maria.first.easypay.service.profile.service;

import androidx.lifecycle.MutableLiveData;

import via.android.maria.first.easypay.model.User;
import via.android.maria.first.easypay.repository.profile.repository.ProfileRepository;
import via.android.maria.first.easypay.repository.profile.repository.ProfileRepositoryImpl;

public class ProfileServiceImpl implements ProfileService {
    private ProfileRepository repository;
    private MutableLiveData<User> userData;

    public ProfileServiceImpl() {
        repository = ProfileRepositoryImpl.getInstance();
    }

    @Override
    public MutableLiveData<User> getUser() {
        repository.getUser((user) -> {
            userData.setValue(user);
        });
        return userData;
    }
}

