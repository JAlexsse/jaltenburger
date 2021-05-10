package singleton;

public class ConexionDBSingleton {

    /*
    Este campo estático va a guardar la única instancia
    de nuestro Singleton.
    */
    private static ConexionDBSingleton instancia;

    /*
    Constructor privado: No puedo crear una instancia
    desde fuera con el constructor.
    */
    private ConexionDBSingleton(){
        System.out.println(
            "Conectandose algún motor de base de datos");
    }

    /*
    Como el atributo que guarda la instancia es estático,
    el constructor también.
    Como solo la vamos a crear una sola vez, antes de crearla
    peguntamos si existe ya una instancia o no.
    */
    public static ConexionDBSingleton getInstancia() {
        if (!(instancia instanceof ConexionDBSingleton)) {
            instancia = new ConexionDBSingleton();
        } 
        return instancia;
    }
    
}
