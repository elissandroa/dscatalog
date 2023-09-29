package edu.elissandro.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.elissandro.dscatalog.dto.CategoryDTO;
import edu.elissandro.dscatalog.entities.Category;
import edu.elissandro.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category>categories = repository.findAll();
		return categories.stream().map(x -> new CategoryDTO(x)).toList();
	}

}