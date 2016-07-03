package mikhail.com.foursquareapi.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.List;

/**
 * Created by Mikhail on 6/17/16.
 */
@Parcel(Parcel.Serialization.BEAN)
public class Venue {

    @SerializedName("id")
    String venueID;
    @SerializedName("name")
    String venueName;
    @SerializedName("url")
    String venueUrl;

    @ParcelConstructor
    public Venue(String venueName, String venueUrl){
        this.venueName = venueName;
        this.venueUrl = venueUrl;
    }

    public Venue() {
    }

    private List<Categories> categories;

    public String getVenueID() {
        return venueID;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueUrl() {
        return venueUrl;
    }

    public void setVenueUrl(String venueUrl) {
        this.venueUrl = venueUrl;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
}
