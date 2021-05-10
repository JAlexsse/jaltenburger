package product;

import java.util.ArrayList;
import java.util.List;

import factory.PizzaProducto;

public class PizzaCaliforniaVegetariana extends PizzaProducto {

    public PizzaCaliforniaVegetariana() {
        super();
        this.setNombre("Pizza vegetariana California.");
        this.setMasa("Masa de cebada.");
        this.setSalsa("Salsa bechamel.");
        List<String> ingredientes = new ArrayList<String>();
            ingredientes.add("Queso vegano");
            ingredientes.add("Espinaca");
            ingredientes.add("Tomates secos");
            ingredientes.add("Aceitunas negras");
        this.setIngredientes(ingredientes);

    }


    @Override
    public void cocinar() {
        System.out.println("Cocinando por 30 minutos a 130°C.");        
    }

    @Override
    public void cortar() {
        System.out.println("Cortando en triángulos.");        
    }
    
}
