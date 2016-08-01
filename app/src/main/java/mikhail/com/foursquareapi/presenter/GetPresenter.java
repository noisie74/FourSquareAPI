package mikhail.com.foursquareapi.presenter;

import android.util.Log;

import java.util.List;

import mikhail.com.foursquareapi.MainActivity;
import mikhail.com.foursquareapi.api.FourSquareAPI;
import mikhail.com.foursquareapi.model.FoursquareSearch;
import mikhail.com.foursquareapi.module.ApiComponent;
import mikhail.com.foursquareapi.module.ApiModule;
import mikhail.com.foursquareapi.module.DaggerApiComponent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mikhail on 7/2/16.
 */
public class GetPresenter {

    FourSquareAPI mApi;
    ApiComponent mApiComponent;
    MainActivity mView;
    public final String COORDINATES = "37.809,-122.273";
    public final String COORDINATES_NYC = "40.7,-74";
    public final String TOKEN = "JCWKUHGL0GRSEKVGC2A5TAOAAAR3S1AXIW0CLTYTJWYPCD55";
    public final String VERSION = "20160702";

    public GetPresenter(MainActivity view) {
        this.mView = view;
        mApiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .build();
        mApi = mApiComponent.provideApiService();
    }


    public void loadVenues(final boolean isRefresh) {
        if (!isRefresh)
            mView.showProgress(true);
        mApi.searchResults(COORDINATES, TOKEN, VERSION)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<FoursquareSearch>>() {
                    @Override
                    public void onCompleted() {
                        mView.showProgress(false);
                        if (isRefresh) {
                            mView.onRefreshDone();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onRequestFail(e.toString());
                    }

                    @Override
                    public void onNext(Response<FoursquareSearch> response) {
                        FoursquareSearch data = response.body();
                        mView.onRequestSuccess(data.getResponse().getVenues());
                        Log.d("MainActivity", "Call Success");
                    }
                });
    }

}
