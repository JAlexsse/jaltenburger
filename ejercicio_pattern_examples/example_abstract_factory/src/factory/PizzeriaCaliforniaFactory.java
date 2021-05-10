package factory;

import constants.TipoPizza;
import product.PizzaCaliforniaCuatroQuesos;
import product.PizzaCaliforniaPepperoni;
import product.PizzaCaliforniaVegetariana;

public class PizzeriaCaliforniaFactory extends PizzaZonaAbstractFactory {

    @Override
    public PizzaProducto crearPizza(TipoPizza tipoPizza) {
        
        return switch (tipoPizza) {
            case CUATROQUESOS -> new PizzaCaliforniaCuatroQuesos();
            case PEPPERONI -> new PizzaCaliforniaPepperoni();
            case VEGETARIANA -> new PizzaCaliforniaVegetariana();
            default -> null;
        };
    }
    
}
