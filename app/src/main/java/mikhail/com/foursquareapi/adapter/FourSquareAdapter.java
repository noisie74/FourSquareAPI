package mikhail.com.foursquareapi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mikhail.com.foursquareapi.R;
import mikhail.com.foursquareapi.interfaces.IClickItem;
//import mikhail.com.foursquareapi.model.Categories;
import mikhail.com.foursquareapi.model.FoursquareSearch;
//import mikhail.com.foursquareapi.model.Response;
//import mikhail.com.foursquareapi.model.Venue;

/**
 * Created by Mikhail on 6/17/16.
 */
public class FourSquareAdapter extends RecyclerView.Adapter<FourSquareAdapter.ViewHolder> {

//    private List<Venue> fourSquareVenues;
    private IClickItem iClickItem;
    private List<FoursquareSearch.response.venues> searchResults;

//    public FourSquareAdapter(List<Venue> fourSquareVenues){
//        this.fourSquareVenues = fourSquareVenues;
////        this.iClickItem = iClickItem;
//    }

    public FourSquareAdapter(List<FoursquareSearch.response.venues> venues){
        this.searchResults = venues;
//        this.iClickItem = iClickItem;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.place_name)
        public TextView placeName;
        @BindView(R.id.place_category)
        public TextView placeCategory;
        @BindView(R.id.place_icon)
        public ImageView placeIcon;

        public View parentView;

        public ViewHolder(View view){
            super(view);
            this.parentView = view;

            try {
                ButterKnife.bind(this,view);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_main, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        Venue data = fourSquareVenues.get(position);
//        Categories categories = data.categories.get(position);
//        FoursquareSearch.response data = searchResults.get(position).;
//        FoursquareSearch.response.venues.get(position) categories = data.categories.get(position).name;
//        bindData(data,holder);
        holder.placeName.setText(searchResults.get(position).name);
        holder.placeCategory.setText(searchResults.get(position).categories.get(position).name);

    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }


//    private void bindData(final Venue data, ViewHolder holder) {
//        holder.parentView.setTag(holder);
//        holder.placeName.setText(data.name);
////        holder.placeCategory.setText(categories.name);


//        holder.parentView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }


