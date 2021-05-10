package factory;

import java.util.ArrayList;
import java.util.List;

public abstract class PizzaProducto {
    private String nombre;
    private String masa;
    private String salsa;
    private List<String> ingredientes;

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMasa() {
        return this.masa;
    }

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public String getSalsa() {
        return this.salsa;
    }

    public void setSalsa(String salsa) {
        this.salsa = salsa;
    }

    public List<String> getIngredientes() {
        return this.ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }


    public PizzaProducto() {
        this.ingredientes = new ArrayList<String>();
    }

    public void preparar(){
        System.out.println("Preparando " + getNombre());
        System.out.println("Seleccionando masa " + getMasa());
        System.out.println("Agregando salsa " + getSalsa());
        System.out.println("Agregando ingredientes ");
        this.getIngredientes().forEach(System.out::println);
    }

    public abstract void cocinar();

    public abstract void cortar();

    public void empaquetar(){
        System.out.println("Empaquetanto la pizza...");
    }


    @Override
    public String toString() {
        return
            "\n Pizza: '" + getNombre() + "'" +
            "\n Masa: '" + getMasa() + "'" +
            "\n Salsa: '" + getSalsa() + "'" +
            "\n Ingredientes: '" + getIngredientes() + "'\n";
    }


}
