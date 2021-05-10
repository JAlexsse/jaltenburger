package services;

import constants.Plus;
import entity.Empleado;
import entity.Encargado;
import entity.Operario;
import strategies.EncargadoPlus;
import strategies.OperarioPlus;
import strategies.PlusStrategy;

public class Salario {

    private PlusStrategy strategy;

    public void infoSalary(Empleado empleado) {

        boolean plus;
            
        if(verifyPlus(empleado)){
            plus = true;
            System.out.println("Le corresponde plus.");
        } else {
            plus = false;
            System.out.println("No le corresponde plus.");
        }

        System.out.println("Salario final: " + finalSalary(empleado, plus));
    }

    public boolean verifyPlus(Empleado empleado){
        if (empleado instanceof Operario){ 
            strategy = new OperarioPlus();
            return strategy.correspondsPlus(empleado);
        }
        
        if(empleado instanceof Encargado){
            strategy = new EncargadoPlus();
            return strategy.correspondsPlus(empleado);
        }

        return false;
    }

    public Double finalSalary(Empleado empleado, boolean plus) {
        if (plus) {
            return empleado.getSalary() + Plus.PLUS.getValue();
        } else {
            return empleado.getSalary();
        }
    }
}
