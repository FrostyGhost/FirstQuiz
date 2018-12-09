package com.fg.frostyghost.firstquizpj.Menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fg.frostyghost.firstquizpj.Levels.FilmsLevels;
import com.fg.frostyghost.firstquizpj.LevelsSettings.Connector;
import com.fg.frostyghost.firstquizpj.LevelsSettings.Level;
import com.fg.frostyghost.firstquizpj.LevelsSettings.LevelsDataBase;
import com.fg.frostyghost.firstquizpj.R;
import com.fg.frostyghost.firstquizpj.Levels.FilmsLevels;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Collections;

public class PlayActivity extends Activity implements View.OnClickListener{

    private SharedPreferences attemptsPreferecnes;

    private static final String SINGLE_LOAD = "SingleLoad";
    public static final String ATTEMPTS_SAVES = "AttemptsSaves";
    public static final String ATTEMPTS_CURRENT_SAVE = "AttemptsCurrentSave";

    private static final int ATTEMPTS_LIMIT = 3;
    private static int ATTEMPTS_CURRENT;

    private static final int TIME_LIMIT = 30 * 1000;

    private ImageView emojiView_1;
    private ImageView emojiView_2;
    private ImageView emojiView_3;

    private Button btnAnswer_1;
    private Button btnAnswer_2;
    private Button btnAnswer_3;
    private Button btnAnswer_4;

    private TextView tvTimer;
    private TextView tvAttempts;

    private String trueAnswer;

    private LevelsDataBase levelsDataBase;
    private Level newLevel;

    private CountDownTimer countDownTimer;

    //sound
    final String LOG_TAG = "myLogs";
    final int MAX_STREAMS = 3;

    SoundPool sp;
    int soundIdLose;
    int soundIdWin;

    int streamIDShot;
    int streamIDExplosion;

    private AdView mAdView2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);

        // Одиночна загрузка даних рівнів.
        loadData();

        attemptsPreferecnes = getSharedPreferences(ATTEMPTS_SAVES, MODE_PRIVATE);
        ATTEMPTS_CURRENT = attemptsPreferecnes.getInt(ATTEMPTS_CURRENT_SAVE, 1);

        levelsDataBase = new LevelsDataBase(this);

        emojiView_1 = findViewById(R.id.emoji_1);
        emojiView_2 = findViewById(R.id.emoji_2);
        emojiView_3 = findViewById(R.id.emoji_3);

        btnAnswer_1 = findViewById(R.id.btn_answer_1);
        btnAnswer_2 = findViewById(R.id.btn_answer_2);
        btnAnswer_3 = findViewById(R.id.btn_answer_3);
        btnAnswer_4 = findViewById(R.id.btn_answer_4);

        tvTimer = findViewById(R.id.tvTimer);
        tvAttempts = findViewById(R.id.tvAttempts);

        playConnector();

        //sound

        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);

        soundIdLose = sp.load(this, R.raw.lose, 1);
        soundIdWin = sp.load(this, R.raw.win, 1);


        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544~3347511713");

        mAdView2 = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest);
    }

    private void playConnector(){

        String strAttemptsOut = String.valueOf(ATTEMPTS_CURRENT) + "/" + String.valueOf(ATTEMPTS_LIMIT);
        tvAttempts.setText(strAttemptsOut);

        Connector connector = new Connector(this);
        newLevel = connector.getRandomLevel();
        trueAnswer = newLevel.getTrueAnswer();

        emojiView_1.setImageDrawable(getDrawableByString(newLevel.getEmojiId_1()));
        emojiView_2.setImageDrawable(getDrawableByString(newLevel.getEmojiId_2()));
        emojiView_3.setImageDrawable(getDrawableByString(newLevel.getEmojiId_3()));

        ArrayList<String> answers = new ArrayList<>();
        answers.add(newLevel.getTrueAnswer());
        answers.add(newLevel.getFalseAnswer_1());
        answers.add(newLevel.getFalseAnswer_2());
        answers.add(newLevel.getFalseAnswer_3());

        Collections.shuffle(answers);

        btnAnswer_1.setText(answers.get(0).toUpperCase());
        btnAnswer_2.setText(answers.get(1).toUpperCase());
        btnAnswer_3.setText(answers.get(2).toUpperCase());
        btnAnswer_4.setText(answers.get(3).toUpperCase());

        countDownTimer = new CountDownTimer( (long) TIME_LIMIT, 1000) {
            @Override
            public void onTick(long l) {
                tvTimer.setText(String.valueOf((int) l/1000));


            }

            @Override
            public void onFinish() {
                levelLose();
            }
        };
        countDownTimer.start();
    }

    private Drawable getDrawableByString(String imageName){
        Resources resources = getResources();
        int resID = resources.getIdentifier(imageName, "drawable", getPackageName());
        return resources.getDrawable(resID);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_answer_1:
                if(btnAnswer_1.getText().toString().toUpperCase().compareTo(trueAnswer.toUpperCase())==0)
                    levelWin();
                else
                    levelLose();
                break;
            case R.id.btn_answer_2:
                if(btnAnswer_2.getText().toString().toUpperCase().compareTo(trueAnswer.toUpperCase())==0)
                    levelWin();
                else
                    levelLose();
                break;
            case R.id.btn_answer_3:
                if(btnAnswer_3.getText().toString().toUpperCase().compareTo(trueAnswer.toUpperCase())==0)
                    levelWin();
                else
                    levelLose();
                break;
            case R.id.btn_answer_4:
                if(btnAnswer_4.getText().toString().toUpperCase().compareTo(trueAnswer.toUpperCase())==0)
                    levelWin();
                else
                    levelLose();
                break;
        }
    }

    private void levelWin() {
        countDownTimer.cancel();

        levelsDataBase.updateStatus(newLevel.getId(), 1);
        playConnector();

        //
        sp.play(soundIdWin, 0.1f, 0.1f, 0, 0, 1);

        Toast.makeText(this, "win", Toast.LENGTH_SHORT).show();
    }

    private void levelLose() {
        countDownTimer.cancel();

        //
        sp.play(soundIdLose, 0.1f, 0.1f, 0, 0, 1);

        if(ATTEMPTS_LIMIT == ATTEMPTS_CURRENT)
        {
            Intent intent = new Intent(this, ShopActivity.class);
            startActivity(intent);

            // TO DO


        }else{
            attemptFall();
            playConnector();
        }

        Toast.makeText(this, "lose", Toast.LENGTH_SHORT).show();
    }

    private void loadData(){
        SharedPreferences sp = getSharedPreferences(SINGLE_LOAD, Context.MODE_PRIVATE);
        boolean hasVisited = sp.getBoolean("hasVisited2", false);

        if (!hasVisited) {
            FilmsLevels filmsLevels = new FilmsLevels(this);

            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited2", true);
            e.commit();
        }
    }

    private void attemptFall(){
        ATTEMPTS_CURRENT++;
        SharedPreferences.Editor e = attemptsPreferecnes.edit();
        e.putInt(ATTEMPTS_CURRENT_SAVE, ATTEMPTS_CURRENT);
        e.commit();
    }
}