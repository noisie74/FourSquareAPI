package mikhail.com.foursquareapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FourSquareAdapter fourSquareAdapter;
    private List<FoursquareSearch.response.venues> venues;

    public final String coordinates = "37.809,-122.273";
    public final String token = "JCWKUHGL0GRSEKVGC2A5TAOAAAR3S1AXIW0CLTYTJWYPCD55";
    public final String version = "20160618";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViews();
        getPlaces();
    }

    private void setViews() {
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }


    private void getPlaces() {


        FourSquareAPI.getNearPlace places = FourSquareAPI.create();

        Call<List<FoursquareSearch.response.venues>> call = places.searchResults(coordinates, token, version);

        call.enqueue(new Callback<List<FoursquareSearch.response.venues>>() {
            @Override
            public void onResponse(Call<List<FoursquareSearch.response.venues>> call, Response<List<FoursquareSearch.response.venues>> response) {

                venues = response.body();
                fourSquareAdapter = new FourSquareAdapter(venues);
                recyclerView.setAdapter(fourSquareAdapter);
                fourSquareAdapter.notifyDataSetChanged();
                Log.d("MainActivity", "Call success!");

            }

            @Override
            public void onFailure(Call<List<FoursquareSearch.response.venues>> call, Throwable t) {
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
