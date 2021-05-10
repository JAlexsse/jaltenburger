package factory;

import constants.TipoPizza;
import product.PizzaNewYorkItaliana;
import product.PizzaNewYorkPepperoni;
import product.PizzaNewYorkVegetariana;

public class PizzeriaNewYorkFactory extends PizzaZonaAbstractFactory {

    @Override
    public PizzaProducto crearPizza(TipoPizza tipoPizza) {
        
        return switch (tipoPizza) {
            case VEGETARIANA -> new PizzaNewYorkVegetariana();
            case PEPPERONI -> new PizzaNewYorkPepperoni();
            case ITALIANA -> new PizzaNewYorkItaliana();
            default -> null;
        };
    }
    
}
