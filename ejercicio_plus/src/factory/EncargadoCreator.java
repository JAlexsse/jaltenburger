package factory;

import entity.Empleado;
import entity.Encargado;

public class EncargadoCreator extends EmpleadoCreator{

    @Override
    public Empleado createEmpleado(Integer age, String name, Double salary, Double commission, String zone) {
        return new Encargado(age, name, salary, commission);
    }

}
