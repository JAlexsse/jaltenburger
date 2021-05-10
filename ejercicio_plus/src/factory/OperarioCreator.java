package factory;

import entity.Empleado;
import entity.Operario;

public class OperarioCreator extends EmpleadoCreator {

    @Override
    public Empleado createEmpleado(Integer age, String name, Double salary, Double commission, String zone) {
        return new Operario(age, name, salary, zone);
    }

    
}
