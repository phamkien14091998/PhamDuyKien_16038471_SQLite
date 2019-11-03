package com.example.phamduykien_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.DatabaseMetaData;

public class DBSV extends SQLiteOpenHelper {

    public DBSV(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public  void query(String sql){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }public Cursor queryCursor(String sql){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.rawQuery(sql, null);
    }
    public long insert(SinhVien sinhVien) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mssv", sinhVien.getMssv());
        values.put("tenSV", sinhVien.getTenSV());
        return sqLiteDatabase.insert("SinhVien", null, values);
    }

    public int update(SinhVien sinhVien) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mssv", sinhVien.getMssv());
        // id nos tuwj taoj , neen khoong theem ok id không sửa đc
        // ,ộn
        values.put("tenSV", sinhVien.getTenSV());
        return sqLiteDatabase.update("SinhVien", values,"id="+sinhVien.getId(), null);
    }

    public int delete(SinhVien sinhVien) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase(); // mssv cais nayf phair
        return sqLiteDatabase.delete("SinhVien","id="+sinhVien.getId(), null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}