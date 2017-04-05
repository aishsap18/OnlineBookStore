package com.example.aishwarya.onlinebookstore;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import static com.example.aishwarya.onlinebookstore.DisplayBookFragment.pd;

/**
 * Created by aishwarya on 04-Apr-17.
 */

public class DisplayBookFragment extends Fragment {

    WebView document;
    public static ProgressDialog pd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.display_book_fragment, container, false);
        UserHomeActivity.title.setText("Display Book");

        document = (WebView) mainView.findViewById(R.id.xBookWebView);

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading document");
        document.setWebViewClient(new AppWebViewClients());
        document.getSettings().setJavaScriptEnabled(true);
        document.getSettings().setUseWideViewPort(true);
        //document.loadUrl("http://10.0.2.2:8081/OnlineBookStore/books/How%20It%20Works%20-%20Amazing%20Chemistry%20-%20Book%201%20(2015).pdf");
        document.loadUrl("http://docs.google.com/gview?embedded=true&url="+"www.google.com");
        return mainView;
    }

}
class AppWebViewClients extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        // TODO Auto-generated method stub
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        // TODO Auto-generated method stub
        super.onPageFinished(view, url);

        if (pd.isShowing()) {
            pd.dismiss();
            pd = null;
        }

    }
}
