package br.com.diego.tasks.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	@NotBlank(message = "Titulo da tarefa.")
	private String title;

	@Column(nullable = false, length = 250)
	@NotBlank(message = "Descrição da tarefa.")
	private String description;

	@Column(nullable = false, columnDefinition = "DATE")
	@Temporal(TemporalType.DATE)
	private Date created;

	@Column(nullable = false, length = 99, columnDefinition = "int default 1 comment '1 active - 0 inative'")
	private int status;

	// Relacionamento com categorias
	@ManyToOne
	private Category category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Task() {
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Task(@NotBlank(message = "Titulo da tarefa.") String title,
			@NotBlank(message = "Descrição da tarefa.") String description,
			@NotBlank(message = "Data criação da tarefa.") Date created, int status, Category category) {
		super();
		this.title = title;
		this.description = description;
		this.created = created;
		this.status = status;
		this.category = category;
	}

}
