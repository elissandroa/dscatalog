package edu.elissandro.dscatalog.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.elissandro.dscatalog.dto.CategoryDTO;
import edu.elissandro.dscatalog.dto.ProductDTO;
import edu.elissandro.dscatalog.entities.Category;
import edu.elissandro.dscatalog.entities.Product;
import edu.elissandro.dscatalog.repositories.CategoryRepository;
import edu.elissandro.dscatalog.repositories.ProductRepository;
import edu.elissandro.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
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
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyDtoToEntity(entity, dto);
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		Product entity = new Product();
		entity = repository.getReferenceById(id);
		copyDtoToEntity(entity, dto);
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}

	private void copyDtoToEntity(Product entity, ProductDTO dto) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setImgUrl(dto.getImgUrl());
		entity.setPrice(dto.getPrice());
		entity.setDate(dto.getDate());
		
		entity.getCategories().clear();
		
		for (CategoryDTO catDto : dto.getCategories()) {
			Category category = categoryRepository.getReferenceById(catDto.getId());
			entity.getCategories().add(category);
		}
		
	}
	
	
}
