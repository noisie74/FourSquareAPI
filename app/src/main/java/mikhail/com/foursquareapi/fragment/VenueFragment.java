package mikhail.com.foursquareapi.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.parceler.Parcel;
import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import mikhail.com.foursquareapi.MainActivity;
import mikhail.com.foursquareapi.R;
import mikhail.com.foursquareapi.model.Venue;

/**
 * Created by Mikhail on 7/3/16.
 */
public class VenueFragment extends BaseFragment {

    public static final String EXTRAS_DETAILS = "extras_venue_url";

    @BindView(R.id.venue_web_view)
    public WebView venueView;

    @BindView(R.id.progress_bar)
    public ProgressBar progress;

    public String venueURL;
    public String htmlString;


    public static VenueFragment createNewVenueFragment(Venue venue) {
        VenueFragment fragment = new VenueFragment();
        Bundle bundle = new Bundle();
//        bundle.putParcelable(EXTRAS_DETAILS, Parcels.wrap(venueURL));

        bundle.putParcelable(EXTRAS_DETAILS, venue);

        fragment.setArguments(bundle);
        return fragment;
    }

//    public void setVenueURL(String venueURL) {
//        this.venueURL = venueURL;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_venue, container, false);
        ButterKnife.bind(this, view);

        Venue venue = getArguments().getParcelable(EXTRAS_DETAILS);
        if (venue != null) {
            String url = venue.getUrl();
            loadWebView(url);
        } else
            Toast.makeText(getContext(),"No URL Available for this place",Toast.LENGTH_SHORT).show();

//       String venueURL = getArguments().getParcelable(EXTRAS_DETAILS);

            return view;
    }


    public void loadWebView(String venueURL) {
        WebSettings webSettings = venueView.getSettings();
        venueView.setWebViewClient(new WebViewClientDemo()); //opens url in app, not in default browser
        webSettings.setJavaScriptEnabled(true); //turn js on for hacking and giving better ux
        venueView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");

        venueView.loadUrl(venueURL);
    }


    private class WebViewClientDemo extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            venueView.loadUrl("javascript:window.HTMLOUT.showHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            progress.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progress.setVisibility(View.VISIBLE);
        }
    }

    public class MyJavaScriptInterface {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void showHTML(String html) {
            htmlString = html;
        }
    }
}
