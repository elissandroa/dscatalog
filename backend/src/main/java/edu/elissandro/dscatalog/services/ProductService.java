package edu.elissandro.dscatalog.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.elissandro.dscatalog.dto.ProductDTO;
import edu.elissandro.dscatalog.entities.Product;
import edu.elissandro.dscatalog.repositories.ProductRepository;
import edu.elissandro.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(Pageable pageable) {
		Page<Product> product = repository.findAll(pageable);
		return product.map(x -> new ProductDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() ->  new ResourceNotFoundException("Resource not found"));
		return new ProductDTO(entity, entity.getCategories());
	}
}
