package via.android.maria.first.easypay.repository.add.user.with.account;

import com.google.firebase.firestore.FirebaseFirestore;

import via.android.maria.first.easypay.model.User;

public class AddUserWithAccountRepositoryImpl implements AddUserWithAccountRepository {
    private static AddUserWithAccountRepositoryImpl instance;
    private FirebaseFirestore database;

    private AddUserWithAccountRepositoryImpl() {
        database = FirebaseFirestore.getInstance();
    }

    public static AddUserWithAccountRepositoryImpl getInstance() {
        if (instance == null)
            instance = new AddUserWithAccountRepositoryImpl();
        return instance;
    }

    @Override
    public void addUserWithAccount(User user) {
    }
}
