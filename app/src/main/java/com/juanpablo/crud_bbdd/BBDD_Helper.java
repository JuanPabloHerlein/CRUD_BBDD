package com.juanpablo.crud_bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BBDD_Helper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="Datos_Trabajadores.db";

    public BBDD_Helper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL(Estructura_BBDD.SQL_DELETE_ENTRIES);
        db.execSQL(Estructura_BBDD.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(Estructura_BBDD.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int i, int i1){
        onUpgrade(db,i,i1);
    }
}
