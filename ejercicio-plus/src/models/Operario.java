package models;

public class Operario extends Empleado{
    
    private String zone;

    public Operario(){
        super();
    }

    public Operario(Integer age, String name, Double salary, String zone){
        super(age, name, salary);
        this.zone = zone;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    
    @Override
    public String toString() {
        return "Este empleado es: operario y se llama: " + this.getName();
    }

    @Override
    public boolean plus() {
        return (this.getAge() < 25 && this.getZone().equals("3"));
    }
    
}
