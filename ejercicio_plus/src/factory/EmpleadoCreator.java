package factory;

import entity.Empleado;

public abstract class EmpleadoCreator {

    public Empleado infoEmpleado(Integer age, String name, Double salary, Double commission, String zone){
        
        Empleado empleado = createEmpleado(age, name, salary, commission, zone);
        
        System.out.println(empleado.message());

        return empleado;

    }

    public abstract Empleado createEmpleado(Integer age, String name, Double salary, Double commission, String zone);
    
}
