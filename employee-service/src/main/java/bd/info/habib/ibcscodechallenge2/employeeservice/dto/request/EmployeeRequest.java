package bd.info.habib.ibcscodechallenge2.employeeservice.dto.request;

import bd.info.habib.ibcscodechallenge2.employeeservice.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    @Size(min = 4, max = 4)
    private String code;

    @Size(min = 3, max = 35)
    @NotNull
    private String name;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private Gender gender;

    private String mobile;

    private Long departmentId;
}
