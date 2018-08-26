package br.com.diego.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import br.com.diego.tasks.model.Category;
import br.com.diego.tasks.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService service;

	@RequestMapping("/category")
	public String index(Model model) {

		Iterable<Category> categories = service.listar();
		model.addAttribute("categories", categories);

		return "categories";
	}

	

	// Salvar
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestParam("description") String description, Model model) {

		Category c = new Category(description);

		service.save(c);
		Iterable<Category> categories = service.listar();
		model.addAttribute("categories", categories);

		return "redirect:/category";
	}

	// Seleciona registro para editar
		@RequestMapping(value = "/getString", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
		@ResponseBody
		public Category getString(@RequestParam("id") Long id, @RequestParam("action") String action) {

			Category s = service.getRegistro(id);
			return s;
		}
	
	
	// Atualizar
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestParam("description") String description, @RequestParam("id") Long id, Model model) {

		Category c = service.getRegistro(id);
		c.setDescription(description);
		service.save(c);

		Iterable<Category> categories = service.listar();
		model.addAttribute("categories", categories);

		return "redirect:/category";
	}

	//Inativar Ou mesmo que deletar
	@RequestMapping(value = "/dropString", method = RequestMethod.GET)
	public String setStatus(@RequestParam("id") Long id, Model model) {

		Category c = service.getRegistro(id);
		c.setStatus(0);
		service.save(c);

		Iterable<Category> categories = service.listar();
		model.addAttribute("categories", categories);

		return "redirect:/category";
	}
	
	
	// Deletar
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id") Long id, Model model) {

		service.delete(id);
		Iterable<Category> categories = service.listar();
		model.addAttribute("categories", categories);

		return "index";
	}

}
