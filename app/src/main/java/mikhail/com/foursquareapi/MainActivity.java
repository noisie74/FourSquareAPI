package mikhail.com.foursquareapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import mikhail.com.foursquareapi.adapter.FourSquareAdapter;
import mikhail.com.foursquareapi.api.FourSquareAPI;
import mikhail.com.foursquareapi.model.FoursquareSearch;
//import mikhail.com.foursquareapi.model.Items;
//import mikhail.com.foursquareapi.model.Places;
//import mikhail.com.foursquareapi.model.Venue;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    private FourSquareAdapter fourSquareAdapter;
    private ArrayList<FoursquareSearch.response> venues;

    public final String coordinates = "37.809,-122.273";
    public final String token = "JCWKUHGL0GRSEKVGC2A5TAOAAAR3S1AXIW0CLTYTJWYPCD55";
    public final String version = "20160620";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        getPlaces();
    }

    private void setViews() {
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        venues = new ArrayList<>();
//        fourSquareAdapter = new FourSquareAdapter(venues);
//        recyclerView.setAdapter(fourSquareAdapter);
    }


    private void getPlaces() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.foursquare.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FourSquareAPI.getNearPlace places = retrofit.create(FourSquareAPI.getNearPlace.class);

        Call<FoursquareSearch> call = places.searchResults(coordinates, token, version);

        call.enqueue(new Callback<FoursquareSearch>() {
            @Override
            public void onResponse(Call<FoursquareSearch> call, Response<FoursquareSearch> response) {

                FoursquareSearch foursquareSearch = response.body();

                Collections.addAll(venues, foursquareSearch.getResponse());

                fourSquareAdapter = new FourSquareAdapter(venues);
                recyclerView.setAdapter(fourSquareAdapter);
                fourSquareAdapter.notifyDataSetChanged();
                Log.d("MainActivity", "Call success!");

            }

            @Override
            public void onFailure(Call<FoursquareSearch> call, Throwable t) {
                Log.d("MainActivity", "Call Failed!" + t.getMessage());

            }
        });
    }


//
//    public void fourSquareApiCall() {
//        final FourSquareAPI.FourSquareRX places = FourSquareAPI.createRx();
//
//        Observable<Response<Places>> observable =
//                places.venues("explore",coordinates,token,version);
//
//        observable.observeOn(AndroidSchedulers.mainThread()).
//                subscribeOn(Schedulers.io()).
//                observeOn(AndroidSchedulers.mainThread()).
//                subscribe(new Subscriber<Response<Places>>() {
//                    @Override
//                    public void onCompleted() {
//
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d("MainActivity", "Call failed!");
//
//                    }
//
//                    @Override
//                    public void onNext(Response<Places> fourSquarePlaces) {
//                        Log.d("MainActivity", "Call success!");
//
//                        for (int i = 0; i < fourSquarePlaces.body().response.groups.size();i++){
//                            venues = (ArrayList<Venue>) fourSquarePlaces.body().response.groups.get(i).items.get(i).venues;
//                            fourSquareAdapter = new FourSquareAdapter(venues);
//                            recyclerView.setAdapter(fourSquareAdapter);
//                            fourSquareAdapter.notifyDataSetChanged();
//                        }
//
////                        venues = (ArrayList<Venue>) fourSquarePlaces.body().response.groups.get(0).items.get(0).venues;
//
//                    }
//                });
//    }

}
