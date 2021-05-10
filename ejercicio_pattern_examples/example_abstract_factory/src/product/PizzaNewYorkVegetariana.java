package product;

import java.util.ArrayList;
import java.util.List;

import factory.PizzaProducto;

public class PizzaNewYorkVegetariana extends PizzaProducto{

    public PizzaNewYorkVegetariana() {
        super();
        this.setNombre("Pizza vegetariana New York.");
        this.setMasa("Masa integral.");
        this.setSalsa("Salsa de tomate.");
        List<String> ingredientes = new ArrayList<String>();
            ingredientes.add("Queso vegano");
            ingredientes.add("Espinaca");
            ingredientes.add("Tomates secos");
            ingredientes.add("Aceite de oliva");
        this.setIngredientes(ingredientes);

    }

    @Override
    public void cocinar() {
        System.out.println("Cocinando por 25 minutos a 150°C.");        
    }

    @Override
    public void cortar() {
        System.out.println("Cortando pizza en cuadrados.");        
    }
    
}
