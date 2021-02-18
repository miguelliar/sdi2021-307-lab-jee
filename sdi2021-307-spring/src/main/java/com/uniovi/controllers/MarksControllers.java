package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Mark;
import com.uniovi.service.MarksService;

@RestController
public class MarksControllers {
	
	@Autowired //Inyectar el servicio 
	private MarksService marksService;
	
	@RequestMapping("/mark/list")
	public String getList(){
		return marksService.getMarks().toString();
	}
	
	@RequestMapping(value = "/mark/add", method=RequestMethod.POST )
	public String setMark(@RequestParam Mark mark){
		marksService.addMark(mark);
		return "OK";
	}
	
	@RequestMapping("/mark/details/{id}")
	public String getDetail(@RequestParam Long id){
		return marksService.getMark(id).toString();
	}
	
	@RequestMapping("/mark/delete/{id}")
	public String deleteMark(@RequestParam Long id){
		marksService.deleteMark(id);
		return "OK";
	}
}
