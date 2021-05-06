package models;

public class PersonaMenor extends Persona{

    public PersonaMenor(String nombre, String ciudad, int diaNac, int mesNac, int anioNac){
        super(nombre, ciudad, diaNac, mesNac, anioNac);
    }

    @Override
    public String toString(){
        return "Persona menor de edad";
    }
}
