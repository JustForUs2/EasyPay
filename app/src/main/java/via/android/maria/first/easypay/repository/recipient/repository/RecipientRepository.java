package via.android.maria.first.easypay.repository.recipient.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import via.android.maria.first.easypay.model.Recipient;

public interface RecipientRepository {
    void addRecipient(Recipient recipient);
    MutableLiveData<List<Recipient>> getRecipients();
}
