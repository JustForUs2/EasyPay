package via.android.maria.first.easypay.networking.loan.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LoanApi {
@GET("/v3/d5c0675c-2e82-436c-ab2a-5eb85b502f7e")
Call<LoanResponse> getLoanList();
}
