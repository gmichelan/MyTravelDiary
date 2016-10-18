package com.example.gaston.mytraveldiary.travelsDB.sqlite;
import java.util.UUID;

/**
 * Clase que establece los nombres a usar en la base de datos
 */

public class TravelsMeta {

    interface ColumnsTravel{
        String ID = "id";
        String NAME = "name";
        String COORDENATE_X ="coordenate_x";
        String COORDENATE_Y = "coordenate_y";
        String COVER_IMAGE= "portrait_image";
        String DESCRIPTION= "description";
    }

    interface ColumnsEvent{
        String ID= "id";
        String ID_TRAVEL="id_travel";
        String TITLE="title";
        String COORDENATE_X ="coordenate_x";
        String COORDENATE_Y = "coordenate_y";
        String DESCRIPTION= "description";
    }
    interface ColumnsImage{
        String ID="id";
        String URL= "url";
    }
    interface ColumnsImageEvent{
        String ID_EVENT= "id_event";
        String ID_IMAGE="id_image";
    }

    public static class Travels implements ColumnsTravel{
        public static String generateIdTravels(){
            return UUID.randomUUID().toString();
        }
    }
    public static class Event implements ColumnsEvent{
        public static String generateIdEvent(){
            return UUID.randomUUID().toString();
        }
    }
    public static class Image implements ColumnsImage{
        public static String generateIdImage(){
            return UUID.randomUUID().toString();
        }
    }
    public static class ImageEvent implements ColumnsImageEvent{
        public static String generateIdEvent(){
            return UUID.randomUUID().toString();
        }
    }


}
