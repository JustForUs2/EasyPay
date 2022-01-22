package via.android.maria.first.easypay.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import via.android.maria.first.easypay.networking.account.api.AccountApi;
import via.android.maria.first.easypay.networking.loan.api.LoanApi;

public class ServiceGenerator {
    private static final Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .addConverterFactory(GsonConverterFactory.create());

    private static final Retrofit retrofit = retrofitBuilder.build();
    private static final AccountApi accountApi = retrofit.create(AccountApi.class);
    private static LoanApi loanApi;

    private ServiceGenerator() {
    }

    public static AccountApi getAccountApi() {
        return accountApi;
    }

    public static LoanApi getLoanApi() {
        if (loanApi == null){
            loanApi = new Retrofit.Builder().baseUrl("https://run.mocky.io/")
                    .addConverterFactory(GsonConverterFactory.create()).build().create(LoanApi.class);
        }
        return loanApi;
    }
}
