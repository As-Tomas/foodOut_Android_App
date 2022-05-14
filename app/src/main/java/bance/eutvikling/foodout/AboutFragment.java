package bance.eutvikling.foodout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class AboutFragment extends Fragment {

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_about, container, false);

        WebView webview1= view.findViewById(R.id.webView);
        webview1.getSettings().setJavaScriptEnabled(true);

        //kad nekviestu kitos narsykles o pats rodytu ir veiktu puslapyje linkai
        webview1.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });

        webview1.loadUrl("https://github.com/As-Tomas/foodOut_Android_App/tree/Clean_version1");

        return view;
    }
}
