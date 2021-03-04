package com.uniovi.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Mark;
import com.uniovi.repositories.MarkRepository;

@Service
public class MarkService {
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private MarkRepository marksRepository;
	
	public List<Mark> getMarks() {
		List<Mark> marks= new ArrayList<Mark>();
		marksRepository.findAll().forEach(marks::add);
		return marks;
	}
	
	public Mark getMark(Long id) {
		Set<Mark> consultedList= (Set<Mark>) httpSession.getAttribute("consultedList");
		if( consultedList== null) {
			consultedList= new HashSet<Mark>();
		}
		Mark obtainedmark= marksRepository.findById(id).get();
		consultedList.add(obtainedmark);
		httpSession.setAttribute("consultedList",consultedList);
		return obtainedmark;}
	
	public void addMark(Mark mark){
		// Si en Id es null le asignamos el ultimo + 1 de la lista 
		marksRepository.save(mark);
	}
	
	public void deleteMark(Long id) {
		marksRepository.deleteById(id);
	}
}