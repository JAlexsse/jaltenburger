package strategies;

import entity.Empleado;
import entity.Operario;

public class OperarioPlus implements PlusStrategy{

    @Override
    public boolean correspondsPlus(Empleado empleado) {

        Operario operario = (Operario) empleado;

        return (operario.getAge() < 25 && operario.getZone().equals("3"));
    }
    
}
