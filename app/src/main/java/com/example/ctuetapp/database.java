package com.example.ctuetapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    public database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    // insert + update + delete
    public void themDuLieu(String sql){
        SQLiteDatabase dulieu = getWritableDatabase();
        dulieu.execSQL(sql);
    }
    // select
    public Cursor layDuLieu(String sql){
        SQLiteDatabase dulieu = getReadableDatabase();
        return dulieu.rawQuery(sql, null);
    }
    // luu anh
    public void update_ANH(String masv , byte[] hinh){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "update SinhVien set anh = ? where mSv = '"+masv+"' ";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1,hinh);

        statement.executeUpdateDelete();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
