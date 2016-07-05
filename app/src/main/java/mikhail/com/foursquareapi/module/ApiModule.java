package mikhail.com.foursquareapi.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mikhail.com.foursquareapi.api.FourSquareAPI;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mikhail on 7/2/16.
 */
@Module
public class ApiModule {

    public static final String API_URL = "https://api.foursquare.com/v2/";


    @Provides
    @Singleton
    OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Provides
    @Singleton
    Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    FourSquareAPI provideGetApi(Retrofit retrofit) {
        return retrofit.create(FourSquareAPI.class);
    }


}
