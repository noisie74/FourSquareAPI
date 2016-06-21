package mikhail.com.foursquareapi.model;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by Mikhail on 6/17/16.
 */
public class FoursquareSearch {
    private response response;

    public FoursquareSearch.response getResponse() {
        return response;
    }


    public meta meta;

    public class meta {
        public int code;
    }

    public class response {

        private List<venues> venues;

        public List<FoursquareSearch.response.venues> getVenues() {
            return venues;
        }

        public class venues {
            public String id;
            public String name;
            public location location;
            public List<categories> categories;
            public Bitmap bitmap;

            public class location {
                public double lat;
                public double lng;
                public double distance;
                public String cc;
                public String country;
            }

            public class categories {

                public String id;
                public String name;
                public String pluralName;
                public String shortName;
                public icon icon;

                public class icon {
                    public String prefix;
                    public String suffix;
                }
            }
        }
    }

}
