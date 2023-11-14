package eetac.upc.dsa.models;

import java.util.concurrent.atomic.AtomicInteger;

public class Mapa {
    int id;
    static AtomicInteger nextId = new AtomicInteger();
    // Constructor
    public Mapa(){this.id = nextId.incrementAndGet();}
    public int GetId(){return this.id;}
}
