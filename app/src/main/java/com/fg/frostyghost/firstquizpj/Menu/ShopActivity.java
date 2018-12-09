package com.fg.frostyghost.firstquizpj.Menu;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.fg.frostyghost.firstquizpj.R;
import com.fg.frostyghost.firstquizpj.MainActivity;

public class ShopActivity extends Activity {

    ConstraintLayout shop_lyt;
    TextView time1, time2;


    //sound
    final String LOG_TAG = "myLogs";
    final int MAX_STREAMS = 3;

    SoundPool sp;
    int soundIdShot;
    int soundIdExplosion;


    int streamIDShot;
    int streamIDExplosion;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);

        //получаэмо контейнер
        shop_lyt = findViewById(R.id.shop_layout);
        //затемняючий фон
        //textview для таймера
        time1 =findViewById(R.id.timer);
        time2 =findViewById(R.id.timer2);


        //shop_lyt.removeView(btn_mask2);
        shop_lyt.removeView(time1);
        //shop_lyt.removeView(time2);


        //щоб створити знову картинку
        //shop_lyt.addView(btn_mask);


        //sound
        Button button= findViewById(R.id.button);

        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);

        soundIdShot = sp.load(this, R.raw.lose, 1);
        Log.d(LOG_TAG, "soundIdShot = " + soundIdShot);
    }

    public void onClick(View view) {
        sp.play(soundIdShot, 0.1f, 0.1f, 0, 0, 1);
//        sp.play(soundIdExplosion, 0.1f, 0.1f, 0, 0, 1);
    }

    // Викл кнопки "Назад".
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //Тимчасове рішення для тестування лайаутів
    public void onBack2 (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}

