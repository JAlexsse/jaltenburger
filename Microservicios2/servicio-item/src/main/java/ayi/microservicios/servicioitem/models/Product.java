package ayi.microservicios.servicioitem.models;

import java.util.Date;

public class Product {

    private Long id;
    private String name;
    private Double price;
    private Date createAt;

    public Product(){

    }

    public Product(Long id, String name, Double price, Date createAt){
        this.id = id;
        this.name = name;
        this.price = price;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }
    public Date getCreateAt() {
        return createAt;
    }
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
