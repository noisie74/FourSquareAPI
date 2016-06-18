package mikhail.com.foursquareapi.api;

import mikhail.com.foursquareapi.model.Places;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Mikhail on 6/17/16.
 */
public class FourSquareAPI {

    public static final String API_URL = "https://api.foursquare.com/v2/";

    public static FourSquareRX createRx() {
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FourSquareAPI.FourSquareRX.class);
    }

    public interface FourSquareRX {
        @GET("venues/explore")
        Observable<Response<Places>> fourSquarePlaces(
                @Query("ll") String coordinates,
                @Query("oauth_token") String token,
                @Query("v") String version);


    }
}
