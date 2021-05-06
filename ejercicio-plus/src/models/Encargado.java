package models;

public class Encargado extends Empleado{

    private Double commission;

    public Encargado(){
        super();
    }

    public Encargado(Integer age, String name, Double salary, Double commission){
        super(age, name, salary);
        this.commission = commission;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    @Override
    public String toString() {
        return "Este empleado es: encargado y se llama: " + this.getName();
    }

    @Override
    public boolean plus() {
        return (this.getAge() > 30 && this.getCommission() > 100.00);
    }
    
}
