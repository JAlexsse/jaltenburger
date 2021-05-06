package models;

public class PersonaMayor extends Persona{

    public PersonaMayor(String nombre, String ciudad, int diaNac, int mesNac, int anioNac){
        super(nombre, ciudad, diaNac, mesNac, anioNac);
    }

    @Override
    public String toString(){
        return "Persona mayor de edad.";
    }
}
