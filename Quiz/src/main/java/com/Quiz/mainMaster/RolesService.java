package com.Quiz.mainMaster;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesService {
	
	@Autowired
	private  RolesRepository repo;
	
	public List<Roles> listAll(){
		return repo.findAll();
	}
	public Roles getById(Integer id) {
		return repo.findById(id).get();
	}
	public void save(Roles std) {
		repo.save(std);
	}
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
	public Roles findRolesNameById(Integer id) {
		return repo.findRolesNameById(id);
	}
	public List<Roles> findRolesByCompanyId(Integer id){
		return repo.findRolesByCompanyId(id);
	}

}
