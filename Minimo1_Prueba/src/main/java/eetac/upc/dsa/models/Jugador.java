package eetac.upc.dsa.models;

import java.util.concurrent.atomic.AtomicInteger;

public class Jugador {
    int id;
    static AtomicInteger nextId = new AtomicInteger();
    String username;
    String pasword;
    String mail;
    int points;   // Puntos en partida


    // Constructores
    public Jugador(String username, String mail, String pasword){
        this();
        this.username = username;
        this.mail = mail;
        this.pasword = pasword;
        this.points = 100;
        this.id = nextId.incrementAndGet();
    }

    public Jugador(){this.id = nextId.incrementAndGet();}

    // Setters y Getters
    public int  GetUserId(){return this.id;}
    public String GetUserName(){return this.username;}
    public void SetUserName(String username){this.username = username;}
    public String GetMail(){return this.mail;}
    public void SetMail(String mail){this.mail = mail;}
    public int GetPoints(){return this.points;}
    public void SetPoints(int points){this.points = this.points + points;}
}