package eetac.upc.dsa.models;

import java.util.concurrent.atomic.AtomicInteger;

public class Tienda {
    int id;
    static AtomicInteger nextId = new AtomicInteger();
    int precio;
    String nombre;
    int efect_type;         // Tipo de efecto que ejerce
                            // Incremento de la vida "0"
                            // Incremento del daño "1"
                            // Incremento de la velocidad "2"
                            // Invisibilidad "3"
    int efect;              // Si el efecto es de tipo invisibilidad siempre será 1
    String description;     // Pequeña descripción del producto

    // Constructores
    public Tienda(){this.id = nextId.incrementAndGet();}
    public Tienda(int precio, String nombre, String description, int efect_type, int efect){
        this();
        this.id = nextId.incrementAndGet();
        this.nombre = nombre;
        this.description = description;
        this.precio = precio;
        this.efect_type = efect_type;
        this.efect = efect;
    }
    public int GetId(){return this.id;}
    public int GetPrecio(){return this.precio;}
    public void SetPrecio(int precio){this.precio = precio;}
    public int GetEfectType(){return this.efect_type;}
    public void SetEfectType(int efect_type){this.efect_type = efect_type;}
    public int GetEfect(){return this.efect;}
    public void SetEfect(int efect){this.efect = efect;}
    public String GetNombre(){return this.nombre;}
    public void SetNombre(String nombre){this.nombre = nombre;}
    public String GetDescription(){return this.description;}
    public void SetDescription(String description){this.description = description;}

}
