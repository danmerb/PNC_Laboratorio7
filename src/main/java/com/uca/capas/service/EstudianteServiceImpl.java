package com.uca.capas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.uca.capas.dao.EstudianteDAO;
import com.uca.capas.domain.Estudiante;
import com.uca.capas.repositories.EstudianteRepo;

@Service
public class EstudianteServiceImpl implements EstudianteService {
	
	@Autowired
	//EstudianteDAO estudianteDao;
	EstudianteRepo estudianteRepo;
	
	@Override
	public List<Estudiante> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return estudianteRepo.findAll();
	}
	
	@Override
	@Transactional
	public void insert(Estudiante estudiante) throws DataAccessException {
		// TODO Auto-generated method stub
		estudianteRepo.save(estudiante);
	}

	@Override
	public Estudiante findOne(Integer code) throws DataAccessException {
		// TODO Auto-generated method stub
		return estudianteRepo.getOne(code);
	}

	@Override
	public void save(Estudiante estudiante) throws DataAccessException {
		// TODO Auto-generated method stub
		estudianteRepo.save(estudiante);
	}
	
	@Override
	public List<Estudiante>  filtrarPor(String cadena) throws DataAccessException {
		// TODO Auto-generated method stub
		return estudianteRepo.findByNombre(cadena);
	}

	@Override
	@Transactional
	public void delete(Integer codigoEstudiant) throws DataAccessException {
		// TODO Auto-generated method stub
		estudianteRepo.deleteById(codigoEstudiant);
	}

}
