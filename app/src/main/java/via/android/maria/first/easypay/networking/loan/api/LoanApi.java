package via.android.maria.first.easypay.networking.loan.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LoanApi {
@GET("v3/bad47a5c-eaef-4c4f-ab97-132775f86aa1")
Call<LoanResponse> getLoanList();
}
