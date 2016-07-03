package mikhail.com.foursquareapi.presenter;

import mikhail.com.foursquareapi.MainActivity;
import mikhail.com.foursquareapi.api.FourSquareAPI;
import mikhail.com.foursquareapi.module.ApiComponent;
import mikhail.com.foursquareapi.module.ApiModule;

/**
 * Created by Mikhail on 7/2/16.
 */
public class GetPresenter {

    FourSquareAPI mApi;
    ApiComponent mApiComponent;
    MainActivity mView;

    public GetPresenter(MainActivity view) {
        this.mView = view;
        mApiComponent = DaggerFourSquareComponent.builder()
                .apiModule(new ApiModule())
                .build();
        mApi = mApiComponent.();
    }

}
