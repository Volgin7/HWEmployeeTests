package sky.employee.tests.service;

import org.springframework.stereotype.Repository;
import sky.employee.tests.exception.EmployeeNotFoundException;
import sky.employee.tests.model.Employee;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeServiceImpl employeeServiceImpl;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }


    @Override
    public List<Employee> listOfDepartment(int departmentId) {

        List<Employee> employees = new ArrayList<>(employeeServiceImpl.getEmployees().values());
        List<Employee> employeesInDepartment = employees.stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .collect(Collectors.toList());
        if(employeesInDepartment.isEmpty()) {
            throw new EmployeeNotFoundException("Employee Not Found");
        }
        return employeesInDepartment;
    }

    @Override
    public long minSalary(int departmentId) {
        List<Employee> employees = new ArrayList<>(employeeServiceImpl.getEmployees().values());
        long result = employees.stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .map(Employee::getSalary)
                .orElseThrow(EmployeeNotFoundException::new);
        return result;
    }

    @Override
    public long maxSalary(int departmentId) {
        List<Employee> employees = new ArrayList<>(employeeServiceImpl.getEmployees().values());
        long result = employees.stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .max(Comparator.comparing(Employee::getSalary))
                .map(Employee::getSalary)
                .orElseThrow(EmployeeNotFoundException::new);
        return result;
    }

    @Override
    public long sumSalary(int departmentId) {

        List<Employee> employees = new ArrayList<>(employeeServiceImpl.getEmployees().values());
        long result = employees.stream()
                .filter(e -> e.getDepartmentId() == departmentId)
 //               .sum(Comparator.comparing(Employee::getSalary));
                .collect(Collectors.summingLong(Employee::getSalary));
        return result;
    }

    @Override
    public Map<Integer,List<Employee>> listByDepartments() {
        List<Employee> employees = new ArrayList<>(employeeServiceImpl.getEmployees().values());
        Map<Integer, List<Employee>> mapEmployees = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));
        if(mapEmployees.isEmpty()) {
            throw new EmployeeNotFoundException();
        }
        return mapEmployees;
    }
}

