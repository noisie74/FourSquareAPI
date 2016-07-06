package mikhail.com.foursquareapi.model;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikhail on 6/17/16.
 */
public class Venue implements Parcelable{

    String id;
    String name;
    String url;
    ArrayList<CategoriesObj> categories;
//
//    @ParcelConstructor
//    public Venue(String name, String url) {
//        this.name = name;
//        this.url = url;
//    }

//     @ParcelConstructor
//    public Venue(List<CategoriesObj> categories) {
//        this.categories = categories;
//    }

    public Venue() {
    }

    public String getVenueID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setVenueName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

//    public void setVenueUrl(String venueUrl) {
//        this.venueUrl = venueUrl;
//    }

    public ArrayList<CategoriesObj> getCategoriesObj() {
        return categories;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {

    }
}
