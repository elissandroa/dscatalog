package edu.elissandro.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.elissandro.dscatalog.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
