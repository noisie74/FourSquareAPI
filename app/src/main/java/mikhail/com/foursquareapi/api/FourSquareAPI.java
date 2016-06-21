package mikhail.com.foursquareapi.api;

import java.util.List;

import mikhail.com.foursquareapi.model.FoursquareSearch;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Mikhail on 6/17/16.
 */
public class FourSquareAPI {

    public static final String API_URL = "https://api.foursquare.com/v2/";


//    public static FourSquareRX createRx() {
//        return new Retrofit.Builder()
//                .baseUrl(API_URL)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(FourSquareAPI.FourSquareRX.class);
//    }

//    public interface FourSquareRX {
//        @GET("venues/{explore}")
//        Observable<Response<Places>> venues(
//                @Path("explore") String explore,
//                @Query("ll") String coordinates,
//                @Query("oauth_token") String token,
//                @Query("v") String version);
//
//
//    }

    public interface getNearPlace {
        @GET("venues/search")
        Call <List<FoursquareSearch.response.venues>> searchResults(@Query("ll") String ll,
                          @Query("oauth_token") String oauth_token,
                          @Query("v") String v);
    }

    public static getNearPlace create() {
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FourSquareAPI.getNearPlace.class);
    }
}
