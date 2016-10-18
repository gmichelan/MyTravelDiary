package com.example.gaston.mytraveldiary.travelsDB.sqlite;

import android.content.Context;
import android.database.sqlite.*;
import android.os.Build;
import android.provider.BaseColumns;

import android.database.sqlite.SQLiteOpenHelper;

import com.example.gaston.mytraveldiary.travelsDB.sqlite.TravelsMeta.Travel;
import com.example.gaston.mytraveldiary.travelsDB.sqlite.TravelsMeta.Event;
import com.example.gaston.mytraveldiary.travelsDB.sqlite.TravelsMeta.Image;
import com.example.gaston.mytraveldiary.travelsDB.sqlite.TravelsMeta.ImageEvent;

import java.lang.ref.Reference;

/**
 Clase que administra la conexión de la base de datos y su estructuración
 */

public class MyTravelsBD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME= "travels.db";
    private static final int ACTUAL_VERSION=1;
    private final Context context;

    interface Tables{
        String TRAVEL= "travels";
        String EVENT= "events";
        String IMAGE= "image";
        String IMAGE_EVENT= "image_event";
    }

    interface References{
        String ID_TRAVEL= String.format("REFERENCES %s(%s)", Tables.TRAVEL, Travel.ID);
        String ID_EVENT= String.format("REFERENCES %s(%s)", Tables.EVENT, Event.ID);
        String ID_IMAGE= String.format("REFERENCES %s(%s)", Tables.IMAGE, Image.ID);
    }

    public MyTravelsBD(Context context){
        super(context, DATABASE_NAME, null, ACTUAL_VERSION);
        this.context=context;
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if(!db.isReadOnly()){
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            }else{
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s text NOT NULL, %f float, %f float, %s long, %s text)"+ Tables.TRAVEL, BaseColumns._ID,Travel.ID,
                Travel.COORDENATE_X,Travel.COORDENATE_Y, Travel.COVER_IMAGE, Travel.DESCRIPTION));
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s int, name text NOT NULL, %f float, %f float, review text, FOREIGN KEY (id_travel) %s)"+ Tables.EVENT, BaseColumns._ID, Event.ID,Event.ID_TRAVEL,
                Event.TITLE, Event.COORDENATE_X,Event.COORDENATE_Y, Event.DESCRIPTION, References.ID_TRAVEL));
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s bitmap)", Tables.IMAGE, BaseColumns._ID,Image.ID,Image.URL));
        db.execSQL(String.format("CREATE TABLE %s (%s int, %s int, PRIMARY KEY (%s, %s), FOREIGN KEY(id_image) %s, FOREIGN KEY (id_event) %s"+Tables.IMAGE_EVENT, BaseColumns._ID,
        ImageEvent.ID_EVENT,ImageEvent.ID_IMAGE,ImageEvent.ID_EVENT,ImageEvent.ID_IMAGE, References.ID_IMAGE, References.ID_EVENT));
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
