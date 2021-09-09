package bd.info.habib.ibcscodechallenge2.employeeservice.dto.response;

import bd.info.habib.ibcscodechallenge2.employeeservice.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private Long id;

    private String code;

    private String name;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate dateOfBirth;

    private Gender gender;

    private String mobile;

    private String departmentName;
}
