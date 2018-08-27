package br.com.diego.tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diego.tasks.model.Category;
import br.com.diego.tasks.model.Task;
import br.com.diego.tasks.repository.CategoryRepository;
import br.com.diego.tasks.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository repository;
	@Autowired
	private CategoryRepository repositoryCat;

	
	//Retorna todos as categorias
	public Iterable<Category> lstCategories() {

		Iterable<Category> categories = repositoryCat.findByStatus(1);
		return categories;

	}

	public Iterable<Task> listar() {

		Iterable<Task> tasks = repository.findAll();
		return tasks;

	}

	// Pegar registro
	public Task getRegistro(Long id) {
		return repository.findById(id).get();// orElse(null);
	}

	// Salvar camada de negócio
	public void save(Task task) {
		repository.save(task);
	}



	// Remover um registro
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
