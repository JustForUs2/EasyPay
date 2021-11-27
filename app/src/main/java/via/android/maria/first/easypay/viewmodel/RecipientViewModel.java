package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import via.android.maria.first.easypay.model.Recipient;
import via.android.maria.first.easypay.repository.RecipientRepository;
import via.android.maria.first.easypay.repository.RecipientRepositoryImpl;

public class RecipientViewModel extends AndroidViewModel {
    private final RecipientRepository repository;

    public RecipientViewModel(@NonNull Application application) {
        super(application);
        repository = RecipientRepositoryImpl.getInstance();
    }

    public void addRecipient(Recipient recipient)
    {
        repository.addRecipient(recipient);
    }

    public void init()
    {

    }

}
