package com.pdp.myemployeeserver.service;

import com.pdp.myemployeeserver.model.Employee;
import com.pdp.myemployeeserver.model.ResponseEmployee;
import com.pdp.myemployeeserver.model.ResponseListEmployee;
import com.pdp.myemployeeserver.repository.EmployeeRepository;
import com.pdp.myemployeeserver.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseListEmployee getEmployees() {
        return new ResponseListEmployee("success", employeeRepository.findAll(), Messages.MESSAGE_SUCCESS_LIST);
    }

    public Employee getEmployeeById(String id){
        return employeeRepository.findById(id).orElse(null);
    }

    public ResponseEmployee<Employee> createEmployee(Employee employee) {
        return new ResponseEmployee<>("status", employeeRepository.save(employee), Messages.MESSAGE_SUCCESS);
    }

    public ResponseEmployee<Employee> editEmployee(String id, Employee employee) {
        Employee editEmployee = getEmployeeById(id);
        if (editEmployee != null){
            editEmployee.setEmployee_age(employee.getEmployee_age());
            editEmployee.setEmployee_name(employee.getEmployee_name());
            editEmployee.setEmployee_salary(employee.getEmployee_salary());
            editEmployee.setProfile_image(employee.getProfile_image());
            employeeRepository.save(editEmployee);
            return new ResponseEmployee<>("success", editEmployee, Messages.MESSAGE_SUCCESS_UPDATED);
        } else {
            return null;
        }
    }

    public boolean deleteEmployee(String id) {
        Employee deletedEmployee = getEmployeeById(id);
        if (deletedEmployee == null){
            return false;
        } else {
            try{
                employeeRepository.delete(deletedEmployee);
                return true;
            }catch (Exception e){
                return false;
            }

        }
    }
}
