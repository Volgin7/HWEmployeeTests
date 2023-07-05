package sky.employee.tests.controller;

import org.springframework.web.bind.annotation.*;
import sky.employee.tests.model.Employee;
import sky.employee.tests.service.DepartmentService;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService deptService;

    public DepartmentController(DepartmentService deptService) {
        this.deptService = deptService;
    }
    @GetMapping("/{departmentId}/employees")
    @ResponseBody
    public Optional<List<Employee>> listOdDepartment(@PathVariable int departmentId)
    {
        return deptService.listOfDepartment(departmentId);
    }

    @GetMapping("/{departmentId}/salary/min")
    public Optional<Employee> minSalary(@PathVariable int departmentId)
    {
        return deptService.minSalary(departmentId);
    }

    @GetMapping("/{departmentId}/salary/max")
    public Optional<Employee> maxSalary(@PathVariable int departmentId)
    {
        return deptService.maxSalary(departmentId);
    }

    @GetMapping("/{departmentId}/salary/sum")
    public long sumSalary(@PathVariable int departmentId)
    {
        return deptService.sumSalary(departmentId);
    }
    @GetMapping("/employees")
    public Optional<Map<Integer,List<Employee>>> listByDepartments()
    {
        return deptService.listByDepartments();
    }
}