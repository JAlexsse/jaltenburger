package product;

import java.util.ArrayList;
import java.util.List;

import factory.PizzaProducto;

public class PizzaNewYorkItaliana extends PizzaProducto{

    public PizzaNewYorkItaliana() {
        super();
        this.setNombre("Pizza italiana New York.");
        this.setMasa("Masa gruesa.");
        this.setSalsa("Salsa italiana.");
        List<String> ingredientes = new ArrayList<String>();
            ingredientes.add("Queso mozzarella");
            ingredientes.add("Aceitunas");
            ingredientes.add("Jamón");
            ingredientes.add("Huevo");
        this.setIngredientes(ingredientes);

    }

    @Override
    public void cocinar() {
        System.out.println("Cocinando por 35 minutos a 120°C");        
        
    }

    @Override
    public void cortar() {
        System.out.println("Cortando la pizza en triángulos grandes");        
        
    }
    
}
