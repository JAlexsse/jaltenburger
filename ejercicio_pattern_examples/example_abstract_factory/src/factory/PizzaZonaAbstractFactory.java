package factory;

import constants.TipoPizza;

public abstract class PizzaZonaAbstractFactory {
    public PizzaProducto ordenarPizza(TipoPizza tipoPizza) {
        PizzaProducto pizza = crearPizza(tipoPizza);
        System.out.println("Fabricando la pizza " + pizza.getNombre());
        pizza.preparar();
        pizza.cocinar();
        pizza.cortar();
        pizza.empaquetar();
        return pizza;
    }

    //no es publica por que solo se puede crear en la factory
    abstract PizzaProducto crearPizza(TipoPizza tipoPizza);
}