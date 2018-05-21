package com.example.cleanarchitecture;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactosSQLiteHelper extends SQLiteOpenHelper {

    private String DATA_BASE_NAME = "Agenda";
    private int DATA_VERSION      = 1;
    String sqlCreate = "CREATE TABLE contactos ("+
            "id         INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre     TEXT, " +
            "telefono   TEXT," +
            "correo     TEXT)";

    public ContactosSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contactos");
        sqLiteDatabase.execSQL(sqlCreate);
    }
}
