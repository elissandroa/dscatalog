package edu.elissandro.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.elissandro.dscatalog.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
