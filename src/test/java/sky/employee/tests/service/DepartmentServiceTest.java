package sky.employee.tests.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sky.employee.tests.exception.EmployeeNotFoundException;
import sky.employee.tests.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    EmployeeServiceImpl empService;

    @InjectMocks
    DepartmentServiceImpl depService;

    @BeforeEach
    void init() {
        Map<String, Employee> employees = new HashMap<>();

        employees.put("volginalex", new Employee("Alex","Volgin",1, 4_000));
        employees.put("volginhelen", new Employee("Helen","Volgin",1, 3_000));
        employees.put("volginvlad",new Employee("Vlad","Volgin",2, 2_000));
        employees.put("volginigor",new Employee("Igor","Volgin",2, 2_000));
        employees.put("volginalexandra",new Employee("Alexandra","Volgin",3, 1_111));

        when(empService.getEmployees()).thenReturn(employees);
    }

    @Test
    @DisplayName("Test of sumSalary method")
    void sumSalary() {
         long actual = depService.sumSalary(1);
         long expected = 7_000L;
        Assertions.assertEquals(expected,actual);

    }
    @Test
    @DisplayName("Test of maxSalary method")
    void maxSalary() {
        long actual = depService.maxSalary(1);
        long expected = 4_000L;
        Assertions.assertEquals(expected,actual);

    }

    @Test
    @DisplayName("Test of minSalary method")
    void minSalary() {
        long actual = depService.minSalary(1);
        long expected = 3_000L;
        Assertions.assertEquals(expected,actual);

    }
    @Test
    @DisplayName("Test of getAllByDepartments")
    void getAllByDepartments() {
        Map<Integer, List<Employee>> actual = depService.listByDepartments();
        Assertions.assertEquals(3,actual.keySet().size());
    }
    @Test
    @DisplayName("Test of listOfDepartment")
    void listOfDepartment() {
        List<Employee> actual = depService.listOfDepartment(3);
        List<Employee> expected = List.of(new Employee("Alexandra","Volgin",3, 1_111));
        Assertions.assertIterableEquals(expected,actual);
    }
    @Test
    @DisplayName("Test of listOfDepartment if wrong number of department")
    void listOfWrongDepartment() {
        Assertions.assertThrows(EmployeeNotFoundException.class,() -> depService.listOfDepartment(11));
    }


}
