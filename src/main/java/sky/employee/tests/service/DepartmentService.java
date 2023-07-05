package sky.employee.tests.service;

import sky.employee.tests.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface DepartmentService {

    Optional<List<Employee>> listOfDepartment(int departmentId);
    Optional<Employee> minSalary(int departmentId);
    Optional<Employee> maxSalary(int departmentId);

    long sumSalary(int departmentId);
    Optional<Map<Integer,List<Employee>>> listByDepartments();
}
