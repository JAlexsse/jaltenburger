package ayi.microservicios.servicioitem.models;

public class Item {
    private Product product;
    private Integer quantity;

    public Item(){

    }

    public Item(Product product, Integer quantity){
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getTotal(){
        return product.getPrice() * this.getQuantity();
    }
    
}
