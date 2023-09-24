package com.workintech.employee.service;

import com.workintech.employee.entity.Employee;
import com.workintech.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()) {
            return employee.get();
        }
        return null;
    }

    @Override
    public Employee save(Employee employee) {
        Employee existingEmployee = findByEmail(employee.getEmail());
        if(existingEmployee == null) {
            return employeeRepository.save(employee);
        }
        return null;
    }

    @Override
    public Employee delete(int id) {
        Employee foundEmployee = findById(id);
        if(foundEmployee != null) {
            employeeRepository.delete(foundEmployee);
            return foundEmployee;
        }
        return null;
    }

    @Override
    public Employee findByEmail(String email) {
        Optional<Employee> foundEmployee = employeeRepository.findByEmail(email);
        if(foundEmployee.isPresent()) {
            return foundEmployee.get();
        }
        return null;
    }

    @Override
    public List<Employee> findBySalary(double salary) {
        return employeeRepository.findBySalary(salary);
    }

    @Override
    public List<Employee> findByOrder() {
        return employeeRepository.findByOrder();
    }
}
