package mikhail.com.foursquareapi.adapter;

import android.app.Activity;
import android.content.Context;
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
import mikhail.com.foursquareapi.model.Venue;

/**
 * Created by Mikhail on 7/2/16.
 */
public class ObjectAdapter<T> extends RecyclerView.Adapter<ObjectAdapter.MyViewHolder> {

    List<T> modelObject;
    List<Venue> venueList;
    IClickItem mIclickItem;
    Context context;
    private Activity mActivity;

    public ObjectAdapter(Activity activity, List<T> modelObject, IClickItem mIclickItem) {
        this.mActivity = activity;
        this.modelObject = modelObject;
        this.mIclickItem = mIclickItem;

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.place_name)
        public TextView placeName;
        @BindView(R.id.place_category)
        public TextView placeCategory;
        @BindView(R.id.place_icon)
        public ImageView placeIcon;

        public View parentView;

        public MyViewHolder(View view) {
            super(view);
            this.parentView = view;

            try {
                ButterKnife.bind(this, view);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setVenueList(List<Venue> list) {
        this.venueList = list;
    }

    public void onRelease() {
        if (venueList != null) {
            venueList.clear();
            venueList = null;
        }
        if (mActivity != null) {
            mActivity = null;
        }
    }

    @Override
    public ObjectAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.fragment_main, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ObjectAdapter.MyViewHolder holder, final int position) {
        T modelObject = this.modelObject.get(position);

        holder.placeName.setText(modelObject.toString());
        holder.placeCategory.setText(modelObject.toString());

        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIclickItem != null){
                    mIclickItem.onClick(venueList.get(position));
                }
            }
        });


    }

    public void bindData(List<Venue> venueList, MyViewHolder holder){

//        Glide.with(context).load(venueList.get(0).getCategories().get(0).getIcon().getPrefix())
//                .placeholder(R.drawable.placeholder)
//                .transform(new CircleTransform(context)
//                .into(place);
    }

    @Override
    public int getItemCount() {
        return modelObject.size();
    }
}
