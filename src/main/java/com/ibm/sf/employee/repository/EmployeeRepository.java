package com.ibm.sf.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.sf.employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
