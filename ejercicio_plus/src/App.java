import constants.TipoEmpleado;
import facade.SalarioFacade;

public class App {
    
    private static TipoEmpleado tipoEmpleado;
    private static SalarioFacade salarioFacade = new SalarioFacade();
    

    public static void main(String[] args) throws Exception {
        tipoEmpleado = TipoEmpleado.OPERARIO;
        salarioFacade.calcularSalario(23, "Alicia", 750.00, null, "3", tipoEmpleado);

        tipoEmpleado = TipoEmpleado.ENCARGADO;
        salarioFacade.calcularSalario(35, "Hector", 1750.00, 150.00, null, tipoEmpleado);

        tipoEmpleado = TipoEmpleado.OPERARIO;
        salarioFacade.calcularSalario(26, "Juan", 1750.00, null, "1", tipoEmpleado);

        tipoEmpleado = TipoEmpleado.ENCARGADO;
        salarioFacade.calcularSalario(26, "Maria", 1750.00, 90.00, null, tipoEmpleado);
    }

}
