package via.android.maria.first.easypay.networking.loan.api;

import static android.content.ContentValues.TAG;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;
import via.android.maria.first.easypay.model.Loan;
import via.android.maria.first.easypay.networking.ServiceGenerator;

public class LoanResponseApi {
    private final List<Loan> loanList = new ArrayList<>();

    public List<Loan> getLoanListApiData(){
        LoanApi loanApi = ServiceGenerator.getLoanApi();
        Call<LoanResponse> call = loanApi.getLoanList();
        call.enqueue(new Callback<LoanResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<LoanResponse> call, Response<LoanResponse> response) {
                if (response.isSuccessful()){
                    loanList.addAll(response.body().getLoans());
                    Log.d(TAG, "On API call");
                }
            }

            @Override
            public void onFailure(Call<LoanResponse> call, Throwable throwable) {
                Log.d(TAG, "API response");
            }
        });
        return loanList;
    }
}
