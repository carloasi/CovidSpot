package com.example.covidspot;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "bdCovid2.db";
    private static  final int version = 1;

    public Conexao(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuario(id integer primary key autoincrement, " +
                "nome varchar(50), login varchar(20), senha varchar(20))");

        db.execSQL("create table casos(id integer primary key autoincrement, " +
                "nome varchar(50), cidade varchar(20), caso varchar(20), sexo varchar(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
