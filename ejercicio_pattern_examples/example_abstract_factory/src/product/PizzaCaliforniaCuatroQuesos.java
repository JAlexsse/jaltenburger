package product;

import java.util.ArrayList;
import java.util.List;

import factory.PizzaProducto;

public class PizzaCaliforniaCuatroQuesos extends PizzaProducto {
    
    public PizzaCaliforniaCuatroQuesos() {
        super();
        this.setNombre("Pizza 4 Quesos California.");
        this.setMasa("Masa delgada a la piedra.");
        this.setSalsa("Salsa de tomate picante.");
        List<String> ingredientes = new ArrayList<String>();
            ingredientes.add("Queso mozzarella");
            ingredientes.add("Queso Roquefort");
            ingredientes.add("Queso Cheddar");
            ingredientes.add("Tomates secos");
        this.setIngredientes(ingredientes);

    }

    @Override
    public void cocinar() {
        System.out.println("Cocinando por 20 minutos a 180°C");        
    }

    @Override
    public void cortar() {
        System.out.println("Cortando la pizza en rectángulos");        
    }
}
