package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import via.android.maria.first.easypay.model.Account;
import via.android.maria.first.easypay.model.User;
import via.android.maria.first.easypay.repository.AddUserWithAccountRepository;
import via.android.maria.first.easypay.repository.AddUserWithAccountRepositoryImpl;

public class AddUserWithAccountViewModel extends AndroidViewModel {
    private AddUserWithAccountRepository repository;

    public AddUserWithAccountViewModel(@NonNull Application application) {
        super(application);
        repository = AddUserWithAccountRepositoryImpl.getInstance();
    }

    public void addUserWithAccount(String uid) {
        User user = new User();
        user.setUid(uid);
        user.setName("Alex");

        Account account = new Account();
        account.setAccountNumber("044011624");
        account.setBalance("34.588");
        //user.setAccount(account);

        repository.addUserWithAccount(user);

    }
}
