package com.fg.frostyghost.firstquizpj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fg.frostyghost.firstquizpj.Menu.PlayActivity;
import com.fg.frostyghost.firstquizpj.Menu.ShopActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544~3347511713");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    //Перехід на лайаут безпосередньо гри
    public void goToPlayActivity (View view){
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }
    //Перехід на лайаут покупки нових рівнів
    public void goToShopLayout (View view){
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);
    }

}
