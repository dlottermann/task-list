package br.com.diego.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.diego.tasks.model.Category;
import br.com.diego.tasks.model.Task;
import br.com.diego.tasks.service.TaskService;

@Controller
public class TaskController {

	@Autowired
	private TaskService service;

	@RequestMapping("/")
	public String index(Model model) {

		Iterable<Task> tasks = service.listar();
		model.addAttribute("tasks", tasks);

		Iterable<Category> categories = service.lstCategories();
		model.addAttribute("categories", categories);


		return "index";
	}

	// Salvar
	@RequestMapping(value = "task/save", method = RequestMethod.POST)
	public String save(@RequestParam("title") String title, @RequestParam("description") String description,
			@RequestParam("created") String created, @RequestParam("categoria_id") Category cat, Model model) {

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date sqlDate = null;
		try {
			java.util.Date utilDate = format.parse(created);
			sqlDate = new java.sql.Date(utilDate.getTime());

		} catch (ParseException e) {
			e.printStackTrace();
		}

		Task c = new Task(title, description, sqlDate, 1, cat);

		service.save(c);
		Iterable<Task> tasks = service.listar();
		model.addAttribute("tasks", tasks);

		return "redirect:/";
	}

	// Seleciona registro para editar
	@RequestMapping(value = "task/getString", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Task getString(@RequestParam("id") Long id, @RequestParam("action") String action) {

		Task s = service.getRegistro(id);
		return s;
	}

	// Atualizar
	@RequestMapping(value = "task/update", method = RequestMethod.POST)
	public String update(@RequestParam("title") String title, @RequestParam("description") String description,
			@RequestParam("created") String created, @RequestParam("categoria_id") Category cat,
			@RequestParam("id") Long id, Model model) {

		Task c = service.getRegistro(id);

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date sqlDate = null;
		try {
			java.util.Date utilDate = format.parse(created);
			sqlDate = new java.sql.Date(utilDate.getTime());

		} catch (ParseException e) {
			e.printStackTrace();
		}

		
		c.setCreated(sqlDate);
		c.setTitle(title);
		c.setCategory(cat);
		c.setDescription(description);
		service.save(c);

		Iterable<Task> tasks = service.listar();
		model.addAttribute("tasks", tasks);

		return "redirect:/";
	}

	// Inativar Ou mesmo que deletar
	@RequestMapping(value = "task/dropString", method = RequestMethod.GET)
	public String setStatus(@RequestParam("id") Long id, Model model) {

		Task c = service.getRegistro(id);
		c.setStatus(0);
		service.save(c);

		Iterable<Task> categories = service.listar();
		model.addAttribute("categories", categories);

		return "redirect:/";
	}

	// Deletar
	@RequestMapping(value = "task/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id") Long id, Model model) {

		service.delete(id);
		Iterable<Task> task = service.listar();
		model.addAttribute("tasks", task);

		return "redirect:/";
	}

}
