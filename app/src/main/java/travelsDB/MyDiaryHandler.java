package travelsDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.*;

/**
 * Created by gaston on 21/09/16.
 * Clase que se encarga de manejar y controlar las actividades de la base de datos.
 * Implementa las clases de SQLite prebistas por defecto en androidstudio
 */
public class MyDiaryHandler extends SQLiteOpenHelper{//hereda de una clase de ayuda
    Context ctx;
   /* public MyDiaryHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
   name es el nombre de la base de datos
  factory va en nulo poe ahora
  version es la version de la base de datos
        super(context, name, null , version);
    }*/

    //constructor
   public MyDiaryHandler(Context context){
       super(context, "my_travels", null , 1);
       ctx=context;
   }
    @Override
    public void onCreate(SQLiteDatabase db){//crea la base de datos
        db.execSQL("CREATE TABLE travel(id_travel INTEGER PRIMARY KEY AUTOINCREMENT, name text NOT NULL, x_coord float, y_coord float, portrait long, review text)");
        db.execSQL("CREATE TABLE event(id_event INTEGER PRIMARY KEY AUTOINCREMENT, id_travel int, name text NOT NULL, x_coord float, y_coord float, review text, FOREIGN KEY (id_travel) references travel(id_travel)");
        db.execSQL("CREATE TABLE image(id_image INTEGER PRIMARY KEY AUTOINCREMENT, location bitmap)");
        db.execSQL("CREATE TABLE eventimage( id_image int, id_event int, PRIMARY KEY (id_image, id_event), FOREIGN KEY(id_image) references image(id_image), FOREIGN KEY (id_event) references event(id_event)");
    }
    @Override
    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //para realizar cambios es mejor eliminar la tabla y recrearla
        db.execSQL("DROP TABLE IF EXISTS telefonos");
        onCreate(db);



    }

    //Variables generales que guardan referencias a los objetos
    MyDiaryHandler ayuda;
    SQLiteDatabase db;

    //Métodos para manejar la BD
    public void abriBD(){
        ayuda=new MyDiaryHandler(ctx);
        db=ayuda.getWritableDatabase(); //recibimos la base de datos de manera escribible

    }

    public void cerrarDB(){
        db.close();
    }

    //Métodos para manipular datos

    public long registrar(String pNumero) throws Exception {
        ContentValues valores = new ContentValues();
        valores.put("numero", pNumero); //en columna número guardo el pNumero que ingresa
        return db.insert("telefonos", null, valores);
    }


    public long travelRegister(String name, float x, float y, float dir, String review){
        ContentValues tableValues= new ContentValues();
        tableValues.put("name", name);
        tableValues.put("x_coord", x);
        tableValues.put("y_coord", y);
        tableValues.put("portrait", dir);
        tableValues.put("review", review);
        return db.insert("travel",null,tableValues);
    }

    public long eventRegister( int id_travel,String title,float x, float y, float dir, String review){
        ContentValues tableValues= new ContentValues();
        tableValues.put("id_travel", id_travel);
        tableValues.put("title", title);
        tableValues.put("x_coord", x);
        tableValues.put("y_coord", y);
        tableValues.put("review", review);
        return db.insert("event",null,tableValues);
    }

    public long imageRegister( float dir){
        ContentValues tableValues= new ContentValues();
        tableValues.put("location", dir);
        return db.insert("image",null,tableValues);
    }
    public long imageEventRegister( int id_image, int id_event){
        ContentValues tableValues= new ContentValues();
        tableValues.put("id_image", id_image);
        tableValues.put("id_event", id_event);
        return db.insert("event",null,tableValues);
    }
}
