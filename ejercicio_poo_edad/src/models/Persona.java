package models;

public class Persona {
    private String nombre;
    private String ciudad;
    private Fecha fechaNac;

    public Persona(){}

    public Persona(String nombre, String ciudad, int diaNac, int mesNac, int anioNac){
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.setFechaNac(new Fecha(diaNac, mesNac, anioNac));
    }

    public Fecha getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Fecha fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString(){
        return "Es una persona";
    }
}
