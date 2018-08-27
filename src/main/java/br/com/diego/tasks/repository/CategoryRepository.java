package br.com.diego.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.diego.tasks.model.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query("select c from Category c where c.status = ?1")
	Iterable<Category>  findByStatus(int status);
}
