package bd.info.habib.ibcscodechallenge2.employeeservice.service;

import bd.info.habib.ibcscodechallenge2.employeeservice.config.FeignDepartment;
import bd.info.habib.ibcscodechallenge2.employeeservice.dto.request.EmployeeRequest;
import bd.info.habib.ibcscodechallenge2.employeeservice.dto.response.ApiMessageResponse;
import bd.info.habib.ibcscodechallenge2.employeeservice.dto.response.BasicApiResponse;
import bd.info.habib.ibcscodechallenge2.employeeservice.dto.response.DepartmentResponse;
import bd.info.habib.ibcscodechallenge2.employeeservice.dto.response.EmployeeResponse;
import bd.info.habib.ibcscodechallenge2.employeeservice.model.EmployeeModel;
import bd.info.habib.ibcscodechallenge2.employeeservice.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final FeignDepartment feignDepartment;

    public ResponseEntity<BasicApiResponse<List<EmployeeResponse>>> getEmployeeList() {
        List<EmployeeModel> employeeModels =
                employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "departmentId"));

        List<Long> deptIds = new ArrayList<>();
        for (EmployeeModel employeeModel : employeeModels) {
            deptIds.add(employeeModel.getDepartmentId());
        }

        List<DepartmentResponse> departmentResponses = feignDepartment.getDepartmentsById(deptIds);

        List<EmployeeResponse> employeeResponses = new ArrayList<>();

        for (EmployeeModel employeeModel : employeeModels) {

            for (DepartmentResponse departmentResponse : departmentResponses) {
                if (departmentResponse.getId().equals(employeeModel.getDepartmentId())) {
                    EmployeeResponse employeeResponse = new EmployeeResponse(employeeModel.getId(), employeeModel.getCode(),
                            employeeModel.getName(), employeeModel.getDateOfBirth(), employeeModel.getGender(),
                            employeeModel.getMobile(), departmentResponse.getName());

                    employeeResponses.add(employeeResponse);
                }

            }
        }

        if (employeeModels.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Employee Found");
        } else {
            return new ResponseEntity<>(new BasicApiResponse<>(200, "Employees Found", employeeResponses),
                    HttpStatus.OK);
        }
    }

    //Create a new employee
    public ResponseEntity<ApiMessageResponse> addEmployee(EmployeeRequest employeeRequest) {

        DepartmentResponse departmentResponse =
                feignDepartment.getDepartmentById(employeeRequest.getDepartmentId()).getData();

        EmployeeModel employeeModel = new EmployeeModel(0L, employeeRequest.getCode(), employeeRequest.getName(),
                employeeRequest.getDateOfBirth(), employeeRequest.getGender(), employeeRequest.getMobile(),
                departmentResponse.getId());

        employeeRepository.save(employeeModel);

        return new ResponseEntity<>(new ApiMessageResponse(201, "Employee Added"), HttpStatus.CREATED);
    }

    //Edit employee
    public ResponseEntity<ApiMessageResponse> editEmployee(Long employeeId, EmployeeRequest employeeRequest) {

        Optional<EmployeeModel> employeeModelOptional = employeeRepository.findById(employeeId);
        if (employeeModelOptional.isPresent()) { //null checking
            EmployeeModel employeeModel = employeeModelOptional.get();

            DepartmentResponse departmentResponse =
                    feignDepartment.getDepartmentById(employeeRequest.getDepartmentId()).getData();

            employeeModel.setCode(employeeRequest.getCode());
            employeeModel.setName(employeeRequest.getName());
            employeeModel.setDateOfBirth(employeeRequest.getDateOfBirth());
            employeeModel.setMobile(employeeRequest.getMobile());
            employeeModel.setGender(employeeRequest.getGender());
            employeeModel.setDepartmentId(departmentResponse.getId());

            return new ResponseEntity<>(new ApiMessageResponse(200, "Employee Edit Successful"), HttpStatus.OK);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Employee found with ID: "
                    + employeeId);
        }
    }

    //Delete employee by id
    public ResponseEntity<ApiMessageResponse> deleteEmployee(Long employeeId) {
        Optional<EmployeeModel> employeeModelOptional = employeeRepository.findById(employeeId);
        if (employeeModelOptional.isPresent()) {
            EmployeeModel employeeModel = employeeModelOptional.get();

            employeeRepository.delete(employeeModel);

            return new ResponseEntity<>(new ApiMessageResponse(200, "Employee Delete Successful"), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Employee found with ID: "
                    + employeeId);
        }
    }

    //Get a single employee by id
    public ResponseEntity<BasicApiResponse<EmployeeResponse>> getEmployeeById(Long employeeId) {
        Optional<EmployeeModel> employeeModelOptional = employeeRepository.findById(employeeId);
        if (employeeModelOptional.isPresent()) {
            EmployeeModel employeeModel = employeeModelOptional.get();

            DepartmentResponse departmentResponse =
                    feignDepartment.getDepartmentById(employeeModel.getDepartmentId()).getData();

            EmployeeResponse employeeResponse = new EmployeeResponse(employeeModel.getId(), employeeModel.getCode(),
                    employeeModel.getName(), employeeModel.getDateOfBirth(), employeeModel.getGender(),
                    employeeModel.getMobile(), departmentResponse.getName());

            return new ResponseEntity<>(new BasicApiResponse<>(200, "Employee Found", employeeResponse),
                    HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Employee found with ID: "
                    + employeeId);
        }
    }
}
