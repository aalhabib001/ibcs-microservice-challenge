package bd.info.habib.ibcscodechallenge2.employeeservice.config;

import bd.info.habib.ibcscodechallenge2.employeeservice.dto.response.BasicApiResponse;
import bd.info.habib.ibcscodechallenge2.employeeservice.dto.response.DepartmentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "department-service", url = "http://localhost:8006/departments")
public interface FeignDepartment {

    @GetMapping
    BasicApiResponse<List<DepartmentResponse>> getDepartments();

    @GetMapping("/byIds")
    List<DepartmentResponse> getDepartmentsById(@RequestParam List<Long> ids);

    @GetMapping("/{departmentId}")
    BasicApiResponse<DepartmentResponse> getDepartmentById(@PathVariable Long departmentId);
}
