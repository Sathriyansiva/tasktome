package com.example.vcssolution.vcs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by venu sankaran on 1/1/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="DemoDataBase";

//    public static final String KEY_ID="id";

    public static final String TABLE_NAME="demoTable";

    public static final String KEY_Name="name";

    public static final String KEY_PhoneNumber="phone_number";
    public static final String KEY_FatherName="fathername";
    public static final String KEY_Nationality="nationality";
    public static final String KEY_Address="address";
    public static final String KEY_Sex="sex";
    public static final String KEY_dob="dob";
//    public static final String KEY_Subject="subject";

    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ( "+KEY_Name+" VARCHAR, "+KEY_FatherName+" VARCHAR,"+KEY_PhoneNumber+" VARCHAR,"+KEY_Nationality+" VARCHAR, "+KEY_Sex+" VARCHAR,"+KEY_dob+" VARCHAR,"+KEY_Address+" VARCHAR)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

}
