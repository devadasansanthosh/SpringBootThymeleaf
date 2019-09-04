package com.ibm.sf.employee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibm.sf.employee.model.Employee;
import com.ibm.sf.employee.repository.EmployeeRepository;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository empRepository;
	
	@GetMapping("/add")
    public String showSignUpForm(Employee employee) {
        return "add-employee";
    }
	
	@PostMapping("/addemployee")
    public String addUser(@Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-employee";
        }
         
        empRepository.save(employee);
        model.addAttribute("employees", empRepository.findAll());
        return "index";
    }
	
	@GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Employee employee = empRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "update-employee";
    }
    
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	employee.setId(id);
            return "update-user";
        }
        
        empRepository.save(employee);
        model.addAttribute("employees", empRepository.findAll());
        return "index";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
    	Employee employee = empRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
    	empRepository.delete(employee);
        model.addAttribute("employees", empRepository.findAll());
        return "index";
    }

}
