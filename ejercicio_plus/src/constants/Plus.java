package constants;

public enum Plus {
    PLUS(200.00);

    Plus(Double value){
        this.value = value;
    }

    private final Double value;

    public Double getValue() {
        return value;
    }
}
