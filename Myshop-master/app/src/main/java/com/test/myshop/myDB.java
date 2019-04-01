package com.test.myshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDB extends SQLiteOpenHelper {

    private static final String DB_NAME= "db_name ";
    private static final String USER_TABLE = "phonee";
    private static final String COMMENT_TABLE = "comments";
    private static final String GOODS_TABLE = "goods";
    private static final int DB_VERSION = 1;

    public myDB(Context context, String phone, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, phone, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //用户信息
        String CREATE_TABLE = "CREATE TABLE if not exists "
                + USER_TABLE
                + " (_id INTEGER PRIMARY KEY, name TEXT, password TEXT, image BLOB, likes TEXT,phone TEXT)";
        db.execSQL(CREATE_TABLE);

        //商品信息
        CREATE_TABLE = "CREATE TABLE if not exists "
                +GOODS_TABLE
                +"(_id INTEGER PRIMARY KEY AUTOINCREMENT,image1 BLOB,image2 BLOB,image3 BLOB,name TEXT,spinner TEXT,price REAL,phone TEXT,notes TEXT)";
        db.execSQL(CREATE_TABLE);

        CREATE_TABLE = "CREATE TABLE if not exists "
                + COMMENT_TABLE
                + " (_id INTEGER PRIMARY KEY, name TEXT, time TEXT, comment TEXT, likenum INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertuser(SQLiteDatabase db, String phone, String name,String pwd) {
        ContentValues values = new ContentValues();
        values.put("phone", phone);
        values.put("name", name);
        values.put("password", pwd);
        db.insert(USER_TABLE, null, values);
    }

    public Cursor queryrawuser(SQLiteDatabase db, String phone) {
        String sql = "select * from " + USER_TABLE + " where phone = " + "\"" + phone + "\"";
        return db.rawQuery(sql, null);
    }

    public void insertGood(SQLiteDatabase db, byte[] image1, byte[] image2, byte[] image3, String name, String spinner, float price, String phone, String notes) {
        ContentValues values = new ContentValues();
        values.put("image1", image1);
        values.put("image2", image2);
        values.put("image3", image3);
        values.put("name", name);
        values.put("spinner", spinner);
        values.put("price", price);
        values.put("phone", phone);
        values.put("notes", notes);
        db.insert(GOODS_TABLE, null, values);
    }

    public Cursor querygoods(SQLiteDatabase db, String phone) {
        String sql = "select * from " + GOODS_TABLE + "where phone = " + "\"" + phone + "\"";
        return db.rawQuery(sql, null);
    }

    public Cursor getallgoods(SQLiteDatabase db) {
        String sql = "select * from " + GOODS_TABLE + " where _id >= 0";
        return db.rawQuery(sql, null);
    }

}
