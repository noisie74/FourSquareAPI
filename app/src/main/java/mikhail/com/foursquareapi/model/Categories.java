package mikhail.com.foursquareapi.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mikhail on 6/17/16.
 */


public class Categories {

    @SerializedName("name")
    private String categoryName;
    private Icon icon;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}
