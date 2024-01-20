package edu.unict.magazzin;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class MagazzinProductsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;
    private String name;
    private Integer quantity = 0;
    private float price = 0.0f;
    // New attribute categoryId to link to the product category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private MagazzinProductCategoryModel categoryId;

    public MagazzinProductCategoryModel getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(MagazzinProductCategoryModel categoryId) {
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
