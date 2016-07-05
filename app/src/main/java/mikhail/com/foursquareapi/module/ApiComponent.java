package mikhail.com.foursquareapi.module;

import javax.inject.Singleton;

import dagger.Component;
import mikhail.com.foursquareapi.api.FourSquareAPI;

/**
 * Created by Mikhail on 7/2/16.
 */

@Singleton
@Component(modules = {ApiModule.class})
public interface ApiComponent {

    FourSquareAPI provideApiService();

}
