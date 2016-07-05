package mikhail.com.foursquareapi.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Mikhail on 6/17/16.
 */

public class Categories {

    //    @SerializedName("name")
    String name;
    Icon icon;

    public Categories() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}
