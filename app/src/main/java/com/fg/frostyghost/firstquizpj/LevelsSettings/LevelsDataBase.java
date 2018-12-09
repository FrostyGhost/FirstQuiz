package com.fg.frostyghost.firstquizpj.LevelsSettings;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LevelsDataBase extends SQLiteOpenHelper {
    // Ім'я БД
    public static final String DATABASE_NAME = "LevelsData2";
    // Назва таблиці
    public static final String LEVELS_TABLE_NAME = "levels";
    // Стовпці таблиці
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMOJI_1 = "emoji_1";
    public static final String COLUMN_EMOJI_2 = "emoji_2";
    public static final String COLUMN_EMOJI_3 = "emoji_3";
    public static final String COLUMN_TRUE_ANSWER = "trueAnswer";
    public static final String COLUMN_FALSE_ANSWER_1 = "falseAnswer_1";
    public static final String COLUMN_FALSE_ANSWER_2 = "falseAnswer_2";
    public static final String COLUMN_FALSE_ANSWER_3 = "falseAnswer_3";
    public static final String COLUMN_LEVELSTATUS = "levelStatus";

    // Конструктор
    public LevelsDataBase(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*Форма таблиці:
         * ID | Емодж 1 | Емоджі 2 | Емоджі 3 | Правильна відповідь | Неправильна відповідь 1 | Неправильна відповідь 2 | Неправильна відповідь 3 | Статус рівня (Пройдений/Непройдений)
         * Назва талиці - LEVELS_TABLE_NAME - levels*/
        sqLiteDatabase.execSQL(
                "create table "+LEVELS_TABLE_NAME+
                        "("+COLUMN_ID+" integer primary key autoincrement, "
                        +COLUMN_EMOJI_1+" text, "+COLUMN_EMOJI_2+" text,"+COLUMN_EMOJI_3+" text, "
                        +COLUMN_TRUE_ANSWER+" text, "
                        +COLUMN_FALSE_ANSWER_1+" text, "+COLUMN_FALSE_ANSWER_2+" text, "+COLUMN_FALSE_ANSWER_3+" text, "
                        +COLUMN_LEVELSTATUS+" integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }

    // Метод getRowById повертає рядок типу (курсор), викликаними по id.
    public Cursor getRowById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select * from "+LEVELS_TABLE_NAME+" where id= "+COLUMN_ID, null );
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select * from " +LEVELS_TABLE_NAME, null );
    }

    public void addRowData(String em1, String em2, String em3, String trueAnswr, String falseAnswr1, String falseAnswr2, String falseAnswr3, int boolStatus){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_EMOJI_1, em1);
        cv.put(COLUMN_EMOJI_2, em2);
        cv.put(COLUMN_EMOJI_3, em3);
        cv.put(COLUMN_TRUE_ANSWER, trueAnswr);
        cv.put(COLUMN_FALSE_ANSWER_1, falseAnswr1);
        cv.put(COLUMN_FALSE_ANSWER_2, falseAnswr2);
        cv.put(COLUMN_FALSE_ANSWER_3, falseAnswr3);
        cv.put(COLUMN_LEVELSTATUS, boolStatus);

        db.insert(LEVELS_TABLE_NAME,null, cv);
        db.close();
    }

    public void updateStatus(int id, int value){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_LEVELSTATUS, value);

        db.update(LEVELS_TABLE_NAME, cv, "_id="+id, null);
    }

}