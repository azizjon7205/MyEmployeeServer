package com.pdp.myemployeeserver.controller;

import com.pdp.myemployeeserver.model.Employee;
import com.pdp.myemployeeserver.model.ResponseEmployee;
import com.pdp.myemployeeserver.model.ResponseListEmployee;
import com.pdp.myemployeeserver.service.EmployeeService;
import com.pdp.myemployeeserver.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public HttpEntity<?> getEmployees(){
        ResponseListEmployee responseListEmployee = employeeService.getEmployees();
        return ResponseEntity.ok(responseListEmployee);
    }

    @GetMapping("/employee/{id}")
    public HttpEntity<?> getEmployee(@PathVariable(value = "id") String id){
        Employee responseEmployee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(new ResponseEmployee<>("Success", responseEmployee, "Successfully! Record has been fetched."));
    }

    @PostMapping("/create")
    public HttpEntity<?> createEmployee(@RequestBody Employee employee){
        ResponseEmployee<Employee> responseEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(201).body(responseEmployee);
    }

    @PutMapping("/update/{id}")
    public HttpEntity<?> editEmployee(@PathVariable(value = "id") String id, @RequestBody Employee employee){
        ResponseEmployee<Employee> responseEmployee = employeeService.editEmployee(id, employee);
        return ResponseEntity.status(responseEmployee != null ? HttpStatus.ACCEPTED : HttpStatus.NO_CONTENT).body(responseEmployee);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteEmployee(@PathVariable(value = "id") String id){
        boolean deleted = employeeService.deleteEmployee(id);
        return ResponseEntity.status(deleted ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(new ResponseEmployee<>("success", id, Messages.MESSAGE_SUCCESS_DELETED));
    }
}
