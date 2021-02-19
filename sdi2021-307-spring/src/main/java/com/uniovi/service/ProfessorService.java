package com.uniovi.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Professor;
import com.uniovi.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	private List<Professor> professorList = new ArrayList<>();
	
	@PostConstruct
    public void init() {
        professorList.add(new Professor(1L, "Carmen", "Ligero", "matematicas"));
        professorList.add(new Professor(2L, "Paco", "Bebelin", "Historia"));
    }
	
	public List<Professor> getProfessors() {
		List<Professor> professors= new ArrayList<Professor>();
		professorRepository.findAll().forEach(professors::add);
		return professors;
	}
	
	public Professor getProfessor (Long id) {
		return professorRepository.findById(id).get();
	}
	
	public void addProfessor(Professor professor){
		// Si en Id es null le asignamos el ultimo + 1 de la lista 
		professorRepository.save(professor);
	}
	
	public void deleteProfessor(Long id) {
		professorRepository.deleteById(id);
	}
}
