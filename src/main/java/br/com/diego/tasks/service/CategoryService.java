package br.com.diego.tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diego.tasks.model.Category;
import br.com.diego.tasks.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public Iterable<Category> listar() {

		Iterable<Category> categories = repository.findAll();
		return categories;

	}

	// Pegar registro
	public Category getRegistro(Long id) {
		return repository.findById(id).get();// orElse(null);
	}

	// Salvar camada de negócio
	public void save(Category category) {
		repository.save(category);
	}


	// Remover um registro
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
