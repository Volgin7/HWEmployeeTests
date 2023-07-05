package sky.employee.tests.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


import sky.employee.tests.exception.EmployeeAlreadyAddedException;
import sky.employee.tests.exception.EmployeeNotFoundException;
import sky.employee.tests.exception.StorageFullException;
import sky.employee.tests.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;
    private final int MAX_NUMBER_OF_EMPLOYEES = 6;

    public Map<String, Employee> getEmployees() {
        return employees;
    }

    public int getMAX_NUMBER_OF_EMPLOYEES() {

        return MAX_NUMBER_OF_EMPLOYEES;
    }

    public  EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }
    @Override
    public int getSize() {return this.employees.size();}
    @Override
    public Employee add(String firstName, String lastName, int departmentId, long salary) {
        if(this.employees.size()>=this.getMAX_NUMBER_OF_EMPLOYEES()) {
            throw new StorageFullException("Full storage");
        }
        String key = lastName + firstName;
        key = key.toLowerCase();
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Employee already added");
        }
        Employee employee = new Employee(firstName, lastName, departmentId, salary);
        employees.put(key, employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {

        String key = lastName + firstName;
        key = key.toLowerCase();
        if (employees.containsKey(key)) {
            Employee employee = new Employee(firstName, lastName, employees.get(key).getDepartmentId(), employees.get(key).getSalary());
            employees.remove(key);
            return employee;
        }
        throw new EmployeeNotFoundException("Employee Not Found");
    }

    @Override
    public Employee find(String firstName, String lastName) {
        String key = lastName + firstName;
        key = key.toLowerCase();
        if (employees.containsKey(key)) {
            Employee employee = new Employee(firstName, lastName, employees.get(key).getDepartmentId(), employees.get(key).getSalary());
            return employee;
        }
        throw new EmployeeNotFoundException("Employee not found");
    }

    @Override
    public Map<String, Employee> list() {
        return new HashMap<>(employees);
    }

    @Override
    public Employee setSalary(String firstName, String lastName, long salary) {

        String key = lastName + firstName;
        key = key.toLowerCase();
        if (employees.containsKey(key)) {
            employees.get(key).setSalary(salary);
            Employee employee = new Employee(firstName, lastName, employees.get(key).getDepartmentId(), employees.get(key).getSalary());
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee setDepartmentId(String firstName, String lastName, int departmentId) {

        String key =  lastName + firstName;
        key = key.toLowerCase();
        if (employees.containsKey(key)) {
            employees.get(key).setDepartmentId(departmentId);
            Employee employee = new Employee(firstName, lastName, employees.get(key).getDepartmentId(), employees.get(key).getSalary());
            return employee;
        }
        throw new EmployeeNotFoundException();
    }


}
