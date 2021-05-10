package entity;

public class Operario extends Empleado{

    private String zone;

    public Operario(Integer age, String name, Double salary, String zone) {
        super(age, name, salary);
        this.zone = zone;
    }

    public String getZone() {
        return this.zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String message() {
        return "Este empleado es: operario y se llama: " + this.getName();
    }

}