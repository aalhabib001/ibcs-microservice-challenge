package bd.info.habib.ibcscodechallenge2.departmentservice.service;

import bd.info.habib.ibcscodechallenge2.departmentservice.dto.request.DepartmentRequest;
import bd.info.habib.ibcscodechallenge2.departmentservice.dto.response.ApiMessageResponse;
import bd.info.habib.ibcscodechallenge2.departmentservice.dto.response.BasicApiResponse;
import bd.info.habib.ibcscodechallenge2.departmentservice.dto.response.DepartmentResponse;
import bd.info.habib.ibcscodechallenge2.departmentservice.model.DepartmentModel;
import bd.info.habib.ibcscodechallenge2.departmentservice.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    //Getting all the list of departments
    public ResponseEntity<BasicApiResponse<List<DepartmentResponse>>> getDepartments() {
        //Getting All The list of departments
        List<DepartmentModel> departmentModels = departmentRepository.findAll();

        List<DepartmentResponse> departmentResponses = new ArrayList<>();
        //Converting Department model to department response for sending in frontend
        for (DepartmentModel departmentModel : departmentModels) {
            DepartmentResponse departmentResponse = new DepartmentResponse(departmentModel.getId(),
                    departmentModel.getName(), departmentModel.getActive());
            departmentResponses.add(departmentResponse);
        }

        if (departmentModels.isEmpty()) {
            //If No department found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Departments Found");
        } else {
            //If department found
            return new ResponseEntity<>(new BasicApiResponse<>(200, "Departments Found",
                    departmentResponses), HttpStatus.OK);
        }

    }

    //Create a new Department
    public ResponseEntity<ApiMessageResponse> createDepartment(DepartmentRequest departmentRequest) {
        //Creating a new object of department model to save on database
        DepartmentModel departmentModel = new DepartmentModel(0L, departmentRequest.getName(), departmentRequest.getActive());

        departmentRepository.save(departmentModel); //Saving in database

        return new ResponseEntity<>(new ApiMessageResponse(201, "Department Created"), HttpStatus.CREATED);
    }

    //Edit Department
    public ResponseEntity<ApiMessageResponse> editDepartment(Long departmentId, DepartmentRequest departmentRequest) {
        Optional<DepartmentModel> departmentModelOptional = departmentRepository.findById(departmentId);
        //Checking if the Department is available
        if (departmentModelOptional.isPresent()) {
            //If department found
            DepartmentModel departmentModel = departmentModelOptional.get();

            departmentModel.setName(departmentRequest.getName());
            departmentModel.setActive(departmentRequest.getActive());

            departmentRepository.save(departmentModel);

            return new ResponseEntity<>(new ApiMessageResponse(200, "Department info edit Successful"),
                    HttpStatus.OK);
        } else {
            //If No department found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Department found with ID: " + departmentId);
        }
    }

    //Delete Department with ID
    public ResponseEntity<ApiMessageResponse> deleteDepartment(Long departmentId) {
        Optional<DepartmentModel> departmentModelOptional = departmentRepository.findById(departmentId);
        //Checking if the Department is available
        if (departmentModelOptional.isPresent()) {
            //If department found
            DepartmentModel departmentModel = departmentModelOptional.get();

            departmentRepository.delete(departmentModel);

            return new ResponseEntity<>(new ApiMessageResponse(200, "Department info delete Successful"),
                    HttpStatus.OK);
        } else {
            //If No department found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Department found with ID: " + departmentId);
        }
    }

    //Getting a single department with department ID
    public ResponseEntity<BasicApiResponse<DepartmentResponse>> getDepartmentById(Long departmentId) {
        Optional<DepartmentModel> departmentModelOptional = departmentRepository.findById(departmentId);

        //Checking if the Department is available
        if(departmentModelOptional.isPresent()){
            //If department found
            DepartmentModel departmentModel = departmentModelOptional.get();

            DepartmentResponse departmentResponse = new DepartmentResponse(departmentModel.getId(),
                    departmentModel.getName(), departmentModel.getActive());

            return new ResponseEntity<>(new BasicApiResponse<>(200,"Department Found",departmentResponse),
                    HttpStatus.OK);
        }
        else {
            //If No department found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Department found with ID: " + departmentId);
        }
    }
}
