package eetac.upc.dsa.models;

import java.util.concurrent.atomic.AtomicInteger;

public class Avatar {
    int id;
    static AtomicInteger nextId = new AtomicInteger();
    int idArma;
    int life;
    int damg;
    int visible;    // Visible "0" Invisible "1"
    int speed;

    // Constructores
    public Avatar(){this.id = nextId.incrementAndGet();}
    public Avatar(int idArma, int life, int damg, int speed){
        this();
        this.id = nextId.incrementAndGet();
        this.idArma = idArma;
        this.life = life;
        this.damg = damg;
        this.speed = speed;
        this.visible = 0;
    }
    // Setters y Getters
    public int GetId(){return this.id;}
    public int GetIdArma(){return this.idArma;}
    public void SetIdArma(int idArma){this.idArma = idArma;}
    public int GetLife(){return this.life;}
    public void SetLife(int life){this.life = life;}
    public int GetDamg(){return this.damg;}
    public void SetDamg(int damg){this.damg = damg;}
    public int GetSpeed(){return this.speed;}
    public void SetSpeed(int speed){this.speed = speed;}
    public int GetVisible(){return this.visible;}
    public void SetVisible(int visible){this.visible = visible;}
}
