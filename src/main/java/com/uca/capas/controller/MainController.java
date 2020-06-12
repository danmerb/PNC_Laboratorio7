package com.uca.capas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.dao.EstudianteDAO;
import com.uca.capas.domain.Estudiante;
import com.uca.capas.service.EstudianteService;

@Controller
public class MainController {
	
	@Autowired
	private EstudianteService estudianteService;
	
	@RequestMapping("/inicio")
	public ModelAndView initMain() {
		
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		
		try {
			estudiantes = estudianteService.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("estudiantes",estudiantes);
		mav.setViewName("index");
		
		return mav;
	}
	
	@RequestMapping(value="/borrarEstudiante",method=RequestMethod.POST)
	public ModelAndView delete(@RequestParam(value="codigo")int id) {		
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		
		try {
			estudianteService.delete(id);
			estudiantes = estudianteService.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("estudiantes",estudiantes);
		mav.setViewName("index");
		
		return mav;
	}
	
	@RequestMapping(value="/buscarEstudiante",method=RequestMethod.POST,params="action=Buscar")
	public ModelAndView buscar(@RequestParam(value="codigo")int id) {		
		ModelAndView mav = new ModelAndView();
		Estudiante estudiante = null;
		
		try {
			estudiante = estudianteService.findOne(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("estudiante",estudiante);
		mav.setViewName("Estudiante");
		
		return mav;
	}
	
	@RequestMapping(value="/buscarEstudiante",method=RequestMethod.POST,params="action=Editar")
	public ModelAndView editar(@RequestParam(value="codigo")int id) {		
		ModelAndView mav = new ModelAndView();
		Estudiante estudiante = null;
		
		try {
			estudiante = estudianteService.findOne(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("estudiante",estudiante);
		mav.setViewName("editar");
		
		return mav;
	}
	

	@RequestMapping(value="/filtrarEstudiante",method=RequestMethod.POST)
	public ModelAndView filt(@RequestParam(value="nombre") String cadena ) {		
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		
		try {
			estudiantes = estudianteService.filtrarPor(cadena);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("estudiantes",estudiantes);
		mav.setViewName("index");
		
		return mav;
	}
	
	@RequestMapping("/ins")
	public ModelAndView inse() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("estudiante", new Estudiante());
		mav.setViewName("InsertEstudiante");
		
		return mav;
	}
	
	
	
	@RequestMapping("/insertar")
	public ModelAndView insertar(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) {
			mav.setViewName("InsertEstudiante");
			return mav;
		}else {
			try {
				estudianteService.insert(estudiante);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			mav.addObject("estudiante", new Estudiante());
			mav.setViewName("InsertEstudiante");
		}
		
		return mav;
	}

}
