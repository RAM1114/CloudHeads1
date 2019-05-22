package com.test.springboot.sample.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.springboot.sample.model.Employee;

public interface EmployeeService extends JpaRepository<Employee, Integer>{
	
	
}
