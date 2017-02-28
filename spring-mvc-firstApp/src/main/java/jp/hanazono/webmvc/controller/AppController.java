package jp.hanazono.webmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.hanazono.webmvc.dao.PersonDAO;
import jp.hanazono.webmvc.domain.Person;

@Controller
@RequestMapping("/")
public class AppController {
	@Autowired
	@Qualifier("personDAO")
	private PersonDAO personDAO;

	@RequestMapping("/")
	public String redirectIndex(ModelMap model) {
		model.addAttribute("author", "Alexander Bogdanovich");
		model.addAttribute("Persons", personDAO.getAllPersons());
		return "index";
	}

	@RequestMapping("/delete")
	public String deletePerson(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		personDAO.deletePerson(personDAO.findByID(id));
		return "redirect:/";
	}

	@RequestMapping("/edit")
	String editPerson(HttpServletRequest request, ModelMap model) {
		if (request.getParameter("id") != null) {
			Person person = new Person();
			person = personDAO.findByID(Integer.parseInt(request.getParameter("id")));
			model.addAttribute("person", person);
		}

		return "edit";
	}

	@RequestMapping("/save")
	public String savePerson(HttpServletRequest request) {
		Person person = new Person();

		person.setFirstname(request.getParameter("firstname"));
		person.setLastname(request.getParameter("lastname"));
		person.setAge(Integer.parseInt(request.getParameter("age")));
		if (request.getParameter("id") != "") {
			person.setId(Integer.parseInt(request.getParameter("id")));
			personDAO.updatePerson(person);

		} else {
			personDAO.addPerson(person);
		}
		return "redirect:/";

	}
}
