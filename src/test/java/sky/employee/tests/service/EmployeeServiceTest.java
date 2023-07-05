package sky.employee.tests.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import sky.employee.tests.exception.EmployeeAlreadyAddedException;
import sky.employee.tests.exception.EmployeeNotFoundException;
import sky.employee.tests.model.Employee;

public class EmployeeServiceTest {
    private EmployeeService service;
    @BeforeEach
    public void setUp() {
        service = new EmployeeServiceImpl();
        service.add("Alex","Volgin",1, 4_000);
        service.add("Helen","Volgin",1, 3_000);
        service.add("Vlad","Volgin",2, 2_000);
        service.add("Igor","Volgin",2, 2_000);
    }
    @Test
    @DisplayName("Test add new employee - number of employees += 1")
    void addTest() {
        int size = service.getSize();
        service.add("Ann","Volgin",2, 5_000);
        Assertions.assertEquals(++size, service.getSize());
    }

    @Test
    @DisplayName("Test new employee is added")
    void addEmployeeTest() {
        Employee newEmployee = new Employee("Ann","Volgin",2, 5_000);
        String expected = newEmployee.toString();
        Assertions.assertEquals(expected, service.add("Ann","Volgin",2, 5_000).toString());
    }
    @Test
    @DisplayName("Test employee already added exception")
    void addExceptionTest() throws EmployeeAlreadyAddedException {
        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> service.add("Alex","Volgin",2, 5_000));
    }

    @Test
    @DisplayName("Test find employee - not found exception")
    void findNotFoundExceptionTest() throws EmployeeNotFoundException {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> service.find("Alexey","Volgin"));
    }
    @Test
    @DisplayName("Test find employee")
    void findTest() {
        String expected = service.add("Ann","Volgin",2, 5_000).toString();
        String actual = service.find("Ann","Volgin").toString();
        Assertions.assertEquals(expected, actual);
    }
    @Test
    @DisplayName("Test remove employee - number of employees -= 1")
    void removeTest() {
        int size = service.getSize();
        service.remove("Alex","Volgin");
        Assertions.assertEquals(--size, service.getSize());
    }

    @Test
    @DisplayName("Test set salary")
    void setSalaryTest()  {
        long expected = 11_000L;
        Employee employee = service.setSalary("Alex","Volgin", expected);
        Assertions.assertEquals(expected + 0, employee.getSalary());
    }

    @Test
    @DisplayName("Test set department")
    void setDepartmentTest()  {
        int expected = 11;
        Employee employee = service.setDepartmentId("Alex","Volgin", expected);
        Assertions.assertEquals(expected + 0, employee.getDepartmentId());
    }

    @Test
    @DisplayName("Test list")
    void listNotNullTest()  {
        Assertions.assertNotNull(service.list());
    }

}
