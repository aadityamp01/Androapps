package Guzzlecode.IntentCaller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import Guzzlecode.IntentCaller.R;


public class WebFragment extends Fragment {

    WebView wvLoadWeb;
    WebSettings wvSettings;

    EditText etPutURL;


    public WebFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        wvLoadWeb = (WebView)view.findViewById(R.id.wvWeb);
        etPutURL = (EditText)view.findViewById(R.id.edtInputURL);

        etPutURL.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    String getURL = etPutURL.getText().toString();
                    loadWebsite(getURL);
                    wvLoadWeb.setVisibility(View.VISIBLE);

                    etPutURL.setText("");

                    return true;
                }
                return false;
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void loadWebsite(String setURL) {
        wvSettings = wvLoadWeb.getSettings();
        wvSettings.setJavaScriptEnabled(true);

        wvLoadWeb.setWebViewClient(new WebViewClient());
        wvLoadWeb.loadUrl("https://" + setURL + "/");
    }
}