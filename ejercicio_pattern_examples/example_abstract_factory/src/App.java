import constants.TipoPizza;
import factory.PizzaProducto;
import factory.PizzaZonaAbstractFactory;
import factory.PizzeriaCaliforniaFactory;
import factory.PizzeriaNewYorkFactory;

public class App {

    private static TipoPizza tipoPizza;
    private static PizzaZonaAbstractFactory pizzeriaNY = new PizzeriaNewYorkFactory();
    private static PizzaZonaAbstractFactory pizzeriaCA = new PizzeriaCaliforniaFactory();
    private static PizzaProducto pizza;
    public static void main(String[] args) throws Exception {

        tipoPizza = TipoPizza.PEPPERONI;
        pizza = pizzeriaNY.ordenarPizza(tipoPizza);

        System.out.println(pizza.toString());

        tipoPizza = TipoPizza.CUATROQUESOS;
        pizza = pizzeriaCA.ordenarPizza(tipoPizza);

        System.out.println(pizza.toString());
    }
}
