package product;

import java.util.ArrayList;
import java.util.List;

import factory.PizzaProducto;

public class PizzaCaliforniaPepperoni extends PizzaProducto {
    
    public PizzaCaliforniaPepperoni() {
        super();
        this.setNombre("Pizza pepperoni California.");
        this.setMasa("Masa gruesa.");
        this.setSalsa("Salsa de tomate.");
        List<String> ingredientes = new ArrayList<String>();
            ingredientes.add("Queso mozzarella");
            ingredientes.add("Extra pepperoni");
            ingredientes.add("Aceitunas negras");
            ingredientes.add("Aceite de oliva");
            ingredientes.add("Cebolla");
        this.setIngredientes(ingredientes);

    }

    @Override
    public void cocinar() {
        System.out.println("Cocinando por 40 minutos a 100°C");        
    }

    @Override
    public void cortar() {
        System.out.println("Cortando la pizza en cuadrados");        
    }
}
