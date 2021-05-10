import singleton.ConexionDBSingleton;

public class App {
    public static void main(String[] args) throws Exception {
        
        /*
        Una sola vez va a hacer print del sout dentro del 
        constructor, las demás veces va a referenciar a 
        la instancia ya creada.
        */

        for (int i = 0; i < 10; i++) {
            ConexionDBSingleton con = ConexionDBSingleton.getInstancia();
            System.out.println("Conexión : " + con);
        }
    }
}
