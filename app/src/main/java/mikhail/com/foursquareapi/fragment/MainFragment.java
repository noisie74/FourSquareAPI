package mikhail.com.foursquareapi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mikhail.com.foursquareapi.R;
import mikhail.com.foursquareapi.adapter.ObjectAdapter;
import mikhail.com.foursquareapi.interfaces.IClickItem;
import mikhail.com.foursquareapi.interfaces.ILoadData;
import mikhail.com.foursquareapi.model.Venue;

/**
 * Created by Mikhail on 7/3/16.
 */
public class MainFragment extends BaseFragment {

    public static final String EXTRAS_LIST = "venue_list";
    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    ObjectAdapter mAdapter;

    IClickItem mIClickItem;
    ILoadData mILoadData;

    ArrayList<Venue> list;

    public static MainFragment createNewVenueFragment(ArrayList<Venue> listVenue){
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRAS_LIST, Parcels.wrap(listVenue));
        fragment.setArguments(bundle);
        return  fragment;
    }


    public void setIClickItem(IClickItem iClickItem){
        this.mIClickItem = iClickItem;
    }

    public void setILoadData(ILoadData iLoadData){
        this.mILoadData = iLoadData;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        ButterKnife.bind(this, view);

        Bundle bun = getArguments();
        list = Parcels.unwrap(bun.getParcelable(EXTRAS_LIST));

        initAdapter();
        initSwipeLayout();

        getActivity().setTitle(R.string.app_name);
        return view;
    }

    public void initAdapter(){

        if (mAdapter != null){

            mAdapter = new ObjectAdapter(this.getActivity(), list, mIClickItem);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        if (mAdapter != null) {
            mAdapter.onRelease();
            mAdapter = null;
        }

        super.onDestroy();
    }


    public void onRefreshDone(){
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void onRequestSuccess(List<Venue> listVenue) {
        mAdapter.setVenueList(listVenue);
        mAdapter.notifyDataSetChanged();
    }

    private void initSwipeLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mILoadData !=null){
                    mILoadData.onLoadData();
                }
            }
        });
    }
}
