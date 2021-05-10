package product;

import java.util.ArrayList;
import java.util.List;

import factory.PizzaProducto;

public class PizzaNewYorkPepperoni extends PizzaProducto{

    public PizzaNewYorkPepperoni() {
        super();
        this.setNombre("Pizza pepperoni New York.");
        this.setMasa("Masa delgada a la piedra.");
        this.setSalsa("Salsa de tomate picante.");
        List<String> ingredientes = new ArrayList<String>();
            ingredientes.add("Queso mozzarella");
            ingredientes.add("Extra pepperoni");
            ingredientes.add("Aceitunas");
            ingredientes.add("Tomates secos");
        this.setIngredientes(ingredientes);

    }

    @Override
    public void cocinar() {
        System.out.println("Cocinando por 40 minutos a 90°C");        
    }

    @Override
    public void cortar() {
        System.out.println("Cortando la pizza en triángulos");        
    }
 
}
