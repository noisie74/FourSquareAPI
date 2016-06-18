package mikhail.com.foursquareapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import mikhail.com.foursquareapi.adapter.FourSquareAdapter;
import mikhail.com.foursquareapi.api.FourSquareAPI;
import mikhail.com.foursquareapi.model.Items;
import mikhail.com.foursquareapi.model.Places;
import mikhail.com.foursquareapi.model.Venue;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity   {

    private RecyclerView recyclerView;
    private FourSquareAdapter fourSquareAdapter;
    private ArrayList<Venue> venues;

    public final String coordinates = "37.809,-122.273";
    public final String token = "JCWKUHGL0GRSEKVGC2A5TAOAAAR3S1AXIW0CLTYTJWYPCD55";
    public final String version ="20160617";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViews();
        fourSquareApiCall();
    }

    private void setViews(){
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        venues = new ArrayList<>();
        fourSquareAdapter = new FourSquareAdapter(venues);
    }

    public void fourSquareApiCall() {
        final FourSquareAPI.FourSquareRX places = FourSquareAPI.createRx();

        Observable<Response<Places>> observable =
                places.fourSquarePlaces(coordinates,token,version);

        observable.observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<Response<Places>>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("MainActivity", "Call failed!");

                    }

                    @Override
                    public void onNext(Response<Places> fourSquarePlaces) {
                        Log.d("MainActivity", "Call success!");

                        for (int i = 0; i < fourSquarePlaces.body().response.groups.size();i++){
                            venues = (ArrayList<Venue>) fourSquarePlaces.body().response.groups.get(i).items.get(i).venues;
                            fourSquareAdapter = new FourSquareAdapter(venues);
                            recyclerView.setAdapter(fourSquareAdapter);
                            fourSquareAdapter.notifyDataSetChanged();
                        }

//                        venues = (ArrayList<Venue>) fourSquarePlaces.body().response.groups.get(0).items.get(0).venues;

                    }
                });
    }

}
