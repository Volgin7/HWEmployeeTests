package sky.employee.tests.service;

import sky.employee.tests.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface DepartmentService {

    List<Employee> listOfDepartment(int departmentId);
    long minSalary(int departmentId);
    long maxSalary(int departmentId);

    long sumSalary(int departmentId);
    Map<Integer,List<Employee>> listByDepartments();
}
