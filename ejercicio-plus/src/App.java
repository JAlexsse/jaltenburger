import java.util.ArrayList;
import java.util.List;

import models.Encargado;
import models.Operario;
import utils.Constante;

public class App {
    public static void main(String[] args) throws Exception {
        List<Operario> operarios = new ArrayList<Operario>();
        operarios.add(new Operario(23, "Juan", 3500.00, "3"));
        operarios.add(new Operario(40, "Maria", 5000.00, "3"));

        List<Encargado> encargados = new ArrayList<Encargado>();
        encargados.add(new Encargado(35, "Julia", 5200.00, 150.00));
        encargados.add(new Encargado(23, "Hector", 4300.00, 150.00));

        for (Encargado encargado : encargados) {
            System.out.println(encargado.toString());

            Double finalSalary = encargado.getSalary();
            if(encargado.plus()){
                System.out.println("Le corresponde plus.");
                finalSalary = finalSalary + Constante.PLUS.getValue();
            }

            System.out.println(finalSalary);
        }

        for (Operario operario : operarios) {
            System.out.println(operario.toString());

            Double finalSalary = operario.getSalary();
            if(operario.plus()){
                System.out.println("Le corresponde plus.");
                finalSalary = finalSalary + Constante.PLUS.getValue();
            }
            
            System.out.println(finalSalary);    
        }
    }
}
