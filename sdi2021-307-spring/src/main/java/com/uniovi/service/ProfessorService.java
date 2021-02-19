package com.uniovi.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.uniovi.entities.Professor;

@Service
public class ProfessorService {
	
	
	private List<Professor> professorList = new ArrayList<>();
	
	@PostConstruct
    public void init() {
        professorList.add(new Professor(1L, "Carmen", "Ligero", "Matemáticas"));
        professorList.add(new Professor(2L, "Paco", "Suárez", "Lengua"));
    }
	
	public List<Professor> getProfessors() {
		return professorList;
	}
	
	public Professor getProfessor (Long id) {
		return professorList.stream().filter(professor-> professor.getDni().equals(id)).findFirst().get();
	}
	
	public void addProfessor(Professor professor){
		// Si en Id es null le asignamos el ultimo + 1 de la lista
		if (professor.getDni() == null) {
			professor.setDni(professorList.get(professorList.size() - 1).getDni() + 1);
		}
		professorList.add(professor);
	}
	
	public void deleteProfessor(Long id) {
		professorList.removeIf(professor -> professor.getDni().equals(id));
	}
}
