package mikhail.com.foursquareapi.model;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Mikhail on 6/17/16.
 */

public class Categories {

    @SerializedName("name")
    String categoryName;
    Icon icon;

//    public Categories() {
//    }


    public String getCategoryName() {
        return categoryName;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

}
