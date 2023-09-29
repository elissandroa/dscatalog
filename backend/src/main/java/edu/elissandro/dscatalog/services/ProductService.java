package edu.elissandro.dscatalog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.elissandro.dscatalog.dto.ProductDTO;
import edu.elissandro.dscatalog.entities.Product;
import edu.elissandro.dscatalog.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(Pageable pageable) {
		Page<Product> product = repository.findAll(pageable);
		return product.map(x -> new ProductDTO(x));
	}
}
