package facade;

import constants.TipoEmpleado;
import entity.Empleado;
import factory.EmpleadoCreator;
import factory.EncargadoCreator;
import factory.OperarioCreator;
import services.Salario;

public class SalarioFacade {

    private static EmpleadoCreator empleadoCreator;
    private static Empleado empleado;
    private Salario salario = new Salario();
    
    public void calcularSalario(Integer age, String name, Double salary, Double commission, String zone, TipoEmpleado tipoEmpleado) {
        configureCreator(tipoEmpleado);
        createEmpleado(age, name, salary, commission, zone);
        salario.infoSalary(empleado);
    }

    public static void configureCreator(TipoEmpleado tipoEmpleado) {
        switch (tipoEmpleado) {
            case OPERARIO:
                empleadoCreator = new OperarioCreator();
                break;
            case ENCARGADO:
                empleadoCreator = new EncargadoCreator();
                break;

            default:
                break;
        }

    }

    public static void createEmpleado(Integer age, String name, Double salary, Double commission, String zone){
        empleado = empleadoCreator.infoEmpleado(age, name, salary, commission, zone);
    }
}
