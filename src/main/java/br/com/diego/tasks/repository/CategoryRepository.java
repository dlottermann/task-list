package br.com.diego.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.diego.tasks.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
