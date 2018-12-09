package com.fg.frostyghost.firstquizpj.LevelsSettings;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Collections;

public class Connector {

    private Context context;
    private Cursor cursor;
    private ArrayList<Level> levList;

    public Connector(Context context) {
        this.context = context;

        LevelsDataBase db = new LevelsDataBase(context);
        cursor = db.getAllData();
        levList = new ArrayList<Level>();

        while (cursor.moveToNext()){
            levList.add(new Level(
                    cursor.getInt(cursor.getColumnIndex(LevelsDataBase.COLUMN_ID)),

                    cursor.getString(cursor.getColumnIndex(LevelsDataBase.COLUMN_EMOJI_1)),
                    cursor.getString(cursor.getColumnIndex(LevelsDataBase.COLUMN_EMOJI_2)),
                    cursor.getString(cursor.getColumnIndex(LevelsDataBase.COLUMN_EMOJI_3)),

                    cursor.getString(cursor.getColumnIndex(LevelsDataBase.COLUMN_TRUE_ANSWER)),

                    cursor.getString(cursor.getColumnIndex(LevelsDataBase.COLUMN_FALSE_ANSWER_1)),
                    cursor.getString(cursor.getColumnIndex(LevelsDataBase.COLUMN_FALSE_ANSWER_2)),
                    cursor.getString(cursor.getColumnIndex(LevelsDataBase.COLUMN_FALSE_ANSWER_3)),

                    cursor.getInt(cursor.getColumnIndex(LevelsDataBase.COLUMN_LEVELSTATUS))));
        }

        updateToStatus();
    }

    public Level getRandomLevel(){
        Collections.shuffle(levList);
        return levList.get(0);
    }

    private void updateToStatus(){
        for(int i = 0; i<levList.size(); i++){
            if(levList.get(i).getLevelStatus()==1) {
                levList.remove(i);
                i--;
            }
        }
    }

}

