package bd.info.habib.ibcscodechallenge2.employeeservice.service;

import bd.info.habib.ibcscodechallenge2.employeeservice.dto.request.EmployeeRequest;
import bd.info.habib.ibcscodechallenge2.employeeservice.dto.response.ApiMessageResponse;
import bd.info.habib.ibcscodechallenge2.employeeservice.dto.response.BasicApiResponse;
import bd.info.habib.ibcscodechallenge2.employeeservice.dto.response.EmployeeResponse;
import bd.info.habib.ibcscodechallenge2.employeeservice.model.DepartmentModel;
import bd.info.habib.ibcscodechallenge2.employeeservice.model.EmployeeModel;
import bd.info.habib.ibcscodechallenge2.employeeservice.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public ResponseEntity<BasicApiResponse<List<EmployeeResponse>>> getEmployeeList() {
        List<EmployeeModel> employeeModels = employeeRepository.findAll();

        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        for (EmployeeModel employeeModel : employeeModels) {
            EmployeeResponse employeeResponse = new EmployeeResponse(employeeModel.getId(), employeeModel.getCode(),
                    employeeModel.getName(), employeeModel.getDateOfBirth(), employeeModel.getGender(),
                    employeeModel.getMobile(), employeeModel.getDepartmentModel().getName());

            employeeResponses.add(employeeResponse);
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
        WebClient webClient = WebClient.create("http://localhost:8006");

        ResponseEntity<BasicApiResponse<DepartmentModel>> departmentModelMono = webClient.get()
                .uri("http://localhost:8006/departments/{employeeId}", employeeRequest.getDepartmentId())
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> Mono.empty()
                )
                .toEntity(new ParameterizedTypeReference<BasicApiResponse<DepartmentModel>>() {
                })
                .block();

        if (departmentModelMono != null && departmentModelMono.getStatusCodeValue() == 404) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No Department found with id: " + employeeRequest.getDepartmentId());
        }

        DepartmentModel departmentModel = departmentModelMono.getBody().getData();

        EmployeeModel employeeModel = new EmployeeModel(0L, employeeRequest.getCode(), employeeRequest.getName(),
                employeeRequest.getDateOfBirth(), employeeRequest.getGender(), employeeRequest.getMobile(),
                departmentModel.getId());

        employeeRepository.save(employeeModel);

        return new ResponseEntity<>(new ApiMessageResponse(201, "Employee Added"), HttpStatus.CREATED);
    }

    //Edit employee
    public ResponseEntity<ApiMessageResponse> editEmployee(Long employeeId, EmployeeRequest employeeRequest) {
//        Optional<EmployeeModel> employeeModelOptional = employeeRepository.findById(employeeId);
//        if (employeeModelOptional.isPresent()) { //null checking
//            EmployeeModel employeeModel = employeeModelOptional.get();
//
//            Optional<DepartmentModel> departmentModelOptional = departmentRepository.findById(employeeRequest.getDepartmentId());
//            if (departmentModelOptional.isPresent()) {
//                DepartmentModel departmentModel = departmentModelOptional.get();
//
//                employeeModel.setCode(employeeRequest.getCode());
//                employeeModel.setName(employeeRequest.getName());
//                employeeModel.setDateOfBirth(employeeRequest.getDateOfBirth());
//                employeeModel.setMobile(employeeRequest.getMobile());
//                employeeModel.setGender(employeeRequest.getGender());
//                employeeModel.setDepartmentModel(departmentModel);
//
//                return new ResponseEntity<>(new ApiMessageResponse(200, "Employee Edit Successful"), HttpStatus.OK);
//            } else {
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Department found with ID: "
//                        + employeeRequest.getDepartmentId());
//            }
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Employee found with ID: "
//                    + employeeId);
//        }

        return null;
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
//        Optional<EmployeeModel> employeeModelOptional = employeeRepository.findById(employeeId);
//        if (employeeModelOptional.isPresent()) {
//            EmployeeModel employeeModel = employeeModelOptional.get();
//
//            EmployeeResponse employeeResponse = new EmployeeResponse(employeeModel.getId(), employeeModel.getCode(),
//                    employeeModel.getName(), employeeModel.getDateOfBirth(), employeeModel.getGender(),
//                    employeeModel.getMobile(), employeeModel.getDepartmentModel().getName());
//
//            return new ResponseEntity<>(new BasicApiResponse<>(200, "Employee Found", employeeResponse),
//                    HttpStatus.OK);
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Employee found with ID: "
//                    + employeeId);
//        }

        return null;
    }
}
