package entity;

public class Encargado extends Empleado{

    private Double commission;

    public Encargado(Integer age, String name, Double salary, Double commission) {
        super(age, name, salary);
        this.commission = commission;
    }

    public Double getCommission() {
        return this.commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    @Override
    public String message() {
        return "Este empleado es: encargado y se llama: " + this.getName();
    }
    
}
