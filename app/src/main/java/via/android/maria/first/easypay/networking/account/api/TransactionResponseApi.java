package via.android.maria.first.easypay.networking.account.api;

import static android.content.ContentValues.TAG;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.annotations.EverythingIsNonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import via.android.maria.first.easypay.model.Transaction;
import via.android.maria.first.easypay.networking.ServiceGenerator;

public class TransactionResponseApi {
    private final List<Transaction> apiAccount = new ArrayList<>();

    public List<Transaction> getApiData() {
        AccountApi accountApi = ServiceGenerator.getAccountApi();
        Call<AccountResponse> call = accountApi.getTransactionList();
        call.enqueue(new Callback<AccountResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if (response.isSuccessful()) {
                    apiAccount.addAll(response.body().getTransactions());
                    Log.d(TAG, "On api call");
                }
            }


            @EverythingIsNonNull
            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                Log.d(TAG, "API response");
            }
        });
        return apiAccount;
    }
}
