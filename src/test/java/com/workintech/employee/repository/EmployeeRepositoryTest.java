package com.workintech.employee.repository;

import com.workintech.employee.entity.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeRepositoryTest(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @BeforeEach
    void setUp() {
        Employee employee1 = new Employee();
        employee1.setFirstName("Mert");
        employee1.setLastName("Can");
        employee1.setEmail("mertcan@gmail.com");
        employee1.setSalary(35000);

        Employee employee2 = new Employee();
        employee2.setFirstName("Ege");
        employee2.setLastName("Aydın");
        employee2.setEmail("ege@gmail.com");
        employee2.setSalary(45000);

        Employee employee3 = new Employee();
        employee3.setFirstName("Seren");
        employee3.setLastName("Doga");
        employee3.setEmail("doga@gmail.com");
        employee3.setSalary(55000);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employeeRepository.saveAll(employees);
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void findByEmail() {
        String notFoundedEmail = "test@gmail.com";
        Optional<Employee> employee = employeeRepository.findByEmail(notFoundedEmail);
        assertEquals(Optional.empty(), employee);

        String foundedEmail = "mertcan@gmail.com";
        Optional<Employee> foundedEmployee = employeeRepository.findByEmail(foundedEmail);
        assertNotNull(foundedEmployee.get());
        assertEquals("Mert", foundedEmployee.get().getFirstName());
        assertEquals(35000, foundedEmployee.get().getSalary());
    }

    @Test
    void findBySalary() {
        List<Employee> employees = employeeRepository.findBySalary(35000);
        assertEquals(2, employees.size());
        assertEquals("Seren", employees.get(0).getFirstName());
        assertEquals("Ege", employees.get(1).getFirstName());

    }

    @Test
    void findByOrder() {
        List<Employee> employees = employeeRepository.findByOrder();
        assertEquals(3, employees.size());
        assertEquals("ege@gmail.com", employees.get(0).getEmail());
        assertEquals("mertcan@gmail.com", employees.get(1).getEmail());
        assertEquals("doga@gmail.com", employees.get(2).getEmail());
    }
}