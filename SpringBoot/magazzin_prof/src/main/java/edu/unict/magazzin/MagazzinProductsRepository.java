package edu.unict.magazzin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MagazzinProductsRepository extends JpaRepository<MagazzinProductsModel,Long> {
    
    List<MagazzinProductsModel> findByQuantityGreaterThan(int i);

    List<MagazzinProductsModel> findByNameContaining(String name);
}
