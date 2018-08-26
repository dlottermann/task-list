package br.com.diego.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diego.tasks.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
