package mikhail.com.foursquareapi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mikhail.com.foursquareapi.R;
import mikhail.com.foursquareapi.interfaces.IClickItem;
import mikhail.com.foursquareapi.model.Items;
import mikhail.com.foursquareapi.model.Places;
import mikhail.com.foursquareapi.model.Venue;

/**
 * Created by Mikhail on 6/17/16.
 */
public class FourSquareAdapter extends RecyclerView.Adapter<FourSquareAdapter.ViewHolder> {

    private List<Places> fourSquarePlaces;
    private IClickItem iClickItem;

    public FourSquareAdapter(List<Places> fourSquarePlaces, IClickItem iClickItem){
        this.fourSquarePlaces = fourSquarePlaces;
        this.iClickItem = iClickItem;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.place_name)
        public TextView placeName;
        @BindView(R.id.place_category)
        public TextView placeCategory;
        @BindView(R.id.place_icon)
        public TextView placeIcon;

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
        Venue data = fourSquarePlaces.get(position).
                response.groups.get(position).
                items.get(position).
                venues.get(position);
        bindData(data,holder);

    }

    @Override
    public int getItemCount() {
        return fourSquarePlaces.size();
    }


    private void bindData(final Venue data, ViewHolder holder) {
        holder.parentView.setTag(holder);
        holder.placeName.setText(data.name);
        holder.placeCategory.setText(data.categories.get(0).name);


        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
