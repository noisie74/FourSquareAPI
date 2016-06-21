package mikhail.com.foursquareapi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mikhail.com.foursquareapi.R;
import mikhail.com.foursquareapi.interfaces.IClickItem;
//import mikhail.com.foursquareapi.model.Categories;
import mikhail.com.foursquareapi.model.FoursquareSearch;
import mikhail.com.foursquareapi.util.CircleTransform;
//import mikhail.com.foursquareapi.model.Response;
//import mikhail.com.foursquareapi.model.Venue;

/**
 * Created by Mikhail on 6/17/16.
 */
public class FourSquareAdapter extends RecyclerView.Adapter<FourSquareAdapter.ViewHolder> {

    private IClickItem iClickItem;
    private ArrayList<FoursquareSearch.response> venues;
    public Context context;


    public FourSquareAdapter(ArrayList<FoursquareSearch.response> venues) {
        this.venues = venues;
//        this.iClickItem = iClickItem;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.place_name)
        public TextView placeName;
        @BindView(R.id.place_category)
        public TextView placeCategory;
        @BindView(R.id.place_icon)
        public ImageView placeIcon;

        public View parentView;

        public ViewHolder(View view) {
            super(view);
            this.parentView = view;


            try {
                ButterKnife.bind(this, view);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.content_main, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.parentView.setTag(holder);
        holder.placeName.setText(venues.get(position).venues.get(position).name);
        holder.placeCategory.setText(venues.get(position).venues.get(position).categories.get(position).name);

        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Glide.with(context).load(venues.get(position).venues.get(position).categories.get(position).icon.prefix +
                venues.get(position).venues.get(position).categories.get(position).icon.suffix)
                .placeholder(R.drawable.placeholder)
                .transform(new CircleTransform(context))
                .into(holder.placeIcon);

    }

    @Override
    public int getItemCount() {
        return venues.size();
    }


}


