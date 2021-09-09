package bd.info.habib.ibcscodechallenge2.employeeservice.controller;

import bd.info.habib.ibcscodechallenge2.employeeservice.dto.request.EmployeeRequest;
import bd.info.habib.ibcscodechallenge2.employeeservice.dto.response.ApiMessageResponse;
import bd.info.habib.ibcscodechallenge2.employeeservice.dto.response.BasicApiResponse;
import bd.info.habib.ibcscodechallenge2.employeeservice.dto.response.EmployeeResponse;
import bd.info.habib.ibcscodechallenge2.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    private ResponseEntity<BasicApiResponse<List<EmployeeResponse>>> getEmployeeList(){
        return employeeService.getEmployeeList();
    }

    //Create a new employee
    @PostMapping
    public ResponseEntity<ApiMessageResponse> addEmployee( @RequestBody EmployeeRequest employeeRequest){
        return employeeService.addEmployee(employeeRequest);
    }

    //Get a single employee by id
    @GetMapping("/{employeeId}")
    public ResponseEntity<BasicApiResponse<EmployeeResponse>> getEmployeeById(@PathVariable Long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

    //Edit an employee
    @PutMapping("/{employeeId}")
    public ResponseEntity<ApiMessageResponse> editEmployee( @RequestBody EmployeeRequest employeeRequest,
                                                           @PathVariable Long employeeId){
        return employeeService.editEmployee(employeeId, employeeRequest);
    }

    //Delete an employee by id
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<ApiMessageResponse> deleteEmployee(@PathVariable Long employeeId){
        return employeeService.deleteEmployee(employeeId);
    }

}
