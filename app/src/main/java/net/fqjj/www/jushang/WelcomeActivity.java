package net.fqjj.www.jushang;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Window;

public class WelcomeActivity extends ActionBarActivity {

    private SharedPreferences prefs;
    private boolean isFirstLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        prefs = PreferenceManager.getDefaultSharedPreferences(WelcomeActivity.this);
        isFirstLogin = prefs.getBoolean("isFirstLogin", true);

        // 演示BannerActivity效果
        isFirstLogin = true;

        startBoundsActivity();
    }

    private void startBoundsActivity() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (isFirstLogin) {
                    startActivity(new Intent(WelcomeActivity.this, BannerActivity.class));
                } else {
                    startActivity(new Intent(WelcomeActivity.this, Main.class));

                }
                finish();
            }
        }, 1500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


}
