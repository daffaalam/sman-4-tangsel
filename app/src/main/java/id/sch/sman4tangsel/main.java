package id.sch.sman4tangsel;

import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class main extends AppCompatActivity {

    WebView vcr_web;
    WebSettings web_set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isNetworkAvailable()){
            popup();
        }
        if (isNetworkAvailable()){
            load_url();
        }

    }

    public void load_url() {
        vcr_web = (WebView) findViewById(R.id.web_vcr);
        web_set = vcr_web.getSettings();
        web_set.setJavaScriptEnabled(true);
        vcr_web.setWebViewClient(new WebViewClient());
        vcr_web.loadUrl("http://sman4tangsel.sch.id");
    }

    public void popup() {
        AlertDialog.Builder alrtbuild = new AlertDialog.Builder(main.this);
        alrtbuild
                .setTitle("WARNING!")
                .setMessage("Pastikan Perangkat Anda Terhubung Internet")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!isNetworkAvailable()){
                            popup();
                        }
                        if (isNetworkAvailable()){
                            load_url();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog alrt = alrtbuild.create();
        alrt.show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
