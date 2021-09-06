package bd.info.habib.ibcscodechallenge2.departmentservice.controller;

import bd.info.habib.ibcscodechallenge2.departmentservice.dto.request.DepartmentRequest;
import bd.info.habib.ibcscodechallenge2.departmentservice.dto.response.ApiMessageResponse;
import bd.info.habib.ibcscodechallenge2.departmentservice.dto.response.BasicApiResponse;
import bd.info.habib.ibcscodechallenge2.departmentservice.dto.response.DepartmentResponse;
import bd.info.habib.ibcscodechallenge2.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    //Getting all the list of departments
    @GetMapping
    public ResponseEntity<BasicApiResponse<List<DepartmentResponse>>> getDepartments() {
        return departmentService.getDepartments();
    }

    //Create a new Department
    @PostMapping
    public ResponseEntity<ApiMessageResponse> createDepartment(@Valid @RequestBody DepartmentRequest departmentRequest) {
        return departmentService.createDepartment(departmentRequest);
    }

    //Getting a single department with department ID
    @GetMapping("/{departmentId}")
    public ResponseEntity<BasicApiResponse<DepartmentResponse>> getDepartmentById(@PathVariable Long departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    //Edit Department
    @PutMapping("/{departmentId}")
    public ResponseEntity<ApiMessageResponse> editDepartment(@PathVariable Long departmentId,
                                                             @Valid @RequestBody DepartmentRequest departmentRequest) {
        return departmentService.editDepartment(departmentId, departmentRequest);
    }

    //Delete Department with ID
    @DeleteMapping("/{departmentId}")
    public ResponseEntity<ApiMessageResponse> deleteDepartment(@PathVariable Long departmentId) {
        return departmentService.deleteDepartment(departmentId);
    }

}
