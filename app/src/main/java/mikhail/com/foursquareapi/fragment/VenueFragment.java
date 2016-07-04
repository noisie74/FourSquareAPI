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

import org.parceler.Parcel;
import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    public Venue venues;
    public String htmlString;


    public static VenueFragment createNewVenueFragment(Venue venues) {
        VenueFragment fragment = new VenueFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRAS_DETAILS, Parcels.wrap(venues));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_venue, container, false);
        ButterKnife.bind(this, view);
        venues = getArguments().getParcelable(EXTRAS_DETAILS);

        loadWebView(venues);
        return view;
    }


    public void loadWebView(Venue venues) {
        WebSettings webSettings = venueView.getSettings();
        venueView.setWebViewClient(new WebViewClientDemo()); //opens url in app, not in default browser
        webSettings.setJavaScriptEnabled(true); //turn js on for hacking and giving better ux
        venueView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");

        venueView.loadUrl(venues.getVenueUrl());
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
