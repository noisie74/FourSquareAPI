package mikhail.com.foursquareapi;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mikhail.com.foursquareapi.fragment.MainFragment;
import mikhail.com.foursquareapi.fragment.VenueFragment;
import mikhail.com.foursquareapi.interfaces.IClickItem;
import mikhail.com.foursquareapi.interfaces.ILoadData;
import mikhail.com.foursquareapi.model.Venue;
import mikhail.com.foursquareapi.presenter.GetPresenter;


public class MainActivity extends AppCompatActivity implements IClickItem, ILoadData {

    private static final String BACKSTACK = "MainActivity";
    @BindView(R.id.container)
    FrameLayout mViewContainer;
    @BindView(R.id.progress_bar)
    ProgressBar mLoading;

    private GetPresenter mPresenter;


    @Override
    public void onClick(Venue venue) {
        //control click item
        if (findViewById(R.id.venue_container) != null) {

            String url = venue.getUrl();
            getVenueFragment().loadWebView(url);
        } else {
            FragmentTransaction f = getSupportFragmentManager().beginTransaction();
            f.replace(R.id.container, VenueFragment.createNewVenueFragment(venue));
            f.addToBackStack(BACKSTACK);
            f.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new GetPresenter(this);
        mPresenter.loadVenues(false);
    }


    @Override
    public void onLoadData() {
        mPresenter.loadVenues(true);
    }


    public void showProgress(boolean isShow) {
        mLoading.setVisibility(isShow ? View.VISIBLE : View.GONE);

    }

    public void onRefreshDone() {
        getMainragment().onRefreshDone();
    }

    public void onRequestFail(String error) {
        Log.w("MainActivity", "Error: " + error);
        Toast.makeText(this, "Error. Please try again!", Toast.LENGTH_SHORT).show();

    }


    public void onRequestSuccess(ArrayList<Venue> listVenue) {
        String venueURL = null;
        if (listVenue.size() > 0)
            venueURL = listVenue.get(0).getUrl();

        MainFragment fragment = getMainragment();
        if (fragment == null) {

            initFragments(listVenue);
        } else {
            fragment.onRequestSuccess(listVenue);
            if (findViewById(R.id.venue_container) != null) {
                getVenueFragment().loadWebView(venueURL);
            }
        }
    }

    private void initFragments(ArrayList<Venue> list) {
        FragmentTransaction f = getSupportFragmentManager().beginTransaction();
        MainFragment mainFragment = MainFragment.createNewVenueFragment(list);
        mainFragment.setIClickItem(this);
        mainFragment.setILoadData(this);
        Log.d("MainActivity", "MainFragment is seen");
        f.add(R.id.container, mainFragment);
        // the fragment_container FrameLayout
        if (findViewById(R.id.venue_container) != null) {
            f.add(R.id.venue_container, VenueFragment.createNewVenueFragment(list.get(0)));
        }
        f.commit();

    }

    private MainFragment getMainragment() {
        MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        return fragment;
    }

    private VenueFragment getVenueFragment() {
        VenueFragment venueFragment = (VenueFragment) getSupportFragmentManager().findFragmentById(R.id.venue_container);
        return venueFragment;
    }

}
