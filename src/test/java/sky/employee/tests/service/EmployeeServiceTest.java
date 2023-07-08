package sky.employee.tests.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sky.employee.tests.exception.EmployeeAlreadyAddedException;
import sky.employee.tests.exception.EmployeeNotFoundException;
import sky.employee.tests.exception.StorageFullException;
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
        service.add("Alexandra","Volgina",3, 1_111);
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
        Assertions.assertTrue(service.list().containsValue(newEmployee));
    }
    @Test
    @DisplayName("Test employee already added exception")
    void addEmployeeAlreadyExistExceptionTest() throws EmployeeAlreadyAddedException {
        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> service.add("Alex","Volgin",2, 5_000));
    }
    @Test
    @DisplayName("Test full storage exception")
    void addStorageFullExceptionTest() throws StorageFullException {
        service.add("Ann","Volgin",2, 5_000).toString();
        Assertions.assertThrows(StorageFullException.class, () -> service.add("Artem","Petrosyan",2, 1_000));
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
    @DisplayName("Test remove employee - positive")
    void removePositiveTest() {
        int size = service.getSize();
        Employee removedEmployee = service.remove("Alex","Volgin");
        Assertions.assertEquals(--size, service.getSize());
        Assertions.assertFalse(service.list().containsValue(removedEmployee));
    }

    @Test
    @DisplayName("Test remove employee - negative")
    void removeNegativeTest() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> service.remove("Alexey","Volgin"));
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
