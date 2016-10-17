package com.example.gaston.mytraveldiary.travelsDB.model;



public class Event {
    public int idEvent;
    public int idTravel;
    public String title;
    public float coordX;
    public float coordY;
    public String description;


    public Event(int idEvent, int idTravel, String title, float coordX, float coordY, String description){
        this.idEvent=idEvent;
        this.idTravel=idTravel;
        this.title=title;
        this.coordX=coordX;
        this.coordY=coordY;
        this.description=description;
    }
}
