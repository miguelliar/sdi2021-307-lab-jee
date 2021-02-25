package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Professor;
import com.uniovi.service.ProfessorService;

@RestController
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;
	
	@RequestMapping(value="/professor/add")
	public String getProfessor() {
		return "professor/add";
	}
	
	@RequestMapping("/professor/list")
	public String getList(Model model) {
		model.addAttribute("professorList", professorService.getProfessors() );
		return "professor/list";
	}
	
	@RequestMapping(value = "/professor/add", method=RequestMethod.POST )
	public String setMark(@ModelAttribute Professor mark){
		professorService.addProfessor(mark);
		return "redirect:/professor/list";
	}
	
	@RequestMapping("/professor/details/{id}")
	public String getDetail(Model model,@PathVariable Long id){
		model.addAttribute("professor", professorService.getProfessor(id));
		return"professor/details";
	}
	
	@RequestMapping("/professor/delete/{id}")
	public String deleteMark(@PathVariable Long id){
		professorService.deleteProfessor(id);
		return "redirect:/professor/list";
	}
	
	@RequestMapping(value="/professor/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id){
		model.addAttribute("professor", professorService.getProfessor(id));
		return"professor/edit";
	}
	
	@RequestMapping(value="/professor/edit/{id}", method=RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Professor professor) {
		professor.setDni(id);
		professorService.addProfessor(professor);
		return"redirect:/professor/details/"+id;
	}
}
