package com.example.gaston.mytraveldiary.travelsDB.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.example.gaston.mytraveldiary.travelsDB.model.Travel;
import com.example.gaston.mytraveldiary.travelsDB.sqlite.TravelsMeta.Travels;
import com.example.gaston.mytraveldiary.travelsDB.sqlite.TravelsMeta.Event;
import com.example.gaston.mytraveldiary.travelsDB.sqlite.TravelsMeta.Image;
import com.example.gaston.mytraveldiary.travelsDB.sqlite.TravelsMeta.ImageEvent;

/* Clase auxiliar que implementa a {@link MyTravelsBD} para llevar a cabo el CRUD
* sobre las entidades existentes.
 */

public class DatabaseHandler {

    private static MyTravelsBD database;
    private static DatabaseHandler instance= new DatabaseHandler();

    private DatabaseHandler(){

    }

    public static DatabaseHandler getInstance(Context context){
        if(database==null){
            database=new MyTravelsBD(context);
        }
        return instance;
    }

    public String insertTravel(Travel travel){
        SQLiteDatabase db= database.getWritableDatabase();
        //generar clave primaria
        String idTravel= Travels.generateIdTravels();
        ContentValues values= new ContentValues();
        values.put(Travels.ID, idTravel);
        values.put(Travels.NAME, travel.name);
        values.put(Travels.COORDENATE_X, travel.coordX);
        values.put(Travels.COORDENATE_Y, travel.coordY);
        values.put(Travels.COVER_IMAGE, travel.coverImage);
        values.put(Travels.DESCRIPTION,travel.description);

        //insertar cabecera
        db.insertOrThrow(MyTravelsBD.Tables.TRAVEL, null,values);
        return idTravel;
    }

}
