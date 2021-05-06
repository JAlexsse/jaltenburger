package models;

public abstract class Empleado {
    private Integer age;
    private String name;
    private Double salary;

    public Empleado(){}

    public Empleado(Integer age, String name, Double salary){
        this.age = age;
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public abstract String toString();

    public abstract boolean plus();
}
