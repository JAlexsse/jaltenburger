package strategies;

import entity.Empleado;
import entity.Encargado;

public class EncargadoPlus implements PlusStrategy{

    @Override
    public boolean correspondsPlus(Empleado empleado) {

        Encargado encargado = (Encargado) empleado;

        return (encargado.getAge() > 30 && encargado.getCommission() > 100.00);
    }
    
}
