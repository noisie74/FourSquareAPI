package mikhail.com.foursquareapi.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mikhail on 7/2/16.
 */
public class Icon {

    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

}
