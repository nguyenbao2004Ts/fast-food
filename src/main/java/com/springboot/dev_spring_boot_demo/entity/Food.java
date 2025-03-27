package com.springboot.dev_spring_boot_demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "name_food")
    private String nameFood;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "price")
    private String price;



    @Column(name = "imageURL")
    private String imageURL;

    public Food() {
    }

    public Food(String nameFood, String quantity, String price, String imageURL) {
        this.nameFood = nameFood;
        this.quantity = quantity;
        this.price = price;
        this.imageURL = imageURL;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", nameFood='" + nameFood + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}