package via.android.maria.first.easypay.networking.account.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AccountApi {
    @GET("/v3/0757e710-e257-480e-863c-adbf103c3bf8")
    Call<AccountResponse> getTransactionList();
}
