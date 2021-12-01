package via.android.maria.first.easypay.repository.profile.repository;

import via.android.maria.first.easypay.model.User;
import via.android.maria.first.easypay.repository.FirestoreCallback;

public interface ProfileRepository {
    void getUser(FirestoreCallback<User> callback);
}
