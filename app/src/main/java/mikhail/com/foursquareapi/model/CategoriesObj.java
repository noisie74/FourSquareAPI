package mikhail.com.foursquareapi.model;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Mikhail on 6/17/16.
 */

public class CategoriesObj implements Parcelable {

    @SerializedName("name")
    String categoryName;
    Icon icon;

//    public CategoriesObj() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {

    }
}
