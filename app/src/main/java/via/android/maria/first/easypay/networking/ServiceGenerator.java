package via.android.maria.first.easypay.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import via.android.maria.first.easypay.networking.account.api.AccountApi;

public class ServiceGenerator {
    private static final Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .addConverterFactory(GsonConverterFactory.create());

    private static final Retrofit retrofit = retrofitBuilder.build();

    private static final AccountApi accountApi = retrofit.create(AccountApi.class);

    private ServiceGenerator() {
    }

    public static AccountApi getAccountApi() {
        return accountApi;
    }
}
