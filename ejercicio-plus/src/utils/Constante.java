package utils;

public enum Constante {
    PLUS(200.00);

    Constante(Double value){
        this.value = value;
    }

    private final Double value;

    public Double getValue() {
        return value;
    }
    
}
