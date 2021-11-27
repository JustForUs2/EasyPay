package via.android.maria.first.easypay.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import via.android.maria.first.easypay.model.Recipient;
import via.android.maria.first.easypay.repository.recipient.repository.RecipientRepository;
import via.android.maria.first.easypay.repository.recipient.repository.RecipientRepositoryImpl;

public class RecipientViewModel extends AndroidViewModel {
    private final RecipientRepository repository;
    private MutableLiveData<List<Recipient>> recipients;

    public RecipientViewModel(Application application) {
        super(application);
        repository = RecipientRepositoryImpl.getInstance();
    }

    public void addRecipient(Recipient recipient) {
        repository.addRecipient(recipient);
    }

    public void init() {
        recipients = repository.getRecipients();
    }

    public LiveData<List<Recipient>> getRecipients() {
        return recipients;
    }
}
