import java.util.ArrayList;
import java.util.List;

import models.Persona;
import models.PersonaMayor;
import models.PersonaMenor;

public class App {
    public static void main(String[] args) throws Exception {
        List<Persona> personas = new ArrayList<Persona>();

        personas.add(new PersonaMenor("Juan", "Cordoba", 5, 5, 2007));
        personas.add(new PersonaMayor("Maria", "Cordoba", 8, 4, 2000));
        personas.add(new PersonaMayor("Julia", "Cordoba", 3, 2, 1985));
        personas.add(new PersonaMenor("Hector", "Cordoba", 7, 10, 2010));

        for (Persona persona : personas) {
            if(persona.getFechaNac().validarMayoriaEdad()){
                System.out.println("Esta persona dice ser:");
                System.out.println(persona.toString());
                System.out.println("Y es Mayor de edad.");
            } else {
                System.out.println("Esta persona dice ser:");
                System.out.println(persona.toString());
                System.out.println("Y es Menor de edad.");
            }
        }

    }
}
