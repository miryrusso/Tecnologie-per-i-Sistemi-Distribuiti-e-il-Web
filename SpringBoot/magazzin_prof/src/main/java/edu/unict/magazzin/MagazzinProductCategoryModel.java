package edu.unict.magazzin;

import jakarta.persistence.*;

@Entity
@Table(name="categories")
public class MagazzinProductCategoryModel {
   @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long id=0L;
   private String name; 

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
}


