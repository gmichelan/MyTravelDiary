package com.example.gaston.mytraveldiary.travelsDB.model;
/*modelo del viaje */

public class Travel {
    public String idTravel;
    public String name;
    public float coordX;
    public float coordY;
    public String coverImage;
    public String description;


    public Travel( String idTravel, String name, float coordX, float coordY, String coverImage, String description){
        this.idTravel=idTravel;
        this.name=name;
        this.coordX=coordX;
        this.coordY=coordY;
        this.coverImage=coverImage;
        this.description=description;
    }
}
