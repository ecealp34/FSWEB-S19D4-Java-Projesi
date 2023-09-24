package com.workintech.employee.service;

import com.workintech.employee.entity.Employee;
import com.workintech.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    void findAll() {
        employeeService.findAll();
        verify(employeeRepository).findAll();
    }

    @Test
    void findById() {
        Employee employee = new Employee();
        employee.setId(1);
        employeeService.findById(employee.getId());
        verify(employeeRepository).findById(employee.getId());
    }

    @Test
    void save() {
        String email = "test@gmail.com";
        Employee employee = new Employee();
        employee.setEmail(email);

        given(employeeRepository.findByEmail(email)).willReturn(Optional.empty());
        given(employeeRepository.save(employee)).willReturn(employee);
        Employee savedEmployee = employeeService.save(employee);
        assertNotNull(savedEmployee);
        verify(employeeRepository).save(employee);
    }

    @Test
    void canNotSave() {
        String email = "test@gmail.com";
        Employee employee = new Employee();
        employee.setEmail(email);

        given(employeeRepository.findByEmail(email)).willReturn(Optional.of(employee));
        assertNull(employeeService.save(employee));
        verify(employeeRepository, never()).save(employee);
    }

    @Test
    void delete() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("cem");
        employee.setLastName("doga");
        employee.setEmail("cemdo@gmail.com");
        employee.setSalary(45000);

        given(employeeRepository.findById(1)).willReturn(Optional.of(employee));

        Employee removedEmployee = employeeService.delete(employee.getId());

        verify(employeeRepository).delete(employee);
        assertEquals("cem", removedEmployee.getFirstName());

    }

    @Test
    void findByEmail() {
        String email = "test";
        given(employeeRepository.findByEmail(email)).willReturn(Optional.empty());
        assertNull(employeeService.findByEmail(email));

        Employee employee = new Employee();
        employee.setFirstName("Seren");
        String email2 = "admin";

        given(employeeRepository.findByEmail(email2)).willReturn(Optional.of(employee));
        Employee foundEmployee = employeeService.findByEmail(email2);
        assertEquals("Seren", foundEmployee.getFirstName());
    }


    @Test
    void findByOrder() {
        employeeService.findByOrder();
        verify(employeeRepository).findByOrder();
    }
}