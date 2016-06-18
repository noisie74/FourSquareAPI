package mikhail.com.foursquareapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mikhail on 6/17/16.
 */
public class Places {

    public Response response;

    public class Response{
        public List<Groups> groups;


        public class Groups{

            public List<Items> items;

            public class Items{

                public List<Venue> venues;

                public class Venue{

                }

                public class Categories {

                    public String name;

                    public icon icon;

                    public class icon {
                        @SerializedName("suffix")
                        public String iconID;
                        @SerializedName("prefix")
                        public String iconURL;
                    }
                }
            }


        }

    }

}
